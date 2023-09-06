package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;

/**
 * Callback for when the player is within the bounds of a structure. Is called on the server.
 */
public interface WithinStructureCallback {
    Event<WithinStructureCallback> EVENT = EventFactory.createArrayBacked(WithinStructureCallback.class,
            (listeners) -> (serverWorld, entity, structureStart) -> {
                for (WithinStructureCallback event : listeners) {
                    event.enter(serverWorld, entity, structureStart);
                }
            }
    );

    void enter(ServerWorld serverWorld, Entity entity, StructureStart structureStart);
}
