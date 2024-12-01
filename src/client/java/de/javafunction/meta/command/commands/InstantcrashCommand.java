package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class InstantcrashCommand extends Command{
    public InstantcrashCommand() {
        super("instantcrash", "instantcrash", "instantcrash", "instantcrash", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Started Instantcrash");
        MinecraftClient.getInstance().getNetworkHandler().unloadWorld();
    }
}
