package de.javafunction.meta.command.commands;


import de.javafunction.meta.MetaClient;
import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class SpalteExpressCommand extends Command{
    public SpalteExpressCommand() {
        super("spalteexpress", "spalteexpress", "spalte", "spaltemalte", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MetaClient.getInstance().hud.SpalteExpress = true;
        ChatUtil.ClientMessage("§6Malte Spalte Express");
    }
}
