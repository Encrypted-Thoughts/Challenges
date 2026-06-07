package net.encrypted.challenges.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ChallengesCommands {
    public static String challengesCommand = "challenges";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection ignoredRegistrationEnvironment) {
        ChallengesSettingsCommand.register(dispatcher, commandRegistryAccess);
        ChallengesKeepInventoryCommand.register(dispatcher);
        ChallengesEndCommand.register(dispatcher);
        ChallengesStartCommand.register(dispatcher);
    }
}
