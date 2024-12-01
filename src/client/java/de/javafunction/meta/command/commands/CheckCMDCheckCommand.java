package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.UpdateCommandBlockC2SPacket;

import java.util.List;

public class CheckCMDCheckCommand extends Command{
    public CheckCMDCheckCommand() {
        super("checkcmd", "checkcmd", "checkcmd", "check cmd block", null);
    }

    @Override
    public void onCommand(List<String> args) {
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(new UpdateCommandBlockC2SPacket(MinecraftClient.getInstance().player.getBlockPos(), "" , CommandBlockBlockEntity.Type.AUTO, false, false, false));
        ChatUtil.ClientMessage("Copied your Pos!");
    }
}
