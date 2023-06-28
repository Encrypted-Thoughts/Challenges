package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import static net.encrypted.challenges.ChallengesManager.end;
import static net.minecraft.server.command.CommandManager.literal;

public class ChallengesEndCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal(ChallengesCommands.challengesCommand)
                .then(literal("end")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> {
                            end();
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}
