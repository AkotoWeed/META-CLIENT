package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("helpcmd", "help", "help", "help", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§e§lHELP: §7help, test, copyip, credits, server, wecrash, copyserver, ign, reg, log, newreg, newlog, fakegm, graftrain, p2crash, percrash, instantcrash, signcrash, nullping");
        ChatUtil.ClientMessageNP("");
    }
}
