package net.encrypted.challenges.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class StatHelper {

    public static int GetBlockStat(StatHandler handler, String itemName) {
        var item = Registries.ITEM.get(new Identifier(itemName));
        var block = Block.getBlockFromItem(item);
        if (block == null || block.getDefaultState().isAir()) return 0;
        return handler.getStat(Stats.MINED, block);
    }

    public static int GetItemStat(StatHandler handler, StatType<Item> statType, String itemName) {
        var item = Registries.ITEM.get(new Identifier(itemName));
        return handler.getStat(statType, item);
    }

    public static int GetMobStat(StatHandler handler, StatType<EntityType<?>> statType, String entityName) {
        var entityType = Registries.ENTITY_TYPE.get(new Identifier(entityName));
        return handler.getStat(statType, entityType);
    }

    public static int GetCustomStat(StatHandler handler, String name) {
        var stat = Registries.CUSTOM_STAT.get(new Identifier(name));
        return handler.getStat(Stats.CUSTOM, stat);
    }
}
