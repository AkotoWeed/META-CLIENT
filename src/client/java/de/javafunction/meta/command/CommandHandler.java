package de.javafunction.meta.command;

import de.javafunction.meta.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    public List<Command> commands = new ArrayList<Command>();
    public void onCommand(String cmd, List<String> args){
        List<String> nargs = new ArrayList<String>();
        for(String arg : args)
            nargs.add(arg.replace("|&|", "§"));

        Boolean pass = false;

        String incmd = cmd.substring(1).toLowerCase();
        for(Command command : commands){
            if(command.cmd.toLowerCase().startsWith(incmd)&& command.cmd.endsWith(incmd) ) {
                command.onCommand(nargs);
                pass = true;
                break;
            }else{
                if(command.aliases != null){
                    if(command.aliases.contains(incmd)){
                        command.onCommand(nargs);
                        pass = true;
                        break;
                    }
                }
            }
        }


        if(!pass){
            ChatUtil.ClientMessage("§cInvalid Command!");
        }
    }
}