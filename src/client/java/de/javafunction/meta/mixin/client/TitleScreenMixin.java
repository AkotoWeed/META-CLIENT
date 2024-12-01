package de.javafunction.meta.mixin.client;

import de.javafunction.meta.Meta;
import de.javafunction.meta.MetaClient;
import de.javafunction.meta.gui.LoginScreen;
import de.javafunction.meta.minigame.KrbecMinigameEngine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }
    @Inject(method = "render", at = @At("HEAD"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if(!MetaClient.getInstance().globals.login) {
            MinecraftClient.getInstance().setScreen(new LoginScreen(Text.literal("Login")));
        }
    }
}
*/

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    private final KrbecMinigameEngine minigameEngine = new KrbecMinigameEngine();

    @Inject(method = "render", at = @At("TAIL"))
    public void renderMixin(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        minigameEngine.renderMinigame(context, mouseX, mouseY, delta);
    }
}