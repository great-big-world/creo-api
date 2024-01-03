package dev.creoii.creoapi.api.event.misc;

import com.mojang.datafixers.util.Either;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Unit;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Events related to sleeping.
 *
 * @see net.minecraft.block.BedBlock
 */
public class SleepEvents {
    public static final Event<Explode> EXPLODE = EventFactory.createArrayBacked(Explode.class,
            listeners -> (state, world, pos, player, hand, hit) -> {
                for (Explode event : listeners) {
                    return event.onExplode(state, world, pos, player, hand, hit);
                }

                return true;
            }
    );

    public static final Event<WakeVillager> WAKE_VILLAGER = EventFactory.createArrayBacked(WakeVillager.class,
            listeners -> (state, world, pos, player, hand, hit, sleepingVillagers) -> {
                for (WakeVillager event : listeners) {
                    return event.onWakeVillager(state, world, pos, player, hand, hit, sleepingVillagers);
                }

                return true;
            }
    );

    public static final Event<Sleep> SLEEP = EventFactory.createArrayBacked(Sleep.class,
            listeners -> (entity, pos, either) -> {
                for (Sleep event : listeners) {
                    return event.onSleep(entity, pos, either);
                }

                return true;
            }
    );

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
        boolean onExplode(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit);
    }

    @FunctionalInterface
    public interface WakeVillager {
        boolean onWakeVillager(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, List<VillagerEntity> sleepingVillagers);
    }

    @FunctionalInterface
    public interface Sleep {
        boolean onSleep(Entity entity, BlockPos pos, Either<PlayerEntity.SleepFailureReason, Unit> either);
    }

    @FunctionalInterface
    public interface WakeUp {
        boolean onWakeUp(Entity entity, boolean skipSleepTimer, boolean updateSleepingPlayers);
    }
}
