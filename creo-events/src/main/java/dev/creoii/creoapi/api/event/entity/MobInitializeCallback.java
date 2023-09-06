package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;

/**
 * Callback for when a {@link MobEntity} is initialized before being added to a world.
 * Examples of this are a Skeleton being given its Bow, or an animal being turned into a baby.
 *
 * <p>Return null for default behavior to take effect.
 */
public interface MobInitializeCallback {
    Event<MobInitializeCallback> EVENT = EventFactory.createArrayBacked(MobInitializeCallback.class,
            (listeners) -> (world, mob, difficulty, spawnReason, entityData, nbt) -> {
                for (MobInitializeCallback event : listeners) {
                    EntityData result = event.initialize(world, mob, difficulty, spawnReason, entityData, nbt);

                    if (result != null) {
                        return result;
                    }
                }

                return entityData;
            }
    );

    EntityData initialize(ServerWorldAccess world, MobEntity mob, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt);
}
