package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;

import java.util.List;

public class UpdaterCommand extends Command{
    public UpdaterCommand() {
        super("Updater", "update", "updating this client", "nigga", null);
    }

    @Override
    public void onCommand(List<String> args) {

    }
}
