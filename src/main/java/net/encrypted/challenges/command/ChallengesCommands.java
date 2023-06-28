package net.encrypted.challenges.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ChallengesCommands {
    public static String challengesCommand = "challenges";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment ignoredRegistrationEnvironment) {
        ChallengesSettingsCommand.register(dispatcher, commandRegistryAccess);
        ChallengesPVPCommand.register(dispatcher);
        ChallengesKeepInventoryCommand.register(dispatcher);
        ChallengesEndCommand.register(dispatcher);
        ChallengesStartCommand.register(dispatcher);
    }
}
