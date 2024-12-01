package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class ServerInfoCommand extends Command {
    public ServerInfoCommand() {
        super("ServerInfoCMD", "server", "server", "Display the Server Information!", null);
    }

    @Override
    public void onCommand(List<String> args) {
        if(MinecraftClient.getInstance().getCurrentServerEntry() !=null) {
            ChatUtil.ClientMessage("§4Getting Serverinfo!");
            ChatUtil.ClientMessage("§4IP §8» §7" + MinecraftClient.getInstance().getCurrentServerEntry().address);
            ChatUtil.ClientMessage("§4VERSION §8» §7" + MinecraftClient.getInstance().getCurrentServerEntry().version.getString());
            ChatUtil.ClientMessage("§4BRAND §8» §7" + MinecraftClient.getInstance().getNetworkHandler().getBrand());
            ChatUtil.ClientMessage("§4PING §8» §7" + MinecraftClient.getInstance().getCurrentServerEntry().ping);
            ChatUtil.ClientMessage("§4to copy all informations type #copyserver and to copy the server address type #copyip");
        }else{
            ChatUtil.ClientMessage("§4Getting Serverinfo!");
            ChatUtil.ClientMessage("§4IP §8» §7 localhost // 127.0.0.1");
            ChatUtil.ClientMessage("§4VERSION §8» §71.20.4");
            ChatUtil.ClientMessage("§4BRAND §8» §7Wer das liest ist doof");
            ChatUtil.ClientMessage("§4PING §8» §70");
        }
    }
}
