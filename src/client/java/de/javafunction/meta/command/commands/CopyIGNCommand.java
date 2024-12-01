package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class CopyIGNCommand extends Command {
    public CopyIGNCommand() {
        super("Copy IGN", "ign", "ign", "Copy your IngameName!", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient.getInstance().keyboard.setClipboard(MinecraftClient.getInstance().player.getName().getString());
        ChatUtil.ClientMessage("§6Your IGN has been successfully copied to your Clipboard!");
    }
}
