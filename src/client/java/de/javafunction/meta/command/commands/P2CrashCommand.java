package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class P2CrashCommand extends Command {
    public P2CrashCommand() {
        super("p2crash", "p2crash", "p2crasher", "penis", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
        ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
        ChatUtil.ClientMessageNP("§8[§6P2§8] §cAn internal error occured while attempting to perform this command");
        MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
    }
}
