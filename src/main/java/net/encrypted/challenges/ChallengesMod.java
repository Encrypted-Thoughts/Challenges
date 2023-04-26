package net.encrypted.challenges;

import net.encrypted.challenges.config.ChallengesConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChallengesMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("challenges");
	public static ChallengesConfig CONFIG = new ChallengesConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized Challenges");

		if (!CONFIG.ReadFromFile()) {
			ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
				for (var world : server.getWorlds())
					CONFIG.ChallengeDimensions.add(world.getRegistryKey().getValue().toString());
				CONFIG.SaveToFile();
			});
		}
	}
}
