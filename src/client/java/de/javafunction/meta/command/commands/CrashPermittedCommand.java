package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class CrashPermittedCommand extends Command {
    public CrashPermittedCommand() {
        super("percrash", "percrash", "percrash", "permitted crash", null);
    }

    @Override
    public void onCommand(List<String> args) {
        for (int i = 0; i < 20; i++) {
            ChatUtil.ClientMessageNP("§4§lYou are not permitted to interact with §cUNKNOWN_ITEM");
        }
        MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
    }
}
