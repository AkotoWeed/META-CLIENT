package de.javafunction.meta.command.commands;


import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class CopyServerCommand extends Command {
    public CopyServerCommand() {
        super("copyserver cmd", "copyserver", "copyserver", "copy all server infos",null);
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient.getInstance().keyboard.setClipboard("IP: " + MinecraftClient.getInstance().getCurrentServerEntry().address + ", VERSION: " + MinecraftClient.getInstance().getCurrentServerEntry().version.getString() + ", BRAND: " + MinecraftClient.getInstance().getNetworkHandler().getBrand().toString() + ", PING: " + MinecraftClient.getInstance().getCurrentServerEntry().ping);
        ChatUtil.ClientMessage("§6all server infos has been successfully copied to your Clipboard!");
    }
}
