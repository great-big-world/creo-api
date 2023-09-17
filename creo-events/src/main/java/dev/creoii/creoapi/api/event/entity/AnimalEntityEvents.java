package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * Events related to {@link AnimalEntity}.
 */
public final class AnimalEntityEvents {
    /**
     * An event that is called when two animals have begun breeding, before a baby is born.
     */
    public static final Event<Pre> BREED_PRE = EventFactory.createArrayBacked(Pre.class,
            listeners -> (world, animal, other, baby) -> {
                for (Pre event : listeners) {
                    return event.onBreed(world, animal, other, baby);
                }

                return true;
            }
    );

    /**
     * An event that is called when two animals have finished breeding, after a baby is born.
     */
    public static final Event<Post> BREED_POST = EventFactory.createArrayBacked(Post.class,
            listeners -> (world, animal, other, baby) -> {
                for (Post event : listeners) {
                    event.onBreed(world, animal, other, baby);
                }
            }
    );

    @FunctionalInterface
    public interface Pre {
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
    public interface Post {
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
}
