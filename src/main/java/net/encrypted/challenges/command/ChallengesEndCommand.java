package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.permissions.Permissions;

import static net.encrypted.challenges.ChallengesManager.end;
import static net.minecraft.commands.Commands.literal;

public class ChallengesEndCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal(ChallengesCommands.challengesCommand)
                .then(literal("end")
                        .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                        .executes(ctx -> {
                            end();
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}
