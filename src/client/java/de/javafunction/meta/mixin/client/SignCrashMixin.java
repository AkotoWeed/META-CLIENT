package de.javafunction.meta.mixin.client;

import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public class SignCrashMixin extends Screen {
    @Shadow private SignBlockEntity blockEntity;
    @Shadow private String[] messages;
    @Shadow private void finishEditing() {
    }
    private static final String TEXT = "{\"translate\":\"%2$s%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s\",\"with\":[\"\",{\"translate\":\"%2$s%2$s%2$s%2$s\", \"a\"]}]}]}]}]}]}]}]}";

    protected SignCrashMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("CRASH"), (button) -> {
            messages[0] = "";
            messages[1] = "";
            messages[2] = "";
            messages[3] = "";
            finishEditing();
            //ChatUtil.ClientMessage("§6Starting SignCrash 1.8.X");
            MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
        }).dimensions(10, 10, 200, 20).build());
    }
}
