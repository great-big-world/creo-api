package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.entity.EntitySpawnCallback;
import dev.creoii.creoapi.api.event.entity.MobInitializeCallback;
import dev.creoii.creoapi.api.event.entity.WithinStructureCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class EntityEventImpl {
    public static void applyEntitySpawnCallback(ServerWorld serverWorld, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean result = EntitySpawnCallback.EVENT.invoker().spawn(serverWorld, entity);

        if (!result)
            cir.cancel();
    }

    public static void applyMobInitializeCallback(ServerWorldAccess world, MobEntity mob, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        EntityData result = MobInitializeCallback.EVENT.invoker().initialize(world, mob, difficulty, spawnReason, entityData, entityNbt);

        if (result != null) {
            cir.setReturnValue(result);
        }
    }

    public static void applyWithinStructureCallback(World world, Entity entity, BlockPos pos, ChunkPos chunkPos) {
        if (entity.age % 10 != 0 || world.isClient) return;
        ServerWorld serverWorld = (ServerWorld) world;
        for (StructureStart structureStart : serverWorld.getStructureAccessor().getStructureStarts(chunkPos, structure -> true)) {
            if (structureStart.hasChildren() && structureStart.getBoundingBox().contains(pos))
                WithinStructureCallback.EVENT.invoker().enter(serverWorld, entity, structureStart);
        }
    }
}
