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
 * Events related to {@link MobEntity}.
 */
public final class MobEntityEvents {
    /**
     * An event called when a {@link MobEntity} is initialized before being added to a world.
     * Examples of this are a Skeleton being given its Bow, or an animal being turned into a baby.
     *
     * <p> Return null for default behavior to take effect.
     */
    public static final Event<Initialize> INITIALIZE = EventFactory.createArrayBacked(Initialize.class,
            listeners -> (world, mob, difficulty, spawnReason, entityData, nbt) -> {
                for (Initialize event : listeners) {
                    EntityData result = event.onInitialize(world, mob, difficulty, spawnReason, entityData, nbt);

                    if (result != null)
                        return result;
                }

                return entityData;
            }
    );

    @FunctionalInterface
    public interface Initialize {
        /**
         * Called when a {@link MobEntity} is initialized before being added to a world.
         *
         * <p> Return null for default behavior to take effect.
         *
         * @param world the world
         * @param mob the mob
         * @param difficulty the local world difficulty
         * @param spawnReason the reason the mob is being spawned
         * @param entityData the mob's entity data
         * @param entityNbt the mob's nbt
         */
        EntityData onInitialize(ServerWorldAccess world, MobEntity mob, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt);
    }
}
