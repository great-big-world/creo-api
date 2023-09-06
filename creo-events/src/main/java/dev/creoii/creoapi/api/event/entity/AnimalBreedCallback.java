package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;

public interface AnimalBreedCallback {
    /**
     * Callback for when two animals are breeding, before breeding actually occurs.
     * <p>
     * Return false to stop the animals from breeding.
     */
    interface Pre {
        Event<AnimalBreedCallback.Pre> EVENT = EventFactory.createArrayBacked(AnimalBreedCallback.Pre.class,
                (listeners) -> (world, animal, other, baby) -> {
                    for (AnimalBreedCallback.Pre event : listeners) {
                        return event.breed(world, animal, other, baby);
                    }

                    return true;
                }
        );

        boolean breed(ServerWorld serverWorld, AnimalEntity animal, AnimalEntity other, PassiveEntity baby);
    }

    /**
     * Callback for when two animals are breeding, after breeding occurs.
     */
    interface Post {
        Event<AnimalBreedCallback.Post> EVENT = EventFactory.createArrayBacked(AnimalBreedCallback.Post.class,
                (listeners) -> (world, animal, other, baby) -> {
                    for (AnimalBreedCallback.Post event : listeners) {
                        event.breed(world, animal, other, baby);
                    }
                }
        );

        void breed(ServerWorld serverWorld, AnimalEntity animal, AnimalEntity other, PassiveEntity baby);
    }
}
