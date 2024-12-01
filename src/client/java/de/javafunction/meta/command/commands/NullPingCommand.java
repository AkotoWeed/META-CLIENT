package de.javafunction.meta.command.commands;
import de.javafunction.meta.command.Command;

import java.util.List;

public class NullPingCommand extends Command{
    public NullPingCommand() {
        super("nullping", "Nullping", "nullping start/stop", "nullping", null);
    }

    @Override
    public void onCommand(List<String> args) {
        
    }
}
