package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

import static net.encrypted.challenges.ChallengesManager.start;
import static net.minecraft.commands.Commands.literal;

public class ChallengesStartCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal(ChallengesCommands.challengesCommand)
                        .then(literal("start")
                                .executes(ctx -> {
                                    start(ctx.getSource().getPlayer());
                                    return Command.SINGLE_SUCCESS;
                                })));
    }
}
