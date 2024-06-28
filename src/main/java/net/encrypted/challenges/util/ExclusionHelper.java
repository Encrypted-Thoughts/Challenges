package net.encrypted.challenges.util;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class ExclusionHelper {
    public static ArrayList<Item> CreativeOnlyItems = new ArrayList<>(List.of(
            Items.BEDROCK,
            Items.BUDDING_AMETHYST,
            Items.CHORUS_PLANT,
            Items.DIRT_PATH,
            Items.END_PORTAL_FRAME,
            Items.FARMLAND,
            Items.FROGSPAWN,
            Items.SPAWNER,
            Items.INFESTED_COBBLESTONE,
            Items.INFESTED_DEEPSLATE,
            Items.INFESTED_CHISELED_STONE_BRICKS,
            Items.INFESTED_STONE,
            Items.INFESTED_CRACKED_STONE_BRICKS,
            Items.INFESTED_MOSSY_STONE_BRICKS,
            Items.INFESTED_STONE_BRICKS,
            Items.REINFORCED_DEEPSLATE,
            Items.PLAYER_HEAD,
            Items.AIR,
            Items.TRIAL_SPAWNER,

            Items.COMMAND_BLOCK,
            Items.CHAIN_COMMAND_BLOCK,
            Items.REPEATING_COMMAND_BLOCK,
            Items.COMMAND_BLOCK_MINECART,
            Items.JIGSAW,
            Items.STRUCTURE_BLOCK,
            Items.STRUCTURE_VOID,
            Items.BARRIER,
            Items.DEBUG_STICK,
            Items.KNOWLEDGE_BOOK,
            Items.LIGHT,
            Items.PETRIFIED_OAK_SLAB,

            Items.ALLAY_SPAWN_EGG,
            Items.AXOLOTL_SPAWN_EGG,
            Items.BAT_SPAWN_EGG,
            Items.BEE_SPAWN_EGG,
            Items.BLAZE_SPAWN_EGG,
            Items.CAMEL_SPAWN_EGG,
            Items.CAT_SPAWN_EGG,
            Items.CAVE_SPIDER_SPAWN_EGG,
            Items.CHICKEN_SPAWN_EGG,
            Items.COD_SPAWN_EGG,
            Items.COW_SPAWN_EGG,
            Items.CREEPER_SPAWN_EGG,
            Items.DOLPHIN_SPAWN_EGG,
            Items.DONKEY_SPAWN_EGG,
            Items.DROWNED_SPAWN_EGG,
            Items.ELDER_GUARDIAN_SPAWN_EGG,
            Items.ENDER_DRAGON_SPAWN_EGG,
            Items.ENDERMAN_SPAWN_EGG,
            Items.ENDERMITE_SPAWN_EGG,
            Items.EVOKER_SPAWN_EGG,
            Items.FOX_SPAWN_EGG,
            Items.FROG_SPAWN_EGG,
            Items.GHAST_SPAWN_EGG,
            Items.GLOW_SQUID_SPAWN_EGG,
            Items.GOAT_SPAWN_EGG,
            Items.GUARDIAN_SPAWN_EGG,
            Items.HOGLIN_SPAWN_EGG,
            Items.HORSE_SPAWN_EGG,
            Items.HUSK_SPAWN_EGG,
            Items.IRON_GOLEM_SPAWN_EGG,
            Items.LLAMA_SPAWN_EGG,
            Items.MAGMA_CUBE_SPAWN_EGG,
            Items.MOOSHROOM_SPAWN_EGG,
            Items.MULE_SPAWN_EGG,
            Items.OCELOT_SPAWN_EGG,
            Items.PANDA_SPAWN_EGG,
            Items.PARROT_SPAWN_EGG,
            Items.PHANTOM_SPAWN_EGG,
            Items.PIG_SPAWN_EGG,
            Items.PIGLIN_SPAWN_EGG,
            Items.PIGLIN_BRUTE_SPAWN_EGG,
            Items.PILLAGER_SPAWN_EGG,
            Items.POLAR_BEAR_SPAWN_EGG,
            Items.PUFFERFISH_SPAWN_EGG,
            Items.RABBIT_SPAWN_EGG,
            Items.RAVAGER_SPAWN_EGG,
            Items.SALMON_SPAWN_EGG,
            Items.SHEEP_SPAWN_EGG,
            Items.SHULKER_SPAWN_EGG,
            Items.SILVERFISH_SPAWN_EGG,
            Items.SKELETON_SPAWN_EGG,
            Items.SKELETON_HORSE_SPAWN_EGG,
            Items.SLIME_SPAWN_EGG,
            Items.SNIFFER_SPAWN_EGG,
            Items.SNOW_GOLEM_SPAWN_EGG,
            Items.SPIDER_SPAWN_EGG,
            Items.SQUID_SPAWN_EGG,
            Items.WITHER_SPAWN_EGG,
            Items.STRAY_SPAWN_EGG,
            Items.STRIDER_SPAWN_EGG,
            Items.TADPOLE_SPAWN_EGG,
            Items.TRADER_LLAMA_SPAWN_EGG,
            Items.TROPICAL_FISH_SPAWN_EGG,
            Items.TURTLE_SPAWN_EGG,
            Items.VEX_SPAWN_EGG,
            Items.VILLAGER_SPAWN_EGG,
            Items.VINDICATOR_SPAWN_EGG,
            Items.WANDERING_TRADER_SPAWN_EGG,
            Items.WARDEN_SPAWN_EGG,
            Items.WITCH_SPAWN_EGG,
            Items.WITHER_SKELETON_SPAWN_EGG,
            Items.WOLF_SPAWN_EGG,
            Items.ZOGLIN_SPAWN_EGG,
            Items.ZOMBIE_SPAWN_EGG,
            Items.ZOMBIE_HORSE_SPAWN_EGG,
            Items.ZOMBIE_VILLAGER_SPAWN_EGG,
            Items.ZOMBIFIED_PIGLIN_SPAWN_EGG,
            Items.BREEZE_SPAWN_EGG
    ));

    public static ArrayList<EntityType<?>> ImpossibleToKillEntities = new ArrayList<>(List.of(
            EntityType.AREA_EFFECT_CLOUD,
            EntityType.ARMOR_STAND,
            EntityType.ARROW,
            EntityType.BLOCK_DISPLAY,
            EntityType.BOAT,
            EntityType.CHEST_BOAT,
            EntityType.CHEST_MINECART,
            EntityType.COMMAND_BLOCK_MINECART,
            EntityType.DRAGON_FIREBALL,
            EntityType.EGG,
            EntityType.END_CRYSTAL,
            EntityType.ENDER_PEARL,
            EntityType.EVOKER_FANGS,
            EntityType.EXPERIENCE_BOTTLE,
            EntityType.EXPERIENCE_ORB,
            EntityType.EYE_OF_ENDER,
            EntityType.FALLING_BLOCK,
            EntityType.FIREBALL,
            EntityType.FIREWORK_ROCKET,
            EntityType.FISHING_BOBBER,
            EntityType.FURNACE_MINECART,
            EntityType.GIANT,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.HOPPER_MINECART,
            EntityType.ILLUSIONER,
            EntityType.INTERACTION,
            EntityType.ITEM,
            EntityType.ITEM_DISPLAY,
            EntityType.ITEM_FRAME,
            EntityType.LEASH_KNOT,
            EntityType.LIGHTNING_BOLT,
            EntityType.LLAMA_SPIT,
            EntityType.MARKER,
            EntityType.MINECART,
            EntityType.PAINTING,
            EntityType.POTION,
            EntityType.SHULKER_BULLET,
            EntityType.SMALL_FIREBALL,
            EntityType.SNOWBALL,
            EntityType.SPAWNER_MINECART,
            EntityType.SPECTRAL_ARROW,
            EntityType.TEXT_DISPLAY,
            EntityType.TNT,
            EntityType.TNT_MINECART,
            EntityType.TRIDENT,
            EntityType.WITHER_SKULL,
            EntityType.ZOMBIE_HORSE
    ));

    public static ArrayList<EntityType<?>> ImpossibleToBeKillByEntities = new ArrayList<>(List.of(
            EntityType.ALLAY,
            EntityType.AREA_EFFECT_CLOUD,
            EntityType.ARMOR_STAND,
            EntityType.ARROW,
            EntityType.AXOLOTL,
            EntityType.BAT,
            EntityType.BLOCK_DISPLAY,
            EntityType.BOAT,
            EntityType.CAMEL,
            EntityType.CAT,
            EntityType.CHEST_BOAT,
            EntityType.CHEST_MINECART,
            EntityType.CHICKEN,
            EntityType.COD,
            EntityType.COMMAND_BLOCK_MINECART,
            EntityType.COW,
            EntityType.DONKEY,
            EntityType.DRAGON_FIREBALL,
            EntityType.EGG,
            EntityType.END_CRYSTAL,
            EntityType.ENDER_PEARL,
            EntityType.EVOKER_FANGS,
            EntityType.EXPERIENCE_BOTTLE,
            EntityType.EXPERIENCE_ORB,
            EntityType.EYE_OF_ENDER,
            EntityType.FALLING_BLOCK,
            EntityType.FIREWORK_ROCKET,
            EntityType.FISHING_BOBBER,
            EntityType.FIREBALL,
            EntityType.FOX,
            EntityType.FROG,
            EntityType.FURNACE_MINECART,
            EntityType.GIANT,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.GLOW_SQUID,
            EntityType.HOPPER_MINECART,
            EntityType.HORSE,
            EntityType.ILLUSIONER,
            EntityType.INTERACTION,
            EntityType.ITEM,
            EntityType.ITEM_DISPLAY,
            EntityType.ITEM_FRAME,
            EntityType.LEASH_KNOT,
            EntityType.LIGHTNING_BOLT,
            EntityType.LLAMA,
            EntityType.LLAMA_SPIT,
            EntityType.MARKER,
            EntityType.MINECART,
            EntityType.MOOSHROOM,
            EntityType.MULE,
            EntityType.OCELOT,
            EntityType.PAINTING,
            EntityType.PARROT,
            EntityType.PIG,
            EntityType.POTION,
            EntityType.RABBIT,
            EntityType.SALMON,
            EntityType.SHULKER_BULLET,
            EntityType.SKELETON_HORSE,
            EntityType.SMALL_FIREBALL,
            EntityType.SNIFFER,
            EntityType.SNOWBALL,
            EntityType.SNOW_GOLEM,
            EntityType.SPAWNER_MINECART,
            EntityType.SPECTRAL_ARROW,
            EntityType.SQUID,
            EntityType.STRIDER,
            EntityType.TADPOLE,
            EntityType.TEXT_DISPLAY,
            EntityType.TNT,
            EntityType.TNT_MINECART,
            EntityType.TRIDENT,
            EntityType.TRADER_LLAMA,
            EntityType.TROPICAL_FISH,
            EntityType.VILLAGER,
            EntityType.WANDERING_TRADER,
            EntityType.WITHER_SKULL,
            EntityType.ZOMBIE_HORSE
    ));

    public static ArrayList<Item> BreakableItems = new ArrayList<>(List.of(
            Items.SHEARS,
            Items.ELYTRA,
            Items.TURTLE_HELMET,
            Items.BRUSH,
            Items.FISHING_ROD,
            Items.CARROT_ON_A_STICK,
            Items.WARPED_FUNGUS_ON_A_STICK,
            Items.BOW,
            Items.SHIELD,
            Items.CROSSBOW,
            Items.FLINT_AND_STEEL,
            Items.TRIDENT,

            Items.LEATHER_BOOTS,
            Items.LEATHER_HELMET,
            Items.LEATHER_CHESTPLATE,
            Items.LEATHER_LEGGINGS,
            Items.WOODEN_AXE,
            Items.WOODEN_HOE,
            Items.WOODEN_PICKAXE,
            Items.WOODEN_SHOVEL,
            Items.WOODEN_SWORD,
            Items.STONE_AXE,
            Items.STONE_HOE,
            Items.STONE_PICKAXE,
            Items.STONE_SHOVEL,
            Items.STONE_SWORD,
            Items.CHAINMAIL_LEGGINGS,
            Items.CHAINMAIL_BOOTS,
            Items.CHAINMAIL_CHESTPLATE,
            Items.CHAINMAIL_HELMET,
            Items.IRON_AXE,
            Items.IRON_HOE,
            Items.IRON_PICKAXE,
            Items.IRON_SHOVEL,
            Items.IRON_SWORD,
            Items.IRON_LEGGINGS,
            Items.IRON_BOOTS,
            Items.IRON_CHESTPLATE,
            Items.IRON_HELMET,
            Items.GOLDEN_AXE,
            Items.GOLDEN_HOE,
            Items.GOLDEN_PICKAXE,
            Items.GOLDEN_SHOVEL,
            Items.GOLDEN_SWORD,
            Items.GOLDEN_LEGGINGS,
            Items.GOLDEN_BOOTS,
            Items.GOLDEN_CHESTPLATE,
            Items.GOLDEN_HELMET,
            Items.DIAMOND_AXE,
            Items.DIAMOND_HOE,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_HELMET,
            Items.NETHERITE_AXE,
            Items.NETHERITE_HOE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_SWORD,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_HELMET
    ));

    public static ArrayList<Item> UncraftableItems = new ArrayList<>(List.of(
            Items.AMETHYST_BLOCK,
            Items.AMETHYST_SHARD,
            Items.ANDESITE,
            Items.APPLE,
            Items.BAMBOO,
            Items.BEETROOT,
            Items.BEETROOT_SEEDS,
            Items.BELL,
            Items.BEEHIVE,
            Items.BONE,
            Items.BROWN_MUSHROOM,
            Items.BROWN_MUSHROOM_BLOCK,
            Items.CACTUS,
            Items.CALCITE,
            Items.CHERRY_LEAVES,
            Items.CHERRY_LOG,
            Items.STRIPPED_CHERRY_LOG,
            Items.STRIPPED_CHERRY_WOOD,
            Items.CHERRY_SAPLING,
            Items.OAK_LEAVES,
            Items.OAK_LOG,
            Items.STRIPPED_OAK_LOG,
            Items.STRIPPED_OAK_WOOD,
            Items.OAK_SAPLING,
            Items.DARK_OAK_LEAVES,
            Items.DARK_OAK_LOG,
            Items.STRIPPED_DARK_OAK_LOG,
            Items.STRIPPED_DARK_OAK_WOOD,
            Items.DARK_OAK_SAPLING,
            Items.SPRUCE_LEAVES,
            Items.SPRUCE_LOG,
            Items.STRIPPED_SPRUCE_LOG,
            Items.STRIPPED_SPRUCE_WOOD,
            Items.SPRUCE_SAPLING,
            Items.BIRCH_LEAVES,
            Items.BIRCH_LOG,
            Items.STRIPPED_BIRCH_LOG,
            Items.STRIPPED_BIRCH_WOOD,
            Items.BIRCH_SAPLING,
            Items.ACACIA_LEAVES,
            Items.ACACIA_LOG,
            Items.STRIPPED_ACACIA_LOG,
            Items.STRIPPED_ACACIA_WOOD,
            Items.ACACIA_SAPLING,
            Items.JUNGLE_LEAVES,
            Items.JUNGLE_LOG,
            Items.STRIPPED_JUNGLE_LOG,
            Items.STRIPPED_JUNGLE_WOOD,
            Items.JUNGLE_SAPLING,
            Items.MANGROVE_LEAVES,
            Items.MANGROVE_LOG,
            Items.STRIPPED_MANGROVE_LOG,
            Items.STRIPPED_MANGROVE_WOOD,
            Items.MANGROVE_PROPAGULE,
            Items.MANGROVE_ROOTS,
            Items.STRIPPED_BAMBOO_BLOCK,
            Items.CHORUS_FLOWER,
            Items.CLAY_BALL,
            Items.COBBLED_DEEPSLATE,
            Items.COBBLESTONE,
            Items.CRIMSON_NYLIUM,
            Items.CRYING_OBSIDIAN,
            Items.DIRT,
            Items.DRIPSTONE_BLOCK,
            Items.POINTED_DRIPSTONE,
            Items.END_STONE,
            Items.GILDED_BLACKSTONE,
            Items.BLACKSTONE,
            Items.SHORT_GRASS,
            Items.GRASS_BLOCK,
            Items.TALL_GRASS,
            Items.GRAVEL,
            Items.HONEY_BOTTLE,
            Items.HONEYCOMB,
            Items.ICE,
            Items.LAPIS_LAZULI,
            Items.LAPIS_ORE,
            Items.DEEPSLATE_LAPIS_ORE,
            Items.MAGMA_BLOCK,
            Items.MOSS_BLOCK,
            Items.MUD,
            Items.MUSHROOM_STEM,
            Items.RED_MUSHROOM,
            Items.RED_MUSHROOM_BLOCK,
            Items.MYCELIUM,
            Items.NETHER_WART,
            Items.NETHERRACK,
            Items.OBSIDIAN,
            Items.PIGLIN_HEAD,
            Items.PINK_PETALS,
            Items.PITCHER_PLANT,
            Items.PITCHER_POD,
            Items.PODZOL,
            Items.PRISMARINE_SHARD,
            Items.PRISMARINE_CRYSTALS,
            Items.PUMPKIN,
            Items.CARVED_PUMPKIN,
            Items.SCULK,
            Items.SCULK_CATALYST,
            Items.SCULK_SENSOR,
            Items.SCULK_SHRIEKER,
            Items.SCULK_VEIN,
            Items.SHROOMLIGHT,
            Items.SLIME_BALL,
            Items.SNIFFER_EGG,
            Items.SOUL_SAND,
            Items.SOUL_SOIL,
            Items.SUSPICIOUS_SAND,
            Items.SUSPICIOUS_GRAVEL,
            Items.TORCHFLOWER,
            Items.TORCHFLOWER_SEEDS,
            Items.SPONGE,
            Items.WET_SPONGE,
            Items.TUFF,
            Items.TURTLE_EGG,
            Items.DRAGON_EGG,
            Items.WARPED_NYLIUM,
            Items.WARPED_WART_BLOCK,
            Items.WHEAT_SEEDS,
            Items.RAW_COPPER,
            Items.RAW_GOLD,
            Items.RAW_IRON,
            Items.PORKCHOP,
            Items.BEEF,
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
            Items.PUFFERFISH,
            Items.AXOLOTL_BUCKET,
            Items.COD_BUCKET,
            Items.LAVA_BUCKET,
            Items.MILK_BUCKET,
            Items.POWDER_SNOW_BUCKET,
            Items.PUFFERFISH_BUCKET,
            Items.TADPOLE_BUCKET,
            Items.TROPICAL_FISH_BUCKET,
            Items.WATER_BUCKET,
            Items.OCHRE_FROGLIGHT,
            Items.VERDANT_FROGLIGHT,
            Items.PEARLESCENT_FROGLIGHT,
            Items.ROTTEN_FLESH,
            Items.SPIDER_EYE,
            Items.STRING,
            Items.GUNPOWDER,
            Items.PHANTOM_MEMBRANE,
            Items.ENDER_PEARL,
            Items.SNOWBALL,
            Items.WITHER_ROSE,
            Items.WITHER_SKELETON_SKULL,
            Items.BLAZE_ROD,
            Items.DIORITE,
            Items.GRANITE,
            Items.COAL_ORE,
            Items.COAL,
            Items.DEEPSLATE_COAL_ORE,
            Items.COPPER_ORE,
            Items.DEEPSLATE_COPPER_ORE,
            Items.DEEPSLATE_DIAMOND_ORE,
            Items.DIAMOND_ORE,
            Items.DEEPSLATE_EMERALD_ORE,
            Items.EMERALD_ORE,
            Items.DEEPSLATE_GOLD_ORE,
            Items.GOLD_ORE,
            Items.NETHER_GOLD_ORE,
            Items.NETHER_QUARTZ_ORE,
            Items.REDSTONE_ORE,
            Items.DEEPSLATE_REDSTONE_ORE,
            Items.IRON_ORE,
            Items.DEEPSLATE_IRON_ORE,
            Items.SPORE_BLOSSOM,
            Items.BIG_DRIPLEAF,
            Items.SMALL_DRIPLEAF,
            Items.POTATO,
            Items.POISONOUS_POTATO,
            Items.CARROT,
            Items.CHORUS_FRUIT,
            Items.CHICKEN,
            Items.RABBIT_HIDE,
            Items.RABBIT_FOOT,
            Items.RABBIT,
            Items.GLOW_BERRIES,
            Items.SWEET_BERRIES,
            Items.MUTTON,
            Items.ENCHANTED_GOLDEN_APPLE,
            Items.EXPERIENCE_BOTTLE,
            Items.SADDLE,
            Items.DIAMOND_HORSE_ARMOR,
            Items.GOLDEN_HORSE_ARMOR,
            Items.IRON_HORSE_ARMOR,
            Items.LEATHER_HORSE_ARMOR,
            Items.PLAYER_HEAD,
            Items.CREEPER_HEAD,
            Items.DRAGON_HEAD,
            Items.ZOMBIE_HEAD,
            Items.OXEYE_DAISY,
            Items.CORNFLOWER,
            Items.SUNFLOWER,
            Items.FLOWERING_AZALEA,
            Items.FLOWERING_AZALEA_LEAVES,
            Items.AZALEA,
            Items.AZALEA_LEAVES,
            Items.ALLIUM,
            Items.AZURE_BLUET,
            Items.BLUE_ORCHID,
            Items.DANDELION,
            Items.LILAC,
            Items.LILY_PAD,
            Items.LILY_OF_THE_VALLEY,
            Items.ORANGE_TULIP,
            Items.PINK_TULIP,
            Items.RED_TULIP,
            Items.WHITE_TULIP,
            Items.PEONY,
            Items.POPPY,
            Items.ROSE_BUSH,
            Items.MUSIC_DISC_11,
            Items.MUSIC_DISC_13,
            Items.MUSIC_DISC_BLOCKS,
            Items.MUSIC_DISC_CAT,
            Items.MUSIC_DISC_CHIRP,
            Items.MUSIC_DISC_FAR,
            Items.MUSIC_DISC_MALL,
            Items.MUSIC_DISC_MELLOHI,
            Items.MUSIC_DISC_OTHERSIDE,
            Items.MUSIC_DISC_PIGSTEP,
            Items.MUSIC_DISC_RELIC,
            Items.MUSIC_DISC_STAL,
            Items.MUSIC_DISC_STRAD,
            Items.MUSIC_DISC_WAIT,
            Items.MUSIC_DISC_WARD,
            Items.HEART_OF_THE_SEA,
            Items.ANCIENT_DEBRIS,
            Items.BASALT,
            Items.BEE_NEST,

            Items.ANGLER_POTTERY_SHERD,
            Items.ARCHER_POTTERY_SHERD,
            Items.ARMS_UP_POTTERY_SHERD,
            Items.BLADE_POTTERY_SHERD,
            Items.BREWER_POTTERY_SHERD,
            Items.BURN_POTTERY_SHERD,
            Items.DANGER_POTTERY_SHERD,
            Items.EXPLORER_POTTERY_SHERD,
            Items.FRIEND_POTTERY_SHERD,
            Items.HEART_POTTERY_SHERD,
            Items.HEARTBREAK_POTTERY_SHERD,
            Items.HOWL_POTTERY_SHERD,
            Items.MINER_POTTERY_SHERD,
            Items.MOURNER_POTTERY_SHERD,
            Items.PLENTY_POTTERY_SHERD,
            Items.PRIZE_POTTERY_SHERD,
            Items.SHEAF_POTTERY_SHERD,
            Items.SHELTER_POTTERY_SHERD,
            Items.SKULL_POTTERY_SHERD,
            Items.SNORT_POTTERY_SHERD,

            Items.TUBE_CORAL,
            Items.TUBE_CORAL_BLOCK,
            Items.TUBE_CORAL_FAN,
            Items.BRAIN_CORAL,
            Items.BRAIN_CORAL_BLOCK,
            Items.BRAIN_CORAL_FAN,
            Items.BUBBLE_CORAL,
            Items.BUBBLE_CORAL_BLOCK,
            Items.BUBBLE_CORAL_FAN,
            Items.FIRE_CORAL,
            Items.FIRE_CORAL_BLOCK,
            Items.FIRE_CORAL_FAN,
            Items.HORN_CORAL,
            Items.HORN_CORAL_BLOCK,
            Items.HORN_CORAL_FAN,

            Items.DEAD_TUBE_CORAL,
            Items.DEAD_TUBE_CORAL_BLOCK,
            Items.DEAD_TUBE_CORAL_FAN,
            Items.DEAD_BRAIN_CORAL,
            Items.DEAD_BRAIN_CORAL_BLOCK,
            Items.DEAD_BRAIN_CORAL_FAN,
            Items.DEAD_BUBBLE_CORAL,
            Items.DEAD_BUBBLE_CORAL_BLOCK,
            Items.DEAD_BUBBLE_CORAL_FAN,
            Items.DEAD_FIRE_CORAL,
            Items.DEAD_FIRE_CORAL_BLOCK,
            Items.DEAD_FIRE_CORAL_FAN,
            Items.DEAD_HORN_CORAL,
            Items.DEAD_HORN_CORAL_BLOCK,
            Items.DEAD_HORN_CORAL_FAN,

            Items.WAXED_COPPER_BLOCK,
            Items.WAXED_CUT_COPPER,
            Items.WAXED_CUT_COPPER_SLAB,
            Items.WAXED_CUT_COPPER_STAIRS,
            Items.WAXED_EXPOSED_COPPER,
            Items.EXPOSED_COPPER,
            Items.WAXED_EXPOSED_CUT_COPPER,
            Items.EXPOSED_CUT_COPPER,
            Items.WAXED_EXPOSED_CUT_COPPER_SLAB,
            Items.EXPOSED_CUT_COPPER_SLAB,
            Items.WAXED_EXPOSED_CUT_COPPER_STAIRS,
            Items.EXPOSED_CUT_COPPER_STAIRS,
            Items.WAXED_WEATHERED_COPPER,
            Items.WEATHERED_COPPER,
            Items.WAXED_WEATHERED_CUT_COPPER,
            Items.WEATHERED_CUT_COPPER,
            Items.WAXED_WEATHERED_CUT_COPPER_SLAB,
            Items.WEATHERED_CUT_COPPER_SLAB,
            Items.WAXED_WEATHERED_CUT_COPPER_STAIRS,
            Items.WEATHERED_CUT_COPPER_STAIRS,
            Items.WAXED_OXIDIZED_COPPER,
            Items.OXIDIZED_COPPER,
            Items.WAXED_OXIDIZED_CUT_COPPER,
            Items.OXIDIZED_CUT_COPPER,
            Items.WAXED_OXIDIZED_CUT_COPPER_SLAB,
            Items.OXIDIZED_CUT_COPPER_SLAB,
            Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
            Items.OXIDIZED_CUT_COPPER_STAIRS,

            Items.BLACK_CONCRETE,
            Items.BLUE_CONCRETE,
            Items.BROWN_CONCRETE,
            Items.CYAN_CONCRETE,
            Items.GRAY_CONCRETE,
            Items.GREEN_CONCRETE,
            Items.LIGHT_BLUE_CONCRETE,
            Items.LIGHT_GRAY_CONCRETE,
            Items.LIME_CONCRETE,
            Items.MAGENTA_CONCRETE,
            Items.ORANGE_CONCRETE,
            Items.PINK_CONCRETE,
            Items.PURPLE_CONCRETE,
            Items.RED_CONCRETE,
            Items.WHITE_CONCRETE,
            Items.YELLOW_CONCRETE,

            Items.CHAINMAIL_BOOTS,
            Items.CHAINMAIL_CHESTPLATE,
            Items.CHAINMAIL_HELMET,
            Items.CHAINMAIL_LEGGINGS,

            Items.CHIPPED_ANVIL,
            Items.DAMAGED_ANVIL,

            Items.COBWEB,
            Items.COCOA_BEANS,

            Items.CREEPER_BANNER_PATTERN,
            Items.FLOWER_BANNER_PATTERN,
            Items.PIGLIN_BANNER_PATTERN,
            Items.GLOBE_BANNER_PATTERN,
            Items.MOJANG_BANNER_PATTERN,
            Items.SKULL_BANNER_PATTERN,

            Items.KELP,
            Items.ECHO_SHARD,
            Items.EGG,
            Items.FEATHER,
            Items.FERN,
            Items.FILLED_MAP,
            Items.FLINT,
            Items.GHAST_TEAR,
            Items.GLOW_INK_SAC,
            Items.GLOW_LICHEN,
            Items.GLOWSTONE_DUST,
            Items.GOAT_HORN,
            Items.HANGING_ROOTS,
            Items.INK_SAC,
            Items.LARGE_FERN,
            Items.LEATHER,
            Items.ARMADILLO_SCUTE,
            Items.NAME_TAG,
            Items.NAUTILUS_SHELL,
            Items.NETHER_STAR,
            Items.NETHER_SPROUTS,
            Items.VINE,
            Items.TWISTING_VINES,
            Items.WEEPING_VINES,
            Items.QUARTZ,
            Items.SAND,
            Items.RED_SAND,
            Items.ROOTED_DIRT,
            Items.TURTLE_SCUTE,
            Items.SEA_PICKLE,
            Items.SEAGRASS,
            Items.SHULKER_SHELL,
            Items.SKELETON_SKULL,
            Items.SPECTRAL_ARROW,
            Items.SUGAR_CANE,
            Items.TOTEM_OF_UNDYING,
            Items.TRIDENT,
            Items.WARPED_FUNGUS,
            Items.CRIMSON_FUNGUS,
            Items.CRIMSON_STEM,
            Items.STRIPPED_CRIMSON_STEM,
            Items.WARPED_ROOTS,
            Items.WARPED_STEM,
            Items.STRIPPED_WARPED_STEM,
            Items.WHEAT,
            Items.WRITTEN_BOOK,
            Items.AMETHYST_CLUSTER,
            Items.SMALL_AMETHYST_BUD,
            Items.MEDIUM_AMETHYST_BUD,
            Items.LARGE_AMETHYST_BUD
    ));

    public static ArrayList<String> getPossibleItems(String filter) {
        var returnItems = new ArrayList<String>();
        for (var item : Registries.ITEM) {
            var id = Registries.ITEM.getId(item).toString();
            if (id.toLowerCase().contains(filter) && !CreativeOnlyItems.contains(item))
                returnItems.add(id);
        }
        return returnItems;
    }

    public static ArrayList<String> getBreakableItems(String filter) {
        var returnItems = new ArrayList<String>();
        for (var item : BreakableItems) {
            var id = Registries.ITEM.getId(item).toString();
            if (id.toLowerCase().contains(filter))
                returnItems.add(id);
        }
        return returnItems;
    }

    public static ArrayList<String> getCraftableItems(String filter) {
        var returnItems = new ArrayList<String>();
        for (var item : Registries.ITEM) {
            var id = Registries.ITEM.getId(item).toString();
            if (id.toLowerCase().contains(filter) && !UncraftableItems.contains(item) && !CreativeOnlyItems.contains(item))
                returnItems.add(id);
        }
        return returnItems;
    }

    public static ArrayList<String> getPossibleBlocks(String filter) {
        var returnBlocks = new ArrayList<String>();
        for (var block : Registries.BLOCK) {
            var id = Registries.BLOCK.getId(block).toString();
            if (id.toLowerCase().contains(filter) && !CreativeOnlyItems.contains(block.asItem()))
                returnBlocks.add(id);
        }
        return returnBlocks;
    }

    public static ArrayList<String> getPossibleToKillEntities(String filter) {
        var returnEntities = new ArrayList<String>();
        for (var entity : Registries.ENTITY_TYPE) {
            var id = Registries.ENTITY_TYPE.getId(entity).toString();
            if (id.toLowerCase().contains(filter) && !ImpossibleToKillEntities.contains(entity))
                returnEntities.add(id);
        }
        return returnEntities;
    }

    public static ArrayList<String> getPossibleToBeKillByEntities(String filter) {
        var returnEntities = new ArrayList<String>();
        for (var entity : Registries.ENTITY_TYPE) {
            var id = Registries.ENTITY_TYPE.getId(entity).toString();
            if (id.toLowerCase().contains(filter) && !ImpossibleToBeKillByEntities.contains(entity))
                returnEntities.add(id);
        }
        return returnEntities;
    }
}
