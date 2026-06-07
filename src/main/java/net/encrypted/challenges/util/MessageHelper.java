package net.encrypted.challenges.util;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

public class MessageHelper {
    public static void sendSystemMessage(ServerPlayer player, Component message) {
        if (player == null) return;
        player.sendSystemMessage(message);
    }

    public static void sendSystemMessageOverlay(ServerPlayer player, Component message) {
        if (player == null) return;
        player.sendSystemMessage(message, true);
    }

    public static void broadcastChat(PlayerList playerManager, Component message) {
        for (var player : playerManager.getPlayers())
            sendSystemMessage(player, message);
    }

    public static void broadcastChatToPlayers(PlayerList playerManager, Component message) {
        for (var player : playerManager.getPlayers())
            sendSystemMessage(player, message);
    }

    public static void broadcastOverlay(PlayerList playerManager, Component message) {
        for (var player : playerManager.getPlayers())
            sendSystemMessageOverlay(player, message);
    }
}
