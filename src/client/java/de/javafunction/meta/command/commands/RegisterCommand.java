package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("Register", "reg", "reg", "Register on Cracked Server", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Registering");
        ChatUtil.Command("register !$ashhisnyk123 !$ashhisnyk123");
    }
}
