package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameMode;

import java.util.List;

public class FakeGamemodeCommand extends Command {
    public FakeGamemodeCommand() {
        super("fakegm", "fakegm", "fakegm (0|1|2|3)", "fakegm", null);
    }

    @Override
    public void onCommand(List<String> args) {
        try {
            int igm = Integer.parseInt(args.get(0));
            GameMode gm = GameMode.SURVIVAL;
            switch (igm) {
                case 0:
                    gm = GameMode.SURVIVAL;
                    break;
                case 1:
                    gm = GameMode.CREATIVE;
                    break;
                case 2:
                    gm = GameMode.ADVENTURE;
                    break;
                case 3:
                    gm = GameMode.SPECTATOR;
                    break;
            }
            MinecraftClient.getInstance().interactionManager.setGameMode(gm);
            ChatUtil.ClientMessage("§6Changed fake gamemode to " + gm + "!");
        }catch (Exception ex){
        }

    }
}
