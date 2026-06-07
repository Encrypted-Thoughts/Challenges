package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.encrypted.challenges.game.GameStatus;
import net.encrypted.challenges.util.MessageHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.level.gamerules.GameRules;

import static net.encrypted.challenges.ChallengesManager.Status;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class ChallengesKeepInventoryCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal(ChallengesCommands.challengesCommand)
                        .then(literal("keepInventory")
                                .then(argument("enabled", BoolArgumentType.bool())
                                        .executes(ctx -> {
                                            var player = ctx.getSource().getPlayer();
                                            if (Status == GameStatus.Idle || ctx.getSource().permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)) {
                                                var bool = BoolArgumentType.getBool(ctx, "enabled");
                                                var server = ctx.getSource().getServer();
                                                ctx.getSource().getLevel().getGameRules().set(GameRules.KEEP_INVENTORY ,bool, server);
                                                MessageHelper.broadcastChat(server.getPlayerList(), Component.literal(bool ? "Keep Inventory set to enabled." : "Keep Inventory disabled.").withStyle(ChatFormatting.WHITE));
                                            } else
                                                MessageHelper.sendSystemMessage(player, Component.literal("Only Ops can change Keep Inventory status while game in progress.").withStyle(ChatFormatting.RED));
                                            return Command.SINGLE_SUCCESS;
                                        }))));
    }
}
