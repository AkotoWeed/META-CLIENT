package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class NewLoginCommand extends Command {
    public NewLoginCommand() {
        super("newlog", "newlog", "newlog", "newlog", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Logging in!");
        ChatUtil.Command("login HOIUAH&(//$$a12384");
    }
}
