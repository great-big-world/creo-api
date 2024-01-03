package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * Events related to {@link AnimalEntity}.
 */
public final class AnimalEntityEvents {
    /**
     * An event that is called when two animals have begun breeding, before a baby is born.
     */
    public static final Event<PreBreed> BREED_PRE = EventFactory.createArrayBacked(PreBreed.class,
            listeners -> (world, animal, other, baby) -> {
                for (PreBreed event : listeners) {
                    return event.onBreed(world, animal, other, baby);
                }

                return true;
            }
    );

    /**
     * An event that is called when two animals have finished breeding, after a baby is born.
     */
    public static final Event<PostBreed> BREED_POST = EventFactory.createArrayBacked(PostBreed.class,
            listeners -> (world, animal, other, baby) -> {
                for (PostBreed event : listeners) {
                    event.onBreed(world, animal, other, baby);
                }
            }
    );

    public static final Event<GrowUp> GROW_UP = EventFactory.createArrayBacked(GrowUp.class,
            listeners -> (player, animal, age, overGrow) -> {
                for (GrowUp event : listeners) {
                    return event.onGrowUp(player, animal, age, overGrow);
                }

                return true;
            }
    );

    public static final Event<Love> LOVE = EventFactory.createArrayBacked(Love.class,
            listeners -> (player, animal, age, overGrow) -> {
                for (Love event : listeners) {
                    return event.onLove(player, animal, age, overGrow);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface PreBreed {
        /**
         * Called when two animals have begun breeding, before a baby is born.
         *
         * <p> Return false to stop the animals from breeding.
         *
         * @param serverWorld the world
         * @param animal the first animal
         * @param other the second animal
         * @param baby the baby
         */
        boolean onBreed(ServerWorld serverWorld, AnimalEntity animal, AnimalEntity other, PassiveEntity baby);
    }

    @FunctionalInterface
    public interface PostBreed {
        /**
         * Called when two animals have finished breeding, after a baby is born.
         *
         * @param serverWorld the world
         * @param animal the first animal
         * @param other the second animal
         * @param baby the baby
         */
        void onBreed(ServerWorld serverWorld, AnimalEntity animal, AnimalEntity other, PassiveEntity baby);
    }

    @FunctionalInterface
    public interface GrowUp {
        boolean onGrowUp(PlayerEntity player, PassiveEntity animal, int age, boolean overGrow);
    }

    @FunctionalInterface
    public interface Love {
        boolean onLove(PlayerEntity player, PassiveEntity animal, int age, boolean overGrow);
    }
}
