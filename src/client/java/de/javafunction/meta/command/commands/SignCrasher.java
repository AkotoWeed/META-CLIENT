package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.Arrays;
import java.util.List;

public class SignCrasher extends Command {
    public SignCrasher() {
        super("MainThread Crasher", "signcrash", "mainthread", "Crashing Server", Arrays.asList("mainthread"));
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
        ChatUtil.ClientMessage("§6Started Sign crasher");
    }
}
