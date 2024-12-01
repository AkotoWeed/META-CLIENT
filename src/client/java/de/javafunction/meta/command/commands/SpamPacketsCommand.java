package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.List;

public class SpamPacketsCommand extends Command {
    public SpamPacketsCommand() {
        super("spampackets", "spam", "spam", "spam packets", null);
    }


    @Override
    public void onCommand(List<String> args) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.getNetworkHandler() != null) {
            int packetCount = 10000;
            for (int i = 0; i < packetCount; i++) {
                client.getNetworkHandler().sendChatMessage("Spam packet " + i);
            }
            ChatUtil.ClientMessage("Sent " + packetCount + " packets.");
        } else {
            ChatUtil.ClientMessage("Player or NetworkHandler is null.");
        }
    }
}
