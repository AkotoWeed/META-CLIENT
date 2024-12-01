package de.javafunction.meta.command.commands;

import de.javafunction.meta.command.Command;
import de.javafunction.meta.utils.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewCrashCommand extends Command {

    private final Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public NewCrashCommand() {
        super("newcrash", "newcrash", "newcrash", "New Crash Command", null);
    }

    @Override
    public void onCommand(List<String> args) {
        ChatUtil.ClientMessage("§aSent packet with value §cREDACTED");

        int initialWaitTime = 3000 + random.nextInt(6001);

        scheduler.schedule(() -> {
            String fakeServerResponse = generateFakeServerResponse();
            ChatUtil.ClientMessage(fakeServerResponse);

            int responseWaitTime = 3000 + random.nextInt(6001);

            scheduler.schedule(() -> {
                ChatUtil.ClientMessage("§aResending packet with adjusted values from response...");

                int finalWaitTime = 3000 + random.nextInt(6001);

                scheduler.schedule(() -> {
                    MinecraftClient.getInstance().getNetworkHandler().unloadWorld();

                    // Schedule the announcePlayers command after 10 seconds
                    scheduler.schedule(() -> {
                        MinecraftServer server = MinecraftClient.getInstance().getServer();
                        if (server != null) {
                            Collection<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
                            for (ServerPlayerEntity player : players) {
                                String playerName = player.getName().getString();
                                Text leaveMessage = Text.literal(playerName + " has left the game!").formatted(Formatting.YELLOW);
                                for (ServerPlayerEntity p : players) {
                                    p.sendMessage(leaveMessage, false);
                                }
                                player.networkHandler.disconnect(leaveMessage);
                            }
                        }
                    }, 10, TimeUnit.SECONDS);

                }, finalWaitTime, TimeUnit.MILLISECONDS);

            }, responseWaitTime, TimeUnit.MILLISECONDS);

        }, initialWaitTime, TimeUnit.MILLISECONDS);
    }

    private String generateFakeServerResponse() {
        return "§7[Server]: returned: §cREDACTED";
    }
}
