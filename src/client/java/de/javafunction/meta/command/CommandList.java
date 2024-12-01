package de.javafunction.meta.command;

import com.mojang.datafixers.Products;
import de.javafunction.meta.MetaClient;
import de.javafunction.meta.command.commands.*;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import javax.security.auth.login.LoginContext;

public class CommandList {
    public static void registerCommand(Command command) {
        CommandHandler cmd = MetaClient.getInstance().cmd;
        cmd.commands.add(command);
        command.onInitialize();
    }
    public static void registerCommands(){
    registerCommand(new TestCommand());
    registerCommand(new HelpCommand());
    registerCommand(new CopyIPCommand());
    registerCommand(new CreditsCommand());
    registerCommand(new WorldEditCrashCommand());
    registerCommand(new ServerInfoCommand());
    registerCommand(new CopyServerCommand());
    registerCommand(new CopyIGNCommand());
    registerCommand(new RegisterCommand());
    registerCommand(new LoginCommand());
    registerCommand(new MainCrashCommand());
    registerCommand(new NewLoginCommand());
    registerCommand(new NewRegisterCommand());
    registerCommand(new SignCrasher());
    registerCommand(new FakeGamemodeCommand());
    registerCommand(new GrafTrainCommand());
    registerCommand(new SpalteExpressCommand());
    registerCommand(new CrashPermittedCommand());
    registerCommand(new P2CrashCommand());
    registerCommand(new InstantcrashCommand());
    registerCommand(new CheckCMDCheckCommand());
    registerCommand(new PlayerPositionCommand());
    registerCommand(new SpamPacketsCommand());
    registerCommand(new NewCrashCommand());
    }
}
