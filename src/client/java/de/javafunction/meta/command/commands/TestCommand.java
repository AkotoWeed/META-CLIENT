package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class TestCommand extends Command {
    public TestCommand() {
        super("Test Command", "test", "test", "testcmd", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§cTest bestanden");
    }
}
