package net.encrypted.challenges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.encrypted.challenges.ChallengesManager;
import net.encrypted.challenges.ChallengesMod;
import net.encrypted.challenges.game.ChallengeCategory;
import net.encrypted.challenges.game.GameStatus;
import net.encrypted.challenges.game.StartingItem;
import net.encrypted.challenges.game.StatusEffect;
import net.encrypted.challenges.util.ExclusionHelper;
import net.encrypted.challenges.util.MessageHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.gamerules.GameRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.encrypted.challenges.ChallengesManager.*;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class ChallengesSettingsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(
                literal(ChallengesCommands.challengesCommand)
                    .then(literal("settings")

                            .executes( ctx -> {
                                var player = ctx.getSource().getPlayer();
                                if(player == null) return 0;

                                var text = Component.literal("Current Settings:").withStyle(ChatFormatting.GOLD);
                                MessageHelper.sendSystemMessage(player, text);
                                text = Component.literal("Challenge: ").withStyle(ChatFormatting.GREEN).append(Component.literal("%s - %s".formatted(Challenge, Category)).withStyle(ChatFormatting.WHITE));
                                MessageHelper.sendSystemMessage(player, text);
                                text = Component.literal("Time Limit: ").withStyle(ChatFormatting.GREEN).append(Component.literal(TimeLimit > 0 ? TimeLimit + " Minutes" : "None").withStyle(ChatFormatting.WHITE));
                                MessageHelper.sendSystemMessage(player, text);

                                var PVPEnabled = player.level().getGameRules().get(GameRules.PVP);
                                text = Component.literal("PVP: ").withStyle(ChatFormatting.GREEN).append(Component.literal(PVPEnabled ? "YES" : "NO").withStyle(ChatFormatting.WHITE));
                                MessageHelper.sendSystemMessage(player, text);

                                var keepInventoryEnabled = player.level().getGameRules().get(GameRules.KEEP_INVENTORY);
                                text = Component.literal("Keep Inventory: ").withStyle(ChatFormatting.GREEN).append(Component.literal(keepInventoryEnabled ? "YES" : "NO").withStyle(ChatFormatting.WHITE));
                                MessageHelper.sendSystemMessage(player, text);
                                return Command.SINGLE_SUCCESS;
                            })

                            // Set the equipment settings
                            .then(literal("equipment")
                                    .then(literal("none")
                                            .executes(ctx -> {
                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                        Component.literal("Starting equipment set to ").withStyle(ChatFormatting.WHITE).append(Component.literal("None").withStyle(ChatFormatting.GREEN)));
                                                ChallengesManager.StartingGear = new ArrayList<>();
                                                return Command.SINGLE_SUCCESS;
                                            }))
                                    .then(literal("stone")
                                            .executes(ctx -> {
                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                        Component.literal("Starting equipment set to ").withStyle(ChatFormatting.WHITE).append(Component.literal("Stone").withStyle(ChatFormatting.GREEN)));

                                                ChallengesManager.StartingGear.removeIf(gear ->
                                                        gear.Name.contains("_pickaxe") ||
                                                                gear.Name.contains("_shovel") ||
                                                                gear.Name.contains("_axe") ||
                                                                gear.Name.contains("_sword"));

                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:stone_pickaxe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:stone_shovel", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:stone_axe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:stone_sword", 1, true, false));
                                                return Command.SINGLE_SUCCESS;
                                            }))
                                    .then(literal("iron")
                                            .executes(ctx -> {
                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                        Component.literal("Starting equipment set to ").withStyle(ChatFormatting.WHITE).append(Component.literal("Iron").withStyle(ChatFormatting.GREEN)));
                                                ChallengesManager.StartingGear.removeIf(gear ->
                                                        gear.Name.contains("_pickaxe") ||
                                                                gear.Name.contains("_shovel") ||
                                                                gear.Name.contains("_axe") ||
                                                                gear.Name.contains("_sword"));

                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:iron_pickaxe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:iron_shovel", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:iron_axe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:iron_sword", 1, true, false));
                                                return Command.SINGLE_SUCCESS;
                                            }))
                                    .then(literal("diamond")
                                            .executes(ctx -> {
                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                        Component.literal("Starting equipment set to ").withStyle(ChatFormatting.WHITE).append(Component.literal("Diamond").withStyle(ChatFormatting.GREEN)));
                                                ChallengesManager.StartingGear.removeIf(gear ->
                                                        gear.Name.contains("_pickaxe") ||
                                                                gear.Name.contains("_shovel") ||
                                                                gear.Name.contains("_axe") ||
                                                                gear.Name.contains("_sword"));

                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:diamond_pickaxe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:diamond_shovel", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:diamond_axe", 1, true, false));
                                                ChallengesManager.StartingGear.add(new StartingItem("minecraft:diamond_sword", 1, true, false));
                                                return Command.SINGLE_SUCCESS;
                                            }))
                                    .then(literal("food")
                                            .then(argument("enabled", BoolArgumentType.bool())
                                                    .executes(ctx -> {
                                                        var bool = BoolArgumentType.getBool(ctx, "enabled");
                                                        var str = bool ? "Food will be given with starting equipment." : "No food will be given with starting equipment.";
                                                        MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(), Component.literal(str).withStyle(ChatFormatting.WHITE));
                                                        ChallengesManager.StartingGear.removeIf(gear -> gear.Name.equals("minecraft:bread"));
                                                        if (bool)
                                                            ChallengesManager.StartingGear.add(new StartingItem("minecraft:bread", 64, true, false));

                                                        return Command.SINGLE_SUCCESS;
                                                    })))
                                    .then(literal("add")
                                            .then(argument("item", ItemArgument.item(registryAccess))
                                                    .then(argument("amount", IntegerArgumentType.integer(1))
                                                            .then(argument("respawn", BoolArgumentType.bool())
                                                                    .then(argument("equip", BoolArgumentType.bool())
                                                                            .executes(ctx -> {
                                                                                var stack = ItemArgument.getItem(ctx, "item");
                                                                                var itemName = BuiltInRegistries.ITEM.getKey(stack.getItem());

                                                                                var count = IntegerArgumentType.getInteger(ctx, "amount");
                                                                                var onRespawn = BoolArgumentType.getBool(ctx, "respawn");
                                                                                var equip = BoolArgumentType.getBool(ctx, "equip");

                                                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                                                        Component.literal("Adding item " + itemName + " | " + count + " | " + onRespawn + " | " + equip + " to equipment.").withStyle(ChatFormatting.WHITE));
                                                                                ChallengesManager.StartingGear.add(new StartingItem(itemName.toString(), count, onRespawn, equip));

                                                                                return Command.SINGLE_SUCCESS;
                                                                            }))))))
                                    .then(literal("remove")
                                            .then(argument("item", ItemArgument.item(registryAccess))
                                                    .executes(ctx -> {
                                                        var stack = ItemArgument.getItem(ctx, "item");
                                                        var itemName = BuiltInRegistries.ITEM.getKey(stack.getItem());
                                                        MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                                Component.literal("Removing item " + itemName + " from equipment.").withStyle(ChatFormatting.WHITE));
                                                        ChallengesManager.StartingGear.removeIf(gear -> gear.Name.equals(itemName.toString()));

                                                        return Command.SINGLE_SUCCESS;
                                                    }))))

                            // Set the effects to play with
                            .then(literal("effects")
                                    .then(literal("add")
                                            .then(argument("effect", ResourceArgument.resource(registryAccess, Registries.MOB_EFFECT))
                                                    .then(argument("amplifier", IntegerArgumentType.integer(0, 255))
                                                            .executes(ctx -> {
                                                                var status = ResourceArgument.getMobEffect(ctx, "effect");
                                                                var amplifier = IntegerArgumentType.getInteger(ctx, "amplifier");
                                                                ChallengesManager.Effects.add(new StatusEffect(status.key().identifier().toString(), 99999, amplifier, true));

                                                                var text = Component.literal(status.value().getDisplayName().getString() + " added as effect with amplifier " + amplifier).withStyle(ChatFormatting.GOLD);
                                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(), text);
                                                                return Command.SINGLE_SUCCESS;
                                                            }))))

                                    .then(literal("remove")
                                            .then(argument("effect", ResourceArgument.resource(registryAccess, Registries.MOB_EFFECT)))
                                            .executes(ctx -> {
                                                var status = ResourceArgument.getMobEffect(ctx, "effect");
                                                var effect = ChallengesManager.Effects.removeIf(statusEffect -> statusEffect.Type.equals(status.value().getDisplayName().toString()));
                                                if (!effect) {
                                                    var text = Component.literal(status.value().getDisplayName().getString() + " isn't in the current list of effects to remove").withStyle(ChatFormatting.RED);
                                                    var player = ctx.getSource().getPlayer();
                                                    if (player != null)
                                                        MessageHelper.sendSystemMessage(player, text);
                                                } else {
                                                    var text = Component.literal(status.value().getDisplayName().getString() + " effect removed").withStyle(ChatFormatting.GOLD);
                                                    MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(), text);
                                                }
                                                return Command.SINGLE_SUCCESS;
                                            }))

                                    .then(literal("clear")
                                            .executes(ctx -> {
                                                ChallengesManager.Effects.clear();
                                                var text = Component.literal("All added effects cleared").withStyle(ChatFormatting.GOLD);
                                                MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(), text);
                                                return Command.SINGLE_SUCCESS;
                                            })))

                        // Set challenge statistic
                        .then(literal("category")
                            .then (literal("random")
                                .executes(ctx -> {
                                    var categories = Arrays.asList(ChallengeCategory.values());
                                    Collections.shuffle(categories);
                                    Category = categories.getFirst();

                                    var challenges = new ArrayList<String>();
                                    switch (Category) {
                                        case Miscellaneous -> {
                                            for (var stat : Stats.CUSTOM)
                                                challenges.add(stat.getValue().toString());
                                        }
                                        case Mined -> challenges.addAll(ExclusionHelper.getPossibleBlocks(""));
                                        case Broken-> challenges.addAll(ExclusionHelper.getBreakableItems(""));
                                        case Crafted-> challenges.addAll(ExclusionHelper.getCraftableItems(""));
                                        case Used, Picked_Up, Dropped, Inventory -> challenges.addAll(ExclusionHelper.getPossibleItems(""));
                                        case Killed -> challenges.addAll(ExclusionHelper.getPossibleToKillEntities(""));
                                        case Killed_By -> challenges.addAll(ExclusionHelper.getPossibleToBeKillByEntities(""));
                                    }

                                    Collections.shuffle(challenges);
                                    Challenge = challenges.getFirst();

                                    MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                            Component.literal("Challenge set to: %s - %s".formatted(Challenge, Category)).withStyle(ChatFormatting.WHITE));

                                    return Command.SINGLE_SUCCESS;
                                }))

                            .then(argument("category", StringArgumentType.word())
                                .suggests(ChallengesSettingsCommand::GetCategories)
                                .then(argument("challenge", StringArgumentType.greedyString())
                                        .suggests(ChallengesSettingsCommand::GetAvailableStats)
                                        .executes(ctx -> {
                                            var selectedCategory = StringArgumentType.getString(ctx, "category").toLowerCase();
                                            for (var category : ChallengeCategory.values()) {
                                                if (category.toString().toLowerCase().equals(selectedCategory)) {
                                                    Category = category;
                                                    Challenge = StringArgumentType.getString(ctx, "challenge");
                                                    MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                            Component.literal("Challenge set to: %s - %s".formatted(Category, Challenge)).withStyle(ChatFormatting.WHITE));
                                                }
                                            }
                                            return Command.SINGLE_SUCCESS;
                                        }))))

                        // Set profile dimension
                        .then(literal("dimension")
                                .then(argument("dimension", DimensionArgument.dimension())
                                        .suggests(ChallengesSettingsCommand::GetDimensionSuggestions)
                                        .then(argument("maxYLevel", IntegerArgumentType.integer())
                                                .then(argument("ySpawnOffset", IntegerArgumentType.integer())
                                                        .executes(ctx -> {
                                                            var player = ctx.getSource().getPlayer();
                                                            MaxYLevel = IntegerArgumentType.getInteger(ctx, "maxYLevel");
                                                            YSpawnOffset = IntegerArgumentType.getInteger(ctx, "ySpawnOffset");

                                                            if (Status != GameStatus.Idle) {
                                                                MessageHelper.sendSystemMessage(player, Component.literal("Can't change dimension while game in process.").withStyle(ChatFormatting.RED));
                                                                return Command.SINGLE_SUCCESS;
                                                            }

                                                            var dimension = DimensionArgument.getDimension(ctx, "dimension");
                                                            var dimensionName = dimension.dimension().identifier().toString();
                                                            MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                                    Component.literal("Dimension set to ").withStyle(ChatFormatting.WHITE).append(dimensionName).withStyle(ChatFormatting.GREEN));
                                                            Dimension = dimensionName;

                                                            return Command.SINGLE_SUCCESS;
                                                        })))))

                        // Set game timer
                        .then(literal("timer")
                                .then(argument("minutes", IntegerArgumentType.integer())
                                        .executes(ctx -> {
                                            if (Status == GameStatus.Idle) {
                                                var minutes = IntegerArgumentType.getInteger(ctx, "minutes");
                                                if (minutes == 0) {
                                                    ChallengesManager.TimeLimit = IntegerArgumentType.getInteger(ctx, "minutes");
                                                    MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                            Component.literal("Game timer disabled.").withStyle(ChatFormatting.WHITE));
                                                } else if (minutes > 0) {
                                                    ChallengesManager.TimeLimit = IntegerArgumentType.getInteger(ctx, "minutes");
                                                    MessageHelper.broadcastChat(ctx.getSource().getServer().getPlayerList(),
                                                            Component.literal("Game timer set to %s minutes.".formatted(minutes)).withStyle(ChatFormatting.WHITE));
                                                }
                                            } else {
                                                var player = ctx.getSource().getPlayer();
                                                MessageHelper.sendSystemMessage(player,
                                                        Component.literal("Can't change timer while in game.").withStyle(ChatFormatting.RED));
                                            }
                                            return Command.SINGLE_SUCCESS;
                                        })))));
    }

    private static CompletableFuture<Suggestions> GetCategories(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        var options = List.of(ChallengeCategory.values());

        for (var option : options){
            if (option.toString().toLowerCase().contains(builder.getRemainingLowerCase())) {
                builder.suggest(option.toString());
            }
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> GetAvailableStats(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        var selectedCategory = StringArgumentType.getString(context, "category").toLowerCase();
        for (var category : ChallengeCategory.values()) {
            if (category.toString().toLowerCase().equals(selectedCategory)) {
                switch (category) {
                    case Miscellaneous -> {
                        for (var stat : Stats.CUSTOM) {
                            var id = stat.getValue().toString();
                            if (id.toLowerCase().contains(builder.getRemainingLowerCase()))
                                builder.suggest(id);
                        }
                    }
                    case Mined -> {
                        for (var block : ExclusionHelper.getPossibleBlocks(builder.getRemainingLowerCase()))
                            builder.suggest(block);
                    }
                    case Broken-> {
                        for (var item : ExclusionHelper.getBreakableItems(builder.getRemainingLowerCase()))
                            builder.suggest(item);
                    }
                    case Crafted-> {
                        for (var item : ExclusionHelper.getCraftableItems(builder.getRemainingLowerCase()))
                            builder.suggest(item);
                    }
                    case Used, Picked_Up, Dropped, Inventory -> {
                        for (var item : ExclusionHelper.getPossibleItems(builder.getRemainingLowerCase()))
                            builder.suggest(item);
                    }
                    case Killed -> {
                        for (var entity : ExclusionHelper.getPossibleToKillEntities(builder.getRemainingLowerCase()))
                            builder.suggest(entity);
                    }
                    case Killed_By -> {
                        for (var entity : ExclusionHelper.getPossibleToBeKillByEntities(builder.getRemainingLowerCase()))
                            builder.suggest(entity);
                    }
                }
            }
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> GetDimensionSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        for (var dimension : ChallengesMod.CONFIG.ChallengeDimensions)
            builder.suggest(dimension);
        return builder.buildFuture();
    }
}
