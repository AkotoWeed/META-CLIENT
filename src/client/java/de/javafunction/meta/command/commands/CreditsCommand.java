package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class CreditsCommand extends Command {
    public CreditsCommand() {
        super("Credits", "credits", "credits", "credits", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Meta ClientBase made by JavaFunction alias PublicCode");
        ChatUtil.ClientMessage("§2Github §7» §3PublicCode1337");
        ChatUtil.ClientMessage("§4YouTube §7» /@JavaFunction");
    }
}
