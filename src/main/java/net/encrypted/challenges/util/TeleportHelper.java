package net.encrypted.challenges.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;

public class TeleportHelper {
    public static ServerPlayerEntity teleport(ServerPlayerEntity player, ServerWorld world, double x, double y, double z, float yaw, float pitch) throws CommandSyntaxException {
        player.stopRiding();
        if (player.isSleeping())
            player.wakeUp(true, true);
        var pos = new Vec3d(x, y, z);
        var velocity = new Vec3d(0, 0, 0);
        return (ServerPlayerEntity) player.teleportTo(new TeleportTarget(world, pos, velocity, yaw, pitch, (entity) -> {}));
    }
}
