package net.encrypted.challenges.util;

import net.minecraft.block.Block;
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

            Items.COMMAND_BLOCK,
            Items.CHAIN_COMMAND_BLOCK,
            Items.REPEATING_COMMAND_BLOCK,
            Items.COMMAND_BLOCK_MINECART,
            Items.JIGSAW,
            Items.STRUCTURE_BLOCK,
            Items.STRUCTURE_VOID,
            Items.BARRIER,
            Items.DEBUG_STICK,

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
            Items.ZOMBIFIED_PIGLIN_SPAWN_EGG
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

    public static ArrayList<Item> getPossibleItems() {
        var returnItems = new ArrayList<Item>();
        for (var item : Registries.ITEM) {
            if (!CreativeOnlyItems.contains(item))
                returnItems.add(item);
        }
        return returnItems;
    }

    public static ArrayList<Block> getPossibleBlocks() {
        var returnBlocks = new ArrayList<Block>();
        for (var block : Registries.BLOCK) {
            if (!CreativeOnlyItems.contains(block.asItem()))
                returnBlocks.add(block);
        }
        return returnBlocks;
    }

    public static ArrayList<EntityType<?>> getPossibleToKillEntities() {
        var returnEntities = new ArrayList<EntityType<?>>();
        for (var entity : Registries.ENTITY_TYPE) {
            if (!ImpossibleToKillEntities.contains(entity))
                returnEntities.add(entity);
        }
        return returnEntities;
    }

    public static ArrayList<EntityType<?>> getPossibleToBeKillByEntities() {
        var returnEntities = new ArrayList<EntityType<?>>();
        for (var entity : Registries.ENTITY_TYPE) {
            if (!ImpossibleToBeKillByEntities.contains(entity))
                returnEntities.add(entity);
        }
        return returnEntities;
    }
}
