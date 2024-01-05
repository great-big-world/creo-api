package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

/**
 * Events related to {@link AnimalEntity}.
 */
public final class AnimalEntityEvents {
    /**
     * An event that is called when two animals have begun breeding, before a baby is born.
     */
    public static final Event<PreBreed> PRE_BREED = EventFactory.createArrayBacked(PreBreed.class,
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
    public static final Event<PostBreed> POST_BREED = EventFactory.createArrayBacked(PostBreed.class,
            listeners -> (world, animal, other, baby) -> {
                for (PostBreed event : listeners) {
                    event.onBreed(world, animal, other, baby);
                }
            }
    );

    /**
     * An event that is called when an animal is fed food.
     */
    public static final Event<Eat> EAT = EventFactory.createArrayBacked(Eat.class,
            listeners -> (player, hand, food, animal, age, overGrow) -> {
                for (Eat event : listeners) {
                    return event.onEat(player, hand, food, animal, age, overGrow);
                }

                return true;
            }
    );

    /**
     * An event that is called when a baby animal grows up.
     */
    public static final Event<GrowUp> GROW_UP = EventFactory.createArrayBacked(GrowUp.class,
            listeners -> (passive, age) -> {
                for (GrowUp event : listeners) {
                    return event.onGrowUp(passive, age);
                }

                return age >= 0;
            }
    );

    /**
     * An event that is called when two animals have begun breeding, when they spawn heart particles.
     */
    public static final Event<Love> LOVE = EventFactory.createArrayBacked(Love.class,
            listeners -> (player, passive, age, overGrow) -> {
                for (Love event : listeners) {
                    return event.onLove(player, passive, age, overGrow);
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
    public interface Eat {
        boolean onEat(PlayerEntity player, Hand hand, ItemStack food, AnimalEntity animal, int age, boolean overGrow);
    }

    @FunctionalInterface
    public interface GrowUp {
        boolean onGrowUp(PassiveEntity passive, int age);
    }

    @FunctionalInterface
    public interface Love {
        boolean onLove(PlayerEntity player, PassiveEntity animal, int age, boolean overGrow);
    }
}
