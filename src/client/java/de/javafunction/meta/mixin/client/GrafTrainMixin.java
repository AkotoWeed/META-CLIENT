package de.javafunction.meta.mixin.client;

import de.javafunction.meta.MetaClient;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public class GrafTrainMixin extends Screen {
    @Shadow
    private SignBlockEntity blockEntity;
    @Shadow private String[] messages;
    @Shadow private void finishEditing() {
    }
    protected GrafTrainMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("GrafTrain beschwören"), (button) -> {
            MetaClient.getInstance().hud.GrafTrain = true;
            MetaClient.getInstance().hud.GrafTrain = true;
            MetaClient.getInstance().hud.GrafTrain = true;
            MetaClient.getInstance().hud.GrafTrain = true;
            MetaClient.getInstance().hud.GrafTrain = true;
            MetaClient.getInstance().hud.GrafTrain = true;
        }).dimensions(10 * 100, 10, 200, 20).build());
        }
}

