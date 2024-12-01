package de.javafunction.meta.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.text.DecimalFormat;


public class HUD implements HudRenderCallback {
    public boolean GrafTrain = false;
    public boolean SpalteExpress = false;

    public int GrafTrainx = 0 - 549 / 3;
    public int Spalteexpressx = 0 - 554 / 3;

    //static final Identifier logoTexture = new Identifier("meta:textures/meta2.png");
    static final Identifier logoTexture = Identifier.of("meta:textures/ethn.png");
    public static final Identifier grafTexture = Identifier.of("meta:textures/graftrain.png");
    public static final Identifier seTexture = Identifier.of("meta:texture/spalte.png");


    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int w = 433 / 4;
        int h = 492 / 4;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);

        Vec3d pos = MinecraftClient.getInstance().player.getPos();

        String x = df.format(pos.x).replace(',', '.');
        String y = df.format(pos.y).replace(',', '.');
        String z = df.format(pos.z).replace(',', '.');
        RenderSystem.setShaderTexture(0, logoTexture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        drawContext.drawTexture(logoTexture, 1, 1, 1, 1, w, h, w, h);
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1FPS§8] §9" + MinecraftClient.getInstance().getCurrentFps(), 2, h + 12, 0xffffff, false);
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1XYZ§8] §9" + x + " " + y + " " + z, 2, h + 22, 0xffffff, false);
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1IGN§8] §9" + MinecraftClient.getInstance().getSession().getUsername(), 2, h + 32, 0xffffff, false);
        if(MinecraftClient.getInstance().getCurrentServerEntry() !=null) {
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Address§8] §9" + MinecraftClient.getInstance().getCurrentServerEntry().address, 2, h + 42, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Brand§8] §9" + MinecraftClient.getInstance().getNetworkHandler().getBrand(), 2, h + 52, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Version§8] §9" + MinecraftClient.getInstance().getCurrentServerEntry().protocolVersion + " / " + MinecraftClient.getInstance().getCurrentServerEntry().version.getString() , 2, h + 62, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Ping§8] §9" + MinecraftClient.getInstance().getCurrentServerEntry().ping + " §9ms", 2, h + 72, 0xffffff, false);
        } else {
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Address§8] §9localhost // 127.0.0.1", 2, h + 42, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Brand§8] §9PascalCord v.666", 2, h + 52, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Server Version§8] §91.21", 2, h + 62, 0xffffff, false);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "§8[§1Ping§8] §90ms", 2, h + 72, 0xffffff, false);
        }
        if(GrafTrain) {
            RenderSystem.setShaderTexture(0, grafTexture);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawContext.drawTexture(grafTexture, GrafTrainx, 50, 1, 1, 549 / 3, 100 / 3, 549 / 3, 100 / 3);
            if (GrafTrainx > drawContext.getScaledWindowWidth()) {
                GrafTrain = false;
                GrafTrainx = 0 - 549 / 3;
            } else
                GrafTrainx++;
        }
        if(SpalteExpress) {
            RenderSystem.setShaderTexture(0, seTexture);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawContext.drawTexture(seTexture, Spalteexpressx, 50, 1, 1, 554 / 3, 108 / 3, 554 / 3, 108 / 3);
            if (Spalteexpressx > drawContext.getScaledWindowWidth()) {
                SpalteExpress = false;
                Spalteexpressx = 0 - 554 / 3;
            } else
                Spalteexpressx++;
        }
    }
}

