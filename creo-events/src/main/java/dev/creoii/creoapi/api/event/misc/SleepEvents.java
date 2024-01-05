package dev.creoii.creoapi.api.event.misc;

import com.mojang.datafixers.util.Either;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Unit;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Events related to sleeping.
 *
 * @see net.minecraft.block.BedBlock
 */
public class SleepEvents {
    /**
     * An event called when a bed explodes.
     */
    public static final Event<Explode> EXPLODE = EventFactory.createArrayBacked(Explode.class,
            listeners -> (state, world, pos, player, hand, hit) -> {
                for (Explode event : listeners) {
                    return event.onExplode(state, world, pos, player, hand, hit);
                }

                return true;
            }
    );

    /**
     * An event called when an entity attempts to sleep in a bed.
     */
    public static final Event<Sleep> SLEEP = EventFactory.createArrayBacked(Sleep.class,
            listeners -> (entity, pos, either) -> {
                for (Sleep event : listeners) {
                    return event.onSleep(entity, pos, either);
                }

                return true;
            }
    );

    /**
     * An event called when an entity wakes up from a bed.
     */
    public static final Event<WakeUp> WAKE_UP = EventFactory.createArrayBacked(WakeUp.class,
            listeners -> (entity, skipSleepTimer, updateSleepingPlayers) -> {
                for (WakeUp event : listeners) {
                    return event.onWakeUp(entity, skipSleepTimer, updateSleepingPlayers);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface Explode {
        /**
         * Called when a bed explodes.
         * @param state the bed blockstate
         * @param world the world
         * @param pos the block pos
         * @param player the player
         * @param hand the hand used
         * @param hit the hitresult
         * @return true to explode or false to ignore the explosion
         */
        boolean onExplode(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit);
    }

    @FunctionalInterface
    public interface Sleep {
        /**
         * Called when an entity attempts to sleep in a bed.
         * @param entity the entity
         * @param pos the block pos
         * @param either the {@link net.minecraft.entity.player.PlayerEntity.SleepFailureReason}, or {@link Unit#INSTANCE} if sleeping is allowed
         * @return true to let the entity sleep or false to not sleep
         */
        boolean onSleep(Entity entity, BlockPos pos, Either<PlayerEntity.SleepFailureReason, Unit> either);
    }

    @FunctionalInterface
    public interface WakeUp {
        /**
         * Called when an entity wakes up from a bed.
         * @param entity the entity
         * @param skipSleepTimer whether to skip the sleep timer or not
         * @param updateSleepingPlayers whether to update other sleeping players or not
         * @return true to wake up the entity or false to keep them in bed
         */
        boolean onWakeUp(Entity entity, boolean skipSleepTimer, boolean updateSleepingPlayers);
    }
}
