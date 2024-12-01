package de.javafunction.meta.command.commands;

import de.javafunction.meta.MetaClient;
import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class GrafTrainCommand extends Command {
    public GrafTrainCommand() {
        super("Graftrain", "graftrain", "graftrain", "graftrain on pascal 10", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MetaClient.getInstance().hud.GrafTrain = true;
        ChatUtil.ClientMessage("§6Tutuuuuuuuuuuuut der Graftrain kommt !!");
    }
}
