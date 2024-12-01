package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class CopyIPCommand extends Command {
    public CopyIPCommand() {
        super("COPYIP", "copyip", "copyip", "copy ip from server", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient.getInstance().keyboard.setClipboard(MinecraftClient.getInstance().getCurrentServerEntry().address);
        ChatUtil.ClientMessage("§6IP has been successfully copied!");
    }
}
