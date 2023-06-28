package net.encrypted.challenges;

import net.encrypted.challenges.command.ChallengesCommands;
import net.encrypted.challenges.config.ChallengesConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.encrypted.challenges.ChallengesManager.runAfterRespawn;

public class ChallengesMod implements ModInitializer {
	public static final String MOD_ID = "challenges";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ChallengesConfig CONFIG = new ChallengesConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized Challenges");

		CONFIG.ReadFromFile();
		CommandRegistrationCallback.EVENT.register(ChallengesCommands::register);

		ServerTickEvents.START_SERVER_TICK.register(ChallengesManager::runOnServerTickEvent);
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> runAfterRespawn(newPlayer));
	}
}
