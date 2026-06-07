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
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.Vec2;
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

	public static void start(ServerPlayer starter) {
		Server = starter.level().getServer();

		if (Status != GameStatus.Idle) {
			MessageHelper.sendSystemMessage(starter, Component.literal("Challenge already in progress.").withStyle(ChatFormatting.RED));
			return;
		}

		if (Category != ChallengeCategory.Inventory) {
			for (var player : Server.getPlayerList().getPlayers())
			{
				var id = Identifier.parse(Challenge);
				switch (Category) {
					case Miscellaneous -> {
						if (Stats.CUSTOM.contains(id))
							player.resetStat(Stats.CUSTOM.get(id));
					}
					case Mined -> {
						var block = Block.byItem(BuiltInRegistries.ITEM.getValue(id));
						if (Stats.BLOCK_MINED.contains(block))
							player.resetStat(Stats.BLOCK_MINED.get(block));
					}
					case Crafted -> {
						var item = BuiltInRegistries.ITEM.getValue(id);
						if (Stats.ITEM_CRAFTED.contains(item))
							player.resetStat(Stats.ITEM_CRAFTED.get(item));
					}
					case Used -> {
						var item = BuiltInRegistries.ITEM.getValue(id);
						if (Stats.ITEM_USED.contains(item))
							player.resetStat(Stats.ITEM_USED.get(item));
					}
					case Broken -> {
						var item = BuiltInRegistries.ITEM.getValue(id);
						if (Stats.ITEM_BROKEN.contains(item))
							player.resetStat(Stats.ITEM_BROKEN.get(item));
					}
					case Picked_Up -> {
						var item = BuiltInRegistries.ITEM.getValue(id);
						if (Stats.ITEM_PICKED_UP.contains(item))
							player.resetStat(Stats.ITEM_PICKED_UP.get(item));
					}
					case Dropped -> {
						var item = BuiltInRegistries.ITEM.getValue(id);
						if (Stats.ITEM_DROPPED.contains(item))
							player.resetStat(Stats.ITEM_DROPPED.get(item));
					}
					case Killed -> {
						var entity = BuiltInRegistries.ENTITY_TYPE.getValue(id);
						if (Stats.ENTITY_KILLED.contains(entity))
							player.resetStat(Stats.ENTITY_KILLED.get(entity));
					}
					case Killed_By -> {
						var entity = BuiltInRegistries.ENTITY_TYPE.getValue(id);
						if (Stats.ENTITY_KILLED_BY.contains(entity))
							player.resetStat(Stats.ENTITY_KILLED_BY.get(entity));
					}
				}
			}
		}

		Status = GameStatus.Loading;
		var text = Component.literal("Challenge starting!").withStyle(ChatFormatting.GREEN);
		MessageHelper.broadcastChatToPlayers(Server.getPlayerList(), text);

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
					new Vec2(origin.getX(), origin.getZ()),
					TPRandomizationRadius,
					MaxYLevel);

			if (GameSpawn == null){
				MessageHelper.broadcastChatToPlayers(Server.getPlayerList(), Component.literal("Unable to find spawn. Ending game.").withStyle(ChatFormatting.RED));
				end();
			}

			for (var player : Server.getPlayerList().getPlayers()) {
				player.getInventory().clearContent();
				player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			}
			Status = GameStatus.Initializing;
		}).exceptionally(ex -> {
			MessageHelper.broadcastChatToPlayers(Server.getPlayerList(), Component.literal("Problem finding spawn."));
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
			var text = Component.literal("%s".formatted(30 - elapsedSeconds)).withStyle(ChatFormatting.GOLD);
			MessageHelper.broadcastOverlay(Server.getPlayerList(), text);

			if (elapsedSeconds >= 30) {
				for (var player : Server.getPlayerList().getPlayers()) {
					player.setSpeed(1);
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
			var text = Component.literal("%s%s%s".formatted(hourText, minuteText, secondText)).withStyle(ChatFormatting.GOLD);
			MessageHelper.broadcastOverlay(Server.getPlayerList(), text);

			if (remaining <= 0) {
				TimerRunning = false;
				handleGameTimeout();
			}
		}
	}

	private static void handleGameTimeout() {
		Component text = Component.literal("Game Over! Tallying Scores:").withStyle(ChatFormatting.GOLD);
		MessageHelper.broadcastChat(Server.getPlayerList(), text);

		var winners = new ArrayList<ServerPlayer>();
		int winningScore = 0;
		for (var player : Server.getPlayerList().getPlayers()) {
			player.playSound(SoundEvents.BELL_RESONATE, 0.5f, 1);
			var statHandler = player.getStats();
			int score = switch (Category) {
				case Miscellaneous -> statHandler.getValue(Stats.CUSTOM, Identifier.parse(Challenge));
				case Mined -> statHandler.getValue(Stats.BLOCK_MINED, Block.byItem(BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge))));
				case Crafted -> statHandler.getValue(Stats.ITEM_CRAFTED, BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge)));
				case Used -> statHandler.getValue(Stats.ITEM_USED, BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge)));
				case Broken -> statHandler.getValue(Stats.ITEM_BROKEN, BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge)));
				case Picked_Up -> statHandler.getValue(Stats.ITEM_PICKED_UP, BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge)));
				case Dropped -> statHandler.getValue(Stats.ITEM_DROPPED, BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge)));
				case Killed -> statHandler.getValue(Stats.ENTITY_KILLED, BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(Challenge)));
				case Killed_By -> statHandler.getValue(Stats.ENTITY_KILLED_BY, BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(Challenge)));
				case Inventory -> {
					var total = 0;
					var inventory = player.getInventory();

					var challengeItem = BuiltInRegistries.ITEM.getValue(Identifier.parse(Challenge));
					for (var item : inventory) {
						if (item.getItem() == challengeItem)
							total += item.getCount();
					}
					var cursor = player.containerMenu.getCarried();
					if (cursor.getItem() == challengeItem)
						total += cursor.getCount();

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

			text = Component.literal("%s: %s".formatted(PlayerHelper.getPlayerName(player), score)).withStyle(ChatFormatting.WHITE);
			MessageHelper.broadcastChat(Server.getPlayerList(), text);
		}

		// Figure out the winner and broadcast scores for all players
		if (winners.size() > 1) {
			text = Component.literal("With a winning score of %s. Game ended in a tie between: ".formatted(winningScore)).withStyle(ChatFormatting.GOLD);
			MessageHelper.broadcastChat(Server.getPlayerList(), text);

			for(var player : winners) {
				text = Component.literal("%s".formatted(PlayerHelper.getPlayerName(player))).withStyle(ChatFormatting.WHITE);
				MessageHelper.broadcastChat(Server.getPlayerList(), text);
			}
		}
		else if (!winners.isEmpty()) {
			text = Component.literal("With a winning score of %s. %s has won the game!".formatted(winningScore, PlayerHelper.getPlayerName(winners.getFirst()))).withStyle(ChatFormatting.GOLD);
			MessageHelper.broadcastChat(Server.getPlayerList(), text);
		}

		end();
	}

	// teleport players
	public static void teleportPlayersToChallengeSpawn(ServerLevel world, BlockPos spawn) {
		for (var player : Server.getPlayerList().getPlayers()) {
			player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300 * 20, 255, false, false, false));
			player.setNoGravity(true);
			teleportPlayerToChallengeSpawn(world, player, spawn.relative(Direction.Axis.Y, YSpawnOffset));
		}
	}

	public static void teleportPlayerToChallengeSpawn(ServerLevel world, ServerPlayer player, BlockPos spawn) {
		try {
			player.setSpeed(0);
			player.getFoodData().setFoodLevel(20);
			TeleportHelper.teleport(player, world, spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5, 0, 0);
			player.setGameMode(GameType.SURVIVAL);
			player.setRespawnPosition(new ServerPlayer.RespawnConfig(LevelData.RespawnData.of(player.level().dimension(), spawn, 0.0f, 0.0f), true), false);
		} catch (CommandSyntaxException e) {
			ChallengesMod.LOGGER.error(e.getMessage());
		}
	}

	public static void teleportAllToHub() {
		for (var player : Server.getPlayerList().getPlayers())
			teleportToHub(player);
	}

	public static void teleportToHub(ServerPlayer player) {
		try {
			var server = player.level().getServer();
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
            player.setGameMode(GameType.ADVENTURE);
        } catch (CommandSyntaxException e) {
			ChallengesMod.LOGGER.error("Unable to teleport player: %s to spawn".formatted(PlayerHelper.getPlayerName(player)));
			ChallengesMod.LOGGER.error(e.getMessage());
		}
	}

	// find spawn
	public static BlockPos getRandomPos(ServerLevel world, int xMin, int xMax, int zMin, int zMax) {
		while (true) {
			var x = ThreadLocalRandom.current().nextInt(xMin, xMax + 1);
			var z = ThreadLocalRandom.current().nextInt(zMin, zMax + 1);
			var pos = new BlockPos(x, 200, z);
			var biome = world.getBiome(pos);

			if (biome.unwrapKey().isPresent() && biome.unwrapKey().get() != Biomes.OCEAN && biome.unwrapKey().get() != Biomes.BEACH)
				return pos;
		}
	}

	public static BlockPos findSpawn(ServerLevel world, Vec2 center, float maxRange, int maxY) {
		var random = RandomSource.create();
		var attempts = 0;
		while (attempts < 100) {
			attempts++;
			var x = Math.floor(Mth.nextDouble(random, center.x - maxRange, center.x + maxRange));
			var z = Math.floor(Mth.nextDouble(random, center.y - maxRange, center.y + maxRange));

			// get the top block
			var mutable = new BlockPos.MutableBlockPos(x, maxY + 1, z);
			var headValid = world.getBlockState(mutable).isAir();
			mutable.move(Direction.DOWN);
			var footValid = world.getBlockState(mutable).isAir();
			if (!headValid || !footValid) continue;

			while (world.getBlockState(mutable).isAir() && mutable.getY() > world.getMinY())
				mutable.move(Direction.DOWN);

			// check if top block is valid
			var state = world.getBlockState(mutable);
			//noinspection deprecation
			if (state.liquid() || state.is(BlockTags.FIRE)) continue;

			return mutable.move(Direction.UP);
		}
		return null;
	}

	public static void resetPlayer(ServerPlayer player) {
		player.setSpeed(1);
		player.setDeltaMovement(0, 0, 0);
		player.setNoGravity(false);
		player.removeAllEffects();

		player.inventoryMenu.getCraftSlots().clearContent();
		player.containerMenu.setCarried(new ItemStack(Items.AIR));
		player.getInventory().clearContent();
		player.containerMenu.broadcastChanges();
		player.inventoryMenu.slotsChanged(player.getInventory());

		player.heal(player.getMaxHealth());
		player.getFoodData().setFoodLevel(20);
		var world = WorldHelper.getWorldRegistryKeyByName(Server, ChallengesMod.CONFIG.SpawnSettings.Dimension);
		player.setRespawnPosition(new ServerPlayer.RespawnConfig(LevelData.RespawnData.of(world, ChallengesMod.CONFIG.SpawnSettings.HubCoords.getBlockPos(), 0.0f, 0.0f), true), false);
	}

	public static void runAfterRespawn(ServerPlayer player) {
		if (Status == GameStatus.Playing) {
			givePlayerEquipment(player, true);
			givePlayerStatusEffects(player, true);
		}
	}

	public static void givePlayerEquipment(ServerPlayer player, boolean respawn) {
		for (var gear : StartingGear) {
			if (!gear.OnRespawn && respawn) continue;

			var item = BuiltInRegistries.ITEM.getValue(Identifier.parse(gear.Name));
			var stack = new ItemStack(item, gear.Amount);
			if (stack.isEnchantable()) {
				var server = player.level().getServer();
				for (var enchantment : gear.Enchantments) {
					var entry = server.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(Identifier.parse(enchantment.Type));
					entry.ifPresent(enchantmentReference -> stack.enchant(enchantmentReference, enchantment.Level));
				}
			}

			if (gear.AutoEquip) {
				var slot = player.getEquipmentSlotForItem(stack);
				player.setItemSlot(slot, stack);
			} else {
				player.addItem(stack);
			}
		}
	}

	public static void givePlayerStatusEffects(Player player, boolean respawn) {
		player.removeAllEffects();

		for (var entry : Effects) {
			if (!entry.OnRespawn && respawn) continue;
			var effect = BuiltInRegistries.MOB_EFFECT.get(Identifier.parse(entry.Type));
            effect.ifPresent(statusEffectReference ->
					player.addEffect(new MobEffectInstance(statusEffectReference, entry.Duration < 0 ? -1 : entry.Duration * 20, entry.Amplifier, entry.Ambient, entry.ShowParticles, entry.ShowIcon))
			);
		}
	}

	public static void runOnServerTickEvent(MinecraftServer server) {
		switch (Status) {
			case Loading -> {
				var text = Component.literal("Loading Spawn").withStyle(ChatFormatting.GREEN);
				MessageHelper.broadcastOverlay(server.getPlayerList(), text);
			}
			case Initializing -> {
				var world = WorldHelper.getWorldByName(server, Dimension);
				if (world == null) return;

				for (var tempWorld : server.getAllLevels()) {
					var clock = tempWorld.dimensionTypeRegistration().value().defaultClock();
					clock.ifPresent(worldClockHolder -> tempWorld.clockManager().setTotalTicks(worldClockHolder, 1000));
					tempWorld.resetWeatherCycle();
					//tempWorld.setWeatherParameters(new java.util.Random().nextInt(0, 1000000), 0, false, false);
				}

				teleportPlayersToChallengeSpawn(world, GameSpawn);
				CountDownStart = System.currentTimeMillis();
				CurrentCountdownSecond = 0;

				Status = GameStatus.Starting;
			}
			case Starting -> {
				var spawn = GameSpawn.relative(Direction.Axis.Y, YSpawnOffset);
				for (var player : Server.getPlayerList().getPlayers()) {
					if (!player.blockPosition().equals(spawn)) {
						try {
							var world = WorldHelper.getWorldByName(server, Dimension);
							TeleportHelper.teleport(player, world, spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5, player.getYRot(), player.getXRot());
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
