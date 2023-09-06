package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

/**
 * Callback for when an entity is added to a world. Is called on the server.
 * <p>
 * Return false to stop an entity from being added.
 */
public interface EntitySpawnCallback {
    Event<EntitySpawnCallback> EVENT = EventFactory.createArrayBacked(EntitySpawnCallback.class,
            (listeners) -> (world, entity) -> {
                for (EntitySpawnCallback event : listeners) {
                    return event.spawn(world, entity);
                }

                return true;
            }
    );

    boolean spawn(ServerWorld serverWorld, Entity entity);
}
