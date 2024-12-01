package de.javafunction.meta.command;

import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public abstract class Command {
    public String name;
    public String cmd;
    public String usage;
    public String desc;
    public List<String> aliases;
    public Command(String name, String cmd, String usage, String desc, List<String> aliases){
        this.name = name;
        this.cmd = cmd;
        this.usage = usage;
        this.desc = desc;
        this.aliases = aliases;
    }

    public void onInitialize(){}
    public abstract void onCommand(List<String> args);
    public void syntaxError() {
        ChatUtil.ClientMessage("§4Syntax: " + usage);
    }
}
