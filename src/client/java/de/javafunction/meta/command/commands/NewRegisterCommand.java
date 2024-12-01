package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class NewRegisterCommand extends Command {
    public NewRegisterCommand() {
        super("new register ccommand", "newreg", "newreg", "new register cmd", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Registering!");
        ChatUtil.Command("register HOIUAH&(//$$a12384 HOIUAH&(//$$a12384");
    }
}
