package de.javafunction.meta.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.DirectConnectScreen;
import net.minecraft.client.gui.screen.world.WorldIcon;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(DirectConnectScreen.class)
public class DirectConnectMixin {
    int r = 255;
    int g = 0;
    int b = 0;
    @Shadow
    private TextFieldWidget addressField;

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // this.width / 2 - 100, 116, 200, 20
        int back = new Color(0, 0, 0, 255).getRGB();
        int side = new Color(r, g, b, 255).getRGB();
        // context.setShaderColor(255, 255, 255, 255);
        ServerInfo inf = new ServerInfo("", addressField.getText(), ServerInfo.ServerType.OTHER);
        WorldIcon ico = WorldIcon.forServer(MinecraftClient.getInstance().getTextureManager(), addressField.getText());
        try {
            ico.load(NativeImage.read(inf.getFavicon()));
        } catch (Exception ex) {
        }
        //WorldIcon ico = WorldIcon.forServer(MinecraftClient.getInstance().getTextureManager(), );
        System.out.println(ico.getTextureId().toString());

        if (r > 0 && b == 0) {
            r--;
            g++;
        }
        if (g > 0 && r == 0) {
            g--;
            b++;
        }
        if (b > 0 && g == 0) {
            b--;
            r++;
        }

        int width = context.getScaledWindowWidth();
        int final_height = context.getScaledWindowHeight();

        context.fill(width / 2 - 100, 50, width / 2 + 100, 50 + 40, back);
        context.drawHorizontalLine(width / 2 - 100, width / 2 + 100 - 1, 50, side);
        context.drawHorizontalLine(width / 2 - 100, width / 2 + 100 - 1, 50 + 40, side);
        context.drawVerticalLine(width / 2 - 100, 50, 50 + 40, side);
        context.drawVerticalLine(width / 2 + 100 - 1, 50, 50 + 40, side);
        RenderSystem.enableBlend();
        context.drawTexture(ico.getTextureId(), width / 2 - 100 + 1, 50 + 1, 0, 0, 40 - 1, 40 - 1);
        RenderSystem.disableBlend();
    }
}