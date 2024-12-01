package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class LoginCommand extends Command {
    public LoginCommand() {
        super("login", "log", "log", "Login on Cracked Server", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Logged in!...");
        ChatUtil.Command("login !$ashhisnyk123");
    }
}
