package de.javafunction.meta.minigame;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class KrbecMinigameEngine implements ClientModInitializer {
    public static List<Identifier> GRAF_OPTIONS = Arrays.asList(
            Identifier.of("meta:textures/minigame/augen.png"),
            Identifier.of("meta:textures/minigame/grafanwesen.png"),
            Identifier.of("meta:textures/minigame/grafbre.png"),
            Identifier.of("meta:textures/minigame/zirismad.png"),
            Identifier.of("meta:textures/minigame/melon.png"),
            Identifier.of("meta:textures/minigame/melonuwu.png"),
            Identifier.of("meta:textures/minigame/zirishs.png"),
            Identifier.of("meta:textures/minigame/zirishs2.png"),
            Identifier.of("meta:textures/minigame/spalte1.png"),
            Identifier.of("meta:textures/minigame/spalte2.png"),
            Identifier.of("meta:textures/minigame/spaltemalte.png"),
            Identifier.of("meta:textures/minigame/dalte.png"),
            Identifier.of("meta:textures/minigame/malterar.png"),
            Identifier.of("meta:textures/minigame/blocher1.png"),
            Identifier.of("meta:textures/minigame/blocher2.png"),
            Identifier.of("meta:textures/minigame/blocher3.png"),
            Identifier.of("meta:textures/minigame/blocher4.png"),
            Identifier.of("meta:textures/minigame/blocher5.png")

    );

    private final Identifier TRASH = Identifier.of("meta:textures/minigame/emptytrash.png");

    private final List<KrbecEntry> entries = new CopyOnWriteArrayList<>();
    private final NzxterUtil timerTaskDefault = new NzxterUtil();
    private int trashX;

    @Override
    public void onInitializeClient() {
        // Keine spezielle Initialisierung erforderlich
    }

    public void renderMinigame(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        // Rendern der Einträge
        entries.forEach(e -> {
            e.render(context);
            if (e.getY() >= height - 160 && e.getX() >= trashX && e.getX() <= trashX + 50) {
                entries.remove(e);
            }
        });

        // Position des Trash-Icons aktualisieren und rendern
        trashX = mouseX - 25;
        TextureManager textureManager = client.getTextureManager();
        textureManager.bindTexture(TRASH);
        context.drawTexture(TRASH, trashX, height - 80, 0, 0, 80, 80, 80, 80);

        // Hinzufügen neuer Einträge in regelmäßigen Abständen
        if (timerTaskDefault.hasTimeReached(1000L)) {
            entries.add(new KrbecEntry(new Random().nextInt(width - 9),
                    GRAF_OPTIONS.get(new Random().nextInt(GRAF_OPTIONS.size()))));
            timerTaskDefault.reset();
        }
    }

    public static class KrbecEntry {
        private final int x;
        private int y;
        private final Identifier identifier;

        public KrbecEntry(int x, Identifier identifier) {
            this.x = x;
            this.identifier = identifier;
        }

        public void render(DrawContext context) {
            y++;
            MinecraftClient.getInstance().getTextureManager().bindTexture(identifier);
            context.drawTexture(identifier, getX(), getY(), 0, 0, 80, 80, 80, 80);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Identifier getIdentifier() {
            return identifier;
        }
    }
}
