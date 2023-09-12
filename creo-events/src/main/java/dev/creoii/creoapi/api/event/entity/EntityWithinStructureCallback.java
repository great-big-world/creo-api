package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;

/**
 * Callback for when an entity is within the bounds of a structure. Is called on the server.
 */
public interface EntityWithinStructureCallback {
    Event<EntityWithinStructureCallback> EVENT = EventFactory.createArrayBacked(EntityWithinStructureCallback.class,
            (listeners) -> (serverWorld, entity, structureStart) -> {
                for (EntityWithinStructureCallback event : listeners) {
                    event.withinStructure(serverWorld, entity, structureStart);
                }
            }
    );

    void withinStructure(ServerWorld serverWorld, Entity entity, StructureStart structureStart);
}
