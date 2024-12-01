package de.javafunction.meta.utils;

import de.javafunction.meta.MetaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtil {
    public static void ClientMessage(String message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(MetaClient.getInstance().ChatPrefix + message));
    }
    public static void PublicMessage(String content) {
        MinecraftClient.getInstance().getNetworkHandler().sendChatMessage(content);
    }

    public static void Command(String content) {
        MinecraftClient.getInstance().getNetworkHandler().sendChatCommand(content);
    }

    public static void ClientMessageNP(String message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(message));
    }
}
