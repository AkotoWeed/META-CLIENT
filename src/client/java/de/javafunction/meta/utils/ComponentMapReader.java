package de.javafunction.meta.utils;
import com.google.gson.Gson;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.HotbarStorageEntry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.CommandSource;
import net.minecraft.command.DataCommandObject;
import net.minecraft.command.EntityDataObject;
import net.minecraft.command.argument.NbtPathArgumentType;
import net.minecraft.component.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ComponentMapReader {
    public static class EasyComp{
        public static ComponentMap MapChangesFromString(String str) throws CommandSyntaxException {
            return new ComponentMapReader.Reader(new StringReader(str), NbtOps.INSTANCE).read();
        }

        public static String GetItemJsonString(ItemStack itm) throws CommandSyntaxException {
            //NbtCompound nbtCompound = itm.getComponents().//NbtHelper.putDataVersion(new NbtCompound());

            /*for(int i = 0; i < 9; ++i) {
                HotbarStorageEntry hotbarStorageEntry = this.getSavedHotbar(i);
                DataResult<NbtElement> dataResult = HotbarStorageEntry.CODEC.encodeStart(NbtOps.INSTANCE, hotbarStorageEntry);
                nbtCompound.put(String.valueOf(i), (NbtElement)dataResult.getOrThrow());
            }*/

            //HotbarStorage

            return itm.getComponents().toString();//nbtCompound.toString();
        }

        public static String GetHeldItemCompString() throws CommandSyntaxException {
            DataCommandObject dataCommandObject = new EntityDataObject(MinecraftClient.getInstance().player);
            NbtPathArgumentType.NbtPath handPath = NbtPathArgumentType.NbtPath.parse("SelectedItem");

            MutableText text = Text.empty();
            String nbt = "";

            try {
                List<NbtElement> nbtElement = handPath.get(dataCommandObject.getNbt());
                if (!nbtElement.isEmpty()) {
                    text.append(NbtHelper.toPrettyPrintedText(nbtElement.getFirst()));
                    nbt = nbtElement.getFirst().toString();
                }
            } catch (CommandSyntaxException e) {
                text.append("[]");
            }

            return nbt;//text.getString().substring(0, text.getString().length() -1).replace("{components: {", "[").replace("\"", "").replace(":", "=") + "]";
        }
    }

    private static final DynamicCommandExceptionType UNKNOWN_COMPONENT_EXCEPTION = new DynamicCommandExceptionType(
            id -> Text.stringifiedTranslatable("arguments.item.component.unknown", id)
    );
    private static final SimpleCommandExceptionType COMPONENT_EXPECTED_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("arguments.item.component.expected"));
    private static final DynamicCommandExceptionType REPEATED_COMPONENT_EXCEPTION = new DynamicCommandExceptionType(
            type -> Text.stringifiedTranslatable("arguments.item.component.repeated", type)
    );
    private static final Dynamic2CommandExceptionType MALFORMED_COMPONENT_EXCEPTION = new Dynamic2CommandExceptionType(
            (type, error) -> Text.stringifiedTranslatable("arguments.item.component.malformed", type, error)
    );
    private final DynamicOps<NbtElement> nbtOps;

    public ComponentMapReader(CommandRegistryAccess commandRegistryAccess) {
        this.nbtOps = commandRegistryAccess.getOps(NbtOps.INSTANCE);
    }

    public ComponentMap consume(StringReader reader) throws CommandSyntaxException {
        int cursor = reader.getCursor();

        try {
            return new Reader(reader, nbtOps).read();
        } catch (CommandSyntaxException e) {
            reader.setCursor(cursor);
            throw e;
        }
    }

    public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder) {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());
        Reader reader = new Reader(stringReader, nbtOps);

        try {
            reader.read();
        } catch (CommandSyntaxException ignored) {
        }

        return reader.suggestor.apply(builder.createOffset(stringReader.getCursor()));
    }

    private static class Reader {
        private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_DEFAULT = SuggestionsBuilder::buildFuture;
        private final StringReader reader;
        private final DynamicOps<NbtElement> nbtOps;
        public Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestor = this::suggestBracket;

        public Reader(StringReader reader, DynamicOps<NbtElement> nbtOps) {
            this.reader = reader;
            this.nbtOps = nbtOps;
        }

        public ComponentMap read() throws CommandSyntaxException {
            ComponentMap.Builder builder = ComponentMap.builder();

            reader.expect('[');
            suggestor = this::suggestComponentType;
            Set<ComponentType<?>> set = new ReferenceArraySet<>();

            while(reader.canRead() && reader.peek() != ']') {
                reader.skipWhitespace();
                ComponentType<?> dataComponentType = readComponentType(reader);
                if (!set.add(dataComponentType)) {
                    throw REPEATED_COMPONENT_EXCEPTION.create(dataComponentType);
                }

                suggestor = this::suggestEqual;
                reader.skipWhitespace();
                reader.expect('=');
                suggestor = SUGGEST_DEFAULT;
                reader.skipWhitespace();
                this.readComponentValue(reader, builder, dataComponentType);
                reader.skipWhitespace();
                suggestor = this::suggestEndOfComponent;
                if (!reader.canRead() || reader.peek() != ',') {
                    break;
                }

                reader.skip();
                reader.skipWhitespace();
                suggestor = this::suggestComponentType;
                if (!reader.canRead()) {
                    throw COMPONENT_EXPECTED_EXCEPTION.createWithContext(reader);
                }
            }

            reader.expect(']');
            suggestor = SUGGEST_DEFAULT;

            return builder.build();
        }

        public static ComponentType<?> readComponentType(StringReader reader) throws CommandSyntaxException {
            if (!reader.canRead()) {
                throw COMPONENT_EXPECTED_EXCEPTION.createWithContext(reader);
            } else {
                int i = reader.getCursor();
                Identifier identifier = Identifier.fromCommandInput(reader);
                ComponentType<?> dataComponentType = Registries.DATA_COMPONENT_TYPE.get(identifier);
                if (dataComponentType != null && !dataComponentType.shouldSkipSerialization()) {
                    return dataComponentType;
                } else {
                    reader.setCursor(i);
                    throw UNKNOWN_COMPONENT_EXCEPTION.createWithContext(reader, identifier);
                }
            }
        }

        private CompletableFuture<Suggestions> suggestComponentType(SuggestionsBuilder builder) {
            String string = builder.getRemaining().toLowerCase(Locale.ROOT);
            CommandSource.forEachMatching(Registries.DATA_COMPONENT_TYPE.getEntrySet(), string, entry -> entry.getKey().getValue(), entry -> {
                ComponentType<?> dataComponentType = entry.getValue();
                if (dataComponentType.getCodec() != null) {
                    Identifier identifier = entry.getKey().getValue();
                    builder.suggest(identifier.toString() + "=");
                }
            });
            return builder.buildFuture();
        }

        private <T> void readComponentValue(StringReader reader, ComponentMap.Builder builder, ComponentType<T> type) throws CommandSyntaxException {
            int i = reader.getCursor();
            NbtElement nbtElement = new StringNbtReader(reader).parseElement();
            DataResult<T> dataResult = type.getCodecOrThrow().parse(this.nbtOps, nbtElement);
            builder.add(type, dataResult.getOrThrow(error -> {
                reader.setCursor(i);
                return MALFORMED_COMPONENT_EXCEPTION.createWithContext(reader, type.toString(), error);
            }));
        }

        private CompletableFuture<Suggestions> suggestBracket(SuggestionsBuilder builder) {
            if (builder.getRemaining().isEmpty()) {
                builder.suggest(String.valueOf('['));
            }

            return builder.buildFuture();
        }

        private CompletableFuture<Suggestions> suggestEndOfComponent(SuggestionsBuilder builder) {
            if (builder.getRemaining().isEmpty()) {
                builder.suggest(String.valueOf(','));
                builder.suggest(String.valueOf(']'));
            }

            return builder.buildFuture();
        }

        private CompletableFuture<Suggestions> suggestEqual(SuggestionsBuilder builder) {
            if (builder.getRemaining().isEmpty()) {
                builder.suggest(String.valueOf('='));
            }

            return builder.buildFuture();
        }
    }
}