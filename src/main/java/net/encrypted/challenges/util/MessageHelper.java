package net.encrypted.challenges.util;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MessageHelper {
    public static void sendSystemMessage(ServerPlayerEntity player, Text message) {
        if (player == null) return;
        player.sendMessage(message);
    }

    public static void sendSystemMessageOverlay(ServerPlayerEntity player, Text message) {
        if (player == null) return;
        player.sendMessage(message, true);
    }

    public static void broadcastChat(PlayerManager playerManager, Text message) {
        for (var player : playerManager.getPlayerList())
            sendSystemMessage(player, message);
    }

    public static void broadcastChatToPlayers(PlayerManager playerManager, Text message) {
        for (var player : playerManager.getPlayerList())
            sendSystemMessage(player, message);
    }

    public static void broadcastOverlay(PlayerManager playerManager, Text message) {
        for (var player : playerManager.getPlayerList())
            sendSystemMessageOverlay(player, message);
    }
}
