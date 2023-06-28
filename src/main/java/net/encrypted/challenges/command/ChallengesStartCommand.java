package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import static net.encrypted.challenges.ChallengesManager.start;
import static net.minecraft.server.command.CommandManager.literal;

public class ChallengesStartCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal(ChallengesCommands.challengesCommand)
                        .then(literal("start")
                                .executes(ctx -> {
                                    start(ctx.getSource().getPlayer());
                                    return Command.SINGLE_SUCCESS;
                                })));
    }
}
