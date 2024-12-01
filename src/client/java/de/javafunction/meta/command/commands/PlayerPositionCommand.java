package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class PlayerPositionCommand extends Command {
    public PlayerPositionCommand() {
        super("playerpos", "copypos", "playerpos", "Displays the player's current position", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            BlockPos pos = client.player.getBlockPos();
            MinecraftClient.getInstance().keyboard.setClipboard("/tp " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
        }
    }
}