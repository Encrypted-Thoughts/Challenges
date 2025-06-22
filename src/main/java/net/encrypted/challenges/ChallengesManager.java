package net.encrypted.challenges;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.encrypted.challenges.game.ChallengeCategory;
import net.encrypted.challenges.game.GameStatus;
import net.encrypted.challenges.game.StartingItem;
import net.encrypted.challenges.game.StatusEffect;
import net.encrypted.challenges.util.MessageHelper;
import net.encrypted.challenges.util.PlayerHelper;
import net.encrypted.challenges.util.TeleportHelper;
import net.encrypted.challenges.util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameMode;
import net.minecraft.world.biome.BiomeKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class ChallengesManager {
	public static MinecraftServer Server;
	public static GameStatus Status = GameStatus.Idle;
	public static String Dimension = ChallengesMod.CONFIG.DefaultChallengeDimension;
	public static int YSpawnOffset = 50;
	public static int MaxYLevel = 200;
	public static int PlayAreaRadius = 1000000;
	public static int TimeLimit = 20;
	public static int TPRandomizationRadius = 2000;

	public static String Challenge;
	public static ChallengeCategory Category;

	public static ArrayList<StatusEffect> Effects = new ArrayList<>(List.of(
		new StatusEffect("minecraft:slow_falling", 20, 6, true),
		new StatusEffect("minecraft:regeneration", 20, 100, true),
		new StatusEffect("minecraft:fire_resistance", 20, 100, true)
	));
	public static ArrayList<StartingItem> StartingGear = new ArrayList<>();

	public static BlockPos GameSpawn;
	public static long CountDownStart;
	public static long CurrentCountdownSecond;

	public static boolean TimerRunning = false;
	public static long TimerStart;
	public static long CurrentTimerSecond;

	public static void start(ServerPlayerEntity starter) {
		Server = starter.getServer();
		if (Server == null) return;

		if (Status != GameStatus.Idle) {
			MessageHelper.sendSystemMessage(starter, Text.literal("Challenge already in progress.").formatted(Formatting.RED));
			return;
		}

		if (Category != ChallengeCategory.Inventory) {
			for (var player : Server.getPlayerManager().getPlayerList())
			{
				var id = Identifier.of(Challenge);
				switch (Category) {
					case Miscellaneous -> {
						if (Stats.CUSTOM.hasStat(id))
							player.resetStat(Stats.CUSTOM.getOrCreateStat(id));
					}
					case Mined -> {
						var block = Block.getBlockFromItem(Registries.ITEM.get(id));
						if (Stats.MINED.hasStat(block))
							player.resetStat(Stats.MINED.getOrCreateStat(block));
					}
					case Crafted -> {
						var item = Registries.ITEM.get(id);
						if (Stats.CRAFTED.hasStat(item))
							player.resetStat(Stats.CRAFTED.getOrCreateStat(item));
					}
					case Used -> {
						var item = Registries.ITEM.get(id);
						if (Stats.USED.hasStat(item))
							player.resetStat(Stats.USED.getOrCreateStat(item));
					}
					case Broken -> {
						var item = Registries.ITEM.get(id);
						if (Stats.BROKEN.hasStat(item))
							player.resetStat(Stats.BROKEN.getOrCreateStat(item));
					}
					case Picked_Up -> {
						var item = Registries.ITEM.get(id);
						if (Stats.PICKED_UP.hasStat(item))
							player.resetStat(Stats.PICKED_UP.getOrCreateStat(item));
					}
					case Dropped -> {
						var item = Registries.ITEM.get(id);
						if (Stats.DROPPED.hasStat(item))
							player.resetStat(Stats.DROPPED.getOrCreateStat(item));
					}
					case Killed -> {
						var entity = Registries.ENTITY_TYPE.get(id);
						if (Stats.KILLED.hasStat(entity))
							player.resetStat(Stats.KILLED.getOrCreateStat(entity));
					}
					case Killed_By -> {
						var entity = Registries.ENTITY_TYPE.get(id);
						if (Stats.KILLED_BY.hasStat(entity))
							player.resetStat(Stats.KILLED_BY.getOrCreateStat(entity));
					}
				}
			}
		}

		Status = GameStatus.Loading;
		var text = Text.literal("Challenge starting!").formatted(Formatting.GREEN);
		MessageHelper.broadcastChatToPlayers(Server.getPlayerManager(), text);

		var world = WorldHelper.getWorldByName(Server, Dimension);

		if (world == null) {
			ChallengesMod.LOGGER.error("Unable to initialize game. World is null.");
			return;
		}

		var origin = getRandomPos(
				world,
				-PlayAreaRadius + TPRandomizationRadius,
				PlayAreaRadius - TPRandomizationRadius,
				-PlayAreaRadius + TPRandomizationRadius,
				PlayAreaRadius - TPRandomizationRadius
		);

		CompletableFuture.runAsync(() -> {
			GameSpawn = findSpawn(
					world,
					new Vec2f(origin.getX(), origin.getZ()),
					TPRandomizationRadius,
					MaxYLevel);

			if (GameSpawn == null){
				MessageHelper.broadcastChatToPlayers(Server.getPlayerManager(), Text.literal("Unable to find spawn. Ending game.").formatted(Formatting.RED));
				end();
			}

			for (var player : Server.getPlayerManager().getPlayerList()) {
				player.getInventory().clear();
				player.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST));
			}
			Status = GameStatus.Initializing;
		}).exceptionally(ex -> {
			MessageHelper.broadcastChatToPlayers(Server.getPlayerManager(), Text.literal("Problem finding spawn."));
			ChallengesMod.LOGGER.error(ex.getMessage());
			return null;
		});
	}

	// end
	public static void end() {
		Status = GameStatus.Idle;
		TimerRunning = false;
		teleportAllToHub();
	}

	public static void handleCountdown() {
		var elapsedSeconds = (System.currentTimeMillis() - CountDownStart) / 1000;

		if (CurrentCountdownSecond < elapsedSeconds) {
			CurrentCountdownSecond = elapsedSeconds;
			var text = Text.literal("%s".formatted(30 - elapsedSeconds)).formatted(Formatting.GOLD);
			MessageHelper.broadcastOverlay(Server.getPlayerManager(), text);

			if (elapsedSeconds >= 30) {
				for (var player : Server.getPlayerManager().getPlayerList()) {
					player.setMovementSpeed(1);
					player.setNoGravity(false);
					givePlayerEquipment(player, false);
					givePlayerStatusEffects(player, false);
				}

				Status = GameStatus.Playing;

				if (TimeLimit > 0) {
					TimerRunning = true;
					TimerStart = System.currentTimeMillis();
					CurrentTimerSecond = 0;
				}
			}
		}
	}

	public static void handleTimer() {
		var elapsedSeconds = (System.currentTimeMillis() - TimerStart) / 1000;

		if (CurrentTimerSecond < elapsedSeconds) {
			CurrentTimerSecond = elapsedSeconds;
			var remaining = TimeLimit * 60L - elapsedSeconds;

			var hours = remaining / 3600;
			var minutes = remaining / 60;
			var seconds = remaining % 60;

			var hourText = hours == 0 ? "" : hours + ":";
			var minuteText = minutes < 10 && hours > 0 ? "0" + minutes + ":" : minutes + ":";
			minuteText = minutes == 0 ? "" : minuteText;
			var secondText = seconds < 10 ? "0" + seconds : seconds;
			var text = Text.literal("%s%s%s".formatted(hourText, minuteText, secondText)).formatted(Formatting.GOLD);
			MessageHelper.broadcastOverlay(Server.getPlayerManager(), text);

			if (remaining <= 0) {
				TimerRunning = false;
				handleGameTimeout();
			}
		}
	}

	private static void handleGameTimeout() {
		Text text = Text.literal("Game Over! Tallying Scores:").formatted(Formatting.GOLD);
		MessageHelper.broadcastChat(Server.getPlayerManager(), text);

		var winners = new ArrayList<ServerPlayerEntity>();
		int winningScore = 0;
		for (var player : Server.getPlayerManager().getPlayerList()) {
			player.playSoundToPlayer(SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.MASTER, 0.5f, 1);
			var statHandler = player.getStatHandler();
			int score = switch (Category) {
				case Miscellaneous -> statHandler.getStat(Stats.CUSTOM, Identifier.of(Challenge));
				case Mined -> statHandler.getStat(Stats.MINED, Block.getBlockFromItem(Registries.ITEM.get(Identifier.of(Challenge))));
				case Crafted -> statHandler.getStat(Stats.CRAFTED, Registries.ITEM.get(Identifier.of(Challenge)));
				case Used -> statHandler.getStat(Stats.USED, Registries.ITEM.get(Identifier.of(Challenge)));
				case Broken -> statHandler.getStat(Stats.BROKEN, Registries.ITEM.get(Identifier.of(Challenge)));
				case Picked_Up -> statHandler.getStat(Stats.PICKED_UP, Registries.ITEM.get(Identifier.of(Challenge)));
				case Dropped -> statHandler.getStat(Stats.DROPPED, Registries.ITEM.get(Identifier.of(Challenge)));
				case Killed -> statHandler.getStat(Stats.KILLED, Registries.ENTITY_TYPE.get(Identifier.of(Challenge)));
				case Killed_By -> statHandler.getStat(Stats.KILLED_BY, Registries.ENTITY_TYPE.get(Identifier.of(Challenge)));
				case Inventory -> {
					var total = 0;
					var inventory = player.getInventory();
					var combinedInventory = new ArrayList<>(inventory.main);
					combinedInventory.addAll(inventory.armor);
					combinedInventory.addAll(inventory.offHand);
					combinedInventory.add(player.currentScreenHandler.getCursorStack());

					var challengeItem = Registries.ITEM.get(Identifier.of(Challenge));
					for (var item : combinedInventory) {
						if (item.getItem() == challengeItem)
							total += item.getCount();
					}
					yield total;
				}
			};

			if (score > winningScore) {
				winningScore = score;
				winners.clear();
				winners.add(player);
			} else if (score == winningScore) {
				winners.add(player);
			}

			text = Text.literal("%s: %s".formatted(PlayerHelper.getPlayerName(player), score)).formatted(Formatting.WHITE);
			MessageHelper.broadcastChat(Server.getPlayerManager(), text);
		}

		// Figure out the winner and broadcast scores for all players
		if (winners.size() > 1) {
			text = Text.literal("With a winning score of %s. Game ended in a tie between: ".formatted(winningScore)).formatted(Formatting.GOLD);
			MessageHelper.broadcastChat(Server.getPlayerManager(), text);

			for(var player : winners) {
				text = Text.literal("%s".formatted(PlayerHelper.getPlayerName(player))).formatted(Formatting.WHITE);
				MessageHelper.broadcastChat(Server.getPlayerManager(), text);
			}
		}
		else if (!winners.isEmpty()) {
			text = Text.literal("With a winning score of %s. %s has won the game!".formatted(winningScore, PlayerHelper.getPlayerName(winners.getFirst()))).formatted(Formatting.GOLD);
			MessageHelper.broadcastChat(Server.getPlayerManager(), text);
		}

		end();
	}

	// teleport players
	public static void teleportPlayersToChallengeSpawn(ServerWorld world, BlockPos spawn) {
		for (var player : Server.getPlayerManager().getPlayerList()) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 300 * 20, 255, false, false, false));
			player.setNoGravity(true);
			teleportPlayerToChallengeSpawn(world, player, spawn.offset(Direction.Axis.Y, YSpawnOffset));
		}
	}

	public static void teleportPlayerToChallengeSpawn(ServerWorld world, ServerPlayerEntity player, BlockPos spawn) {
		try {
			player.setMovementSpeed(0);
			player.getHungerManager().setFoodLevel(20);
			TeleportHelper.teleport(player, world, spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5, 0, 0);
			player.changeGameMode(GameMode.SURVIVAL);
			player.setSpawnPoint(player.getWorld().getRegistryKey(), spawn, 0, true, false);
		} catch (CommandSyntaxException e) {
			ChallengesMod.LOGGER.error(e.getMessage());
		}
	}

	public static void teleportAllToHub() {
		for (var player : Server.getPlayerManager().getPlayerList())
			teleportToHub(player);
	}

	public static void teleportToHub(ServerPlayerEntity player) {
		try {
			var server = player.getServer();
			if (server != null) {
				var world = WorldHelper.getWorldByName(server, ChallengesMod.CONFIG.SpawnSettings.Dimension);
				var tpPlayer = TeleportHelper.teleport(
						player,
						world,
						ChallengesMod.CONFIG.SpawnSettings.HubCoords.getBlockPos().getX() + 0.5,
						ChallengesMod.CONFIG.SpawnSettings.HubCoords.getBlockPos().getY(),
						ChallengesMod.CONFIG.SpawnSettings.HubCoords.getBlockPos().getZ() + 0.5,
						180,
						0);
				resetPlayer(tpPlayer);
				player.changeGameMode(GameMode.ADVENTURE);
			} else
				ChallengesMod.LOGGER.error("Unable to teleport player: %s to spawn".formatted(PlayerHelper.getPlayerName(player)));
		} catch (CommandSyntaxException e) {
			ChallengesMod.LOGGER.error("Unable to teleport player: %s to spawn".formatted(PlayerHelper.getPlayerName(player)));
			ChallengesMod.LOGGER.error(e.getMessage());
		}
	}

	// find spawn
	public static BlockPos getRandomPos(ServerWorld world, int xMin, int xMax, int zMin, int zMax) {
		while (true) {
			var x = ThreadLocalRandom.current().nextInt(xMin, xMax + 1);
			var z = ThreadLocalRandom.current().nextInt(zMin, zMax + 1);
			var pos = new BlockPos(x, 200, z);
			var biome = world.getBiome(pos);

			if (biome.getKey().isPresent() && biome.getKey().get() != BiomeKeys.OCEAN && biome.getKey().get() != BiomeKeys.BEACH)
				return pos;
		}
	}

	public static BlockPos findSpawn(ServerWorld world, Vec2f center, float maxRange, int maxY) {
		var random = Random.create();
		var attempts = 0;
		while (attempts < 100) {
			attempts++;
			var x = Math.floor(MathHelper.nextDouble(random, center.x - maxRange, center.x + maxRange));
			var z = Math.floor(MathHelper.nextDouble(random, center.y - maxRange, center.y + maxRange));

			// get the top block
			var mutable = new BlockPos.Mutable(x, maxY + 1, z);
			var headValid = world.getBlockState(mutable).isAir();
			mutable.move(Direction.DOWN);
			var footValid = world.getBlockState(mutable).isAir();
			if (!headValid || !footValid) continue;

			while (world.getBlockState(mutable).isAir() && mutable.getY() > world.getBottomY())
				mutable.move(Direction.DOWN);

			// check if top block is valid
			var state = world.getBlockState(mutable);
			//noinspection deprecation
			if (state.isLiquid() || state.isIn(BlockTags.FIRE)) continue;

			return mutable.move(Direction.UP);
		}
		return null;
	}

	public static void resetPlayer(ServerPlayerEntity player) {
		player.setMovementSpeed(1);
		player.setVelocity(0, 0, 0);
		player.setNoGravity(false);
		player.clearStatusEffects();

		player.playerScreenHandler.getCraftingInput().clear();
		player.currentScreenHandler.setCursorStack(new ItemStack(Items.AIR));
		player.getInventory().clear();
		player.currentScreenHandler.sendContentUpdates();
		player.playerScreenHandler.onContentChanged(player.getInventory());

		player.heal(player.getMaxHealth());
		player.getHungerManager().setFoodLevel(20);
		var world = WorldHelper.getWorldRegistryKeyByName(Server, ChallengesMod.CONFIG.SpawnSettings.Dimension);
		player.setSpawnPoint(world, ChallengesMod.CONFIG.SpawnSettings.HubCoords.getBlockPos(), 0, true, false);
	}

	public static void runAfterRespawn(ServerPlayerEntity player) {
		if (Status == GameStatus.Playing) {
			givePlayerEquipment(player, true);
			givePlayerStatusEffects(player, true);
		}
	}

	public static void givePlayerEquipment(PlayerEntity player, boolean respawn) {
		for (var gear : StartingGear) {
			if (!gear.OnRespawn && respawn) continue;

			var item = Registries.ITEM.get(Identifier.of(gear.Name));
			var stack = new ItemStack(item, gear.Amount);
			if (stack.isEnchantable()) {
				var server = player.getServer();
				if (server != null) {
					for (var enchantment : gear.Enchantments) {
						var entry = server.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(Identifier.of(enchantment.Type));
						entry.ifPresent(enchantmentReference -> stack.addEnchantment(enchantmentReference, enchantment.Level));
					}
				}
			}

			if (gear.AutoEquip) {
				var slot = player.getPreferredEquipmentSlot(stack);
				player.equipStack(slot, stack);
			} else {
				player.giveItemStack(stack);
			}
		}
	}

	public static void givePlayerStatusEffects(PlayerEntity player, boolean respawn) {
		player.clearStatusEffects();

		for (var entry : Effects) {
			if (!entry.OnRespawn && respawn) continue;
			var effect = Registries.STATUS_EFFECT.getEntry(Identifier.of(entry.Type));
            effect.ifPresent(statusEffectReference ->
					player.addStatusEffect(new StatusEffectInstance(statusEffectReference, entry.Duration < 0 ? -1 : entry.Duration * 20, entry.Amplifier, entry.Ambient, entry.ShowParticles, entry.ShowIcon))
			);
		}
	}

	public static void runOnServerTickEvent(MinecraftServer server) {
		switch (Status) {
			case Loading -> {
				var text = Text.literal("Loading Spawn").formatted(Formatting.GREEN);
				MessageHelper.broadcastOverlay(server.getPlayerManager(), text);
			}
			case Initializing -> {
				var world = WorldHelper.getWorldByName(server, Dimension);
				if (world == null) return;

				for (var tempWorld : server.getWorlds()) {
					tempWorld.setTimeOfDay(1000);
					tempWorld.setWeather(new java.util.Random().nextInt(0, 1000000), 0, false, false);
				}

				teleportPlayersToChallengeSpawn(world, GameSpawn);
				CountDownStart = System.currentTimeMillis();
				CurrentCountdownSecond = 0;

				Status = GameStatus.Starting;
			}
			case Starting -> {
				var spawn = GameSpawn.offset(Direction.Axis.Y, YSpawnOffset);
				for (var player : Server.getPlayerManager().getPlayerList()) {
					if (!player.getBlockPos().equals(spawn)) {
						try {
							var world = WorldHelper.getWorldByName(server, Dimension);
							TeleportHelper.teleport(player, world, spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5, player.getYaw(), player.getPitch());
						} catch (CommandSyntaxException e) {
							ChallengesMod.LOGGER.error("Unable to teleport player: %s".formatted(PlayerHelper.getPlayerName(player)));
						}
					}
				}
				handleCountdown();
			}
			case Playing -> {
				if (TimerRunning) handleTimer();
			}
		}
	}
}
