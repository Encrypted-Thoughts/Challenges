package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.encrypted.challenges.game.GameStatus;
import net.encrypted.challenges.util.MessageHelper;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;

import static net.encrypted.challenges.ChallengesManager.Status;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ChallengesKeepInventoryCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal(ChallengesCommands.challengesCommand)
                        .then(literal("keepInventory")
                                .then(argument("enabled", BoolArgumentType.bool())
                                        .executes(ctx -> {
                                            var player = ctx.getSource().getPlayer();
                                            if (Status == GameStatus.Idle || ctx.getSource().hasPermissionLevel(2)) {
                                                var bool = BoolArgumentType.getBool(ctx, "enabled");
                                                var server = ctx.getSource().getServer();
                                                server.getGameRules().get(GameRules.KEEP_INVENTORY).set(bool, server);
                                                MessageHelper.broadcastChat(server.getPlayerManager(), Text.literal(bool ? "Keep Inventory set to enabled." : "Keep Inventory disabled.").formatted(Formatting.WHITE));
                                            } else
                                                MessageHelper.sendSystemMessage(player, Text.literal("Only Ops can change Keep Inventory status while game in progress.").formatted(Formatting.RED));
                                            return Command.SINGLE_SUCCESS;
                                        }))));
    }
}
