package de.javafunction.meta.mixin.client;

import de.javafunction.meta.MetaClient;
import de.javafunction.meta.imgui.ImGuiImpl;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static imgui.flag.ImGuiCol.*;
import static imgui.flag.ImGuiCol.TextSelectedBg;

@Mixin(MinecraftClient.class)
public class MetaMixin {
    @Shadow
    @Final
    private Window window;
    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    private String setTitle(String original) {
        if (MinecraftClient.getInstance().getWindow() != null) {
            try {
                String sip = "";
                if(MinecraftClient.getInstance().getCurrentServerEntry() != null && MinecraftClient.getInstance().getCurrentServerEntry().address != null){
                    sip = MinecraftClient.getInstance().getCurrentServerEntry().address;
                }else{
                    sip = "DISCONNECTED";
                }
                return ("Meta" + " " + MetaClient.getInstance().Version + " logged in as -> " + MinecraftClient.getInstance().getSession().getUsername() + " | " + sip);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return original;

    }
    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(RunArgs args, CallbackInfo ci) {
        ImGuiImpl.create(window.getHandle());
        ImGui.getStyle().setColor(Text, 0.93f, 0.32f, 0.42f, 1.00f);
        ImGui.getStyle().setColor(TextDisabled, 0.58f, 0.35f, 0.35f, 1.00f);
        ImGui.getStyle().setColor(PopupBg, 1.00f, 1.00f, 1.00f, 0.94f);
        ImGui.getStyle().setColor(WindowBg, 0.01f, 0.04f, 0.18f, 0.94f);
        ImGui.getStyle().setColor(Border, 0.00f, 0.00f, 0.00f, 0.39f);
        ImGui.getStyle().setColor(BorderShadow, 1.00f, 1.00f, 1.00f, 0.10f);
        ImGui.getStyle().setColor(FrameBg, 0.19f, 0.18f, 0.18f, 0.94f);
        ImGui.getStyle().setColor(FrameBgHovered, 0.26f, 0.59f, 0.98f, 0.67f);
        ImGui.getStyle().setColor(FrameBgActive, 0.26f, 0.59f, 0.98f, 0.40f);
        ImGui.getStyle().setColor(TitleBg, 0.13f, 0.12f, 0.12f, 0.96f);
        ImGui.getStyle().setColor(TitleBgCollapsed, 0.33f, 0.32f, 0.32f, 1.00f);
        ImGui.getStyle().setColor(TitleBgActive, 0.13f, 0.12f, 0.12f, 0.96f);
        ImGui.getStyle().setColor(MenuBarBg, 0.86f, 0.86f, 0.86f, 1.00f);
        ImGui.getStyle().setColor(ScrollbarBg, 0.31f, 0.31f, 0.31f, 0.53f);
        ImGui.getStyle().setColor(ScrollbarGrab, 0.69f, 0.69f, 0.69f, 1.00f);
        ImGui.getStyle().setColor(ScrollbarGrabHovered, 0.59f, 0.59f, 0.59f, 1.00f);
        ImGui.getStyle().setColor(ScrollbarGrabActive, 0.49f, 0.49f, 0.49f, 1.00f);
        ImGui.getStyle().setColor(CheckMark, 0.26f, 0.59f, 0.98f, 1.00f);
        ImGui.getStyle().setColor(SliderGrab, 0.24f, 0.52f, 0.88f, 1.00f);
        ImGui.getStyle().setColor(SliderGrabActive, 0.26f, 0.59f, 0.98f, 1.00f);
        ImGui.getStyle().setColor(Button, 0.26f, 0.59f, 0.98f, 0.40f);
        ImGui.getStyle().setColor(ButtonHovered, 0.26f, 0.59f, 0.98f, 1.00f);
        ImGui.getStyle().setColor(ButtonActive, 0.06f, 0.53f, 0.98f, 1.00f);
        ImGui.getStyle().setColor(Header, 0.26f, 0.59f, 0.98f, 0.31f);
        ImGui.getStyle().setColor(HeaderHovered, 0.26f, 0.59f, 0.98f, 0.80f);
        ImGui.getStyle().setColor(HeaderActive, 0.26f, 0.59f, 0.98f, 1.00f);
        ImGui.getStyle().setColor(ResizeGrip, 1.00f, 1.00f, 1.00f, 0.50f);
        ImGui.getStyle().setColor(ResizeGripHovered, 0.26f, 0.59f, 0.98f, 0.67f);
        ImGui.getStyle().setColor(ResizeGripActive, 0.26f, 0.59f, 0.98f, 0.95f);
        ImGui.getStyle().setColor(PlotLines, 0.39f, 0.39f, 0.39f, 1.00f);
        ImGui.getStyle().setColor(PlotLinesHovered, 1.00f, 0.43f, 0.35f, 1.00f);
        ImGui.getStyle().setColor(PlotHistogram, 0.90f, 0.70f, 0.00f, 1.00f);
        ImGui.getStyle().setColor(PlotHistogramHovered, 1.00f, 0.60f, 0.00f, 1.00f);
        ImGui.getStyle().setColor(TextSelectedBg, 0.26f, 0.59f, 0.98f, 0.35f);
    }

}
