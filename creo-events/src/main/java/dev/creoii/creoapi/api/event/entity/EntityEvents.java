package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;

/**
 * Events related to {@link Entity}.
 */
public final class EntityEvents {
    /**
     * An event that is called on the server when an {@link Entity} is added to a world.
     */
    public static final Event<Spawn> SPAWN = EventFactory.createArrayBacked(Spawn.class,
            (listeners) -> (world, entity) -> {
                for (Spawn event : listeners) {
                    return event.shouldSpawn(world, entity);
                }

                return true;
            }
    );

    /**
     * Called on the server when an {@link Entity} is within the bounds of a structure.
     */
    public static final Event<WithinStructure> WITHIN_STRUCTURE = EventFactory.createArrayBacked(WithinStructure.class,
            (listeners) -> (serverWorld, entity, structureStart) -> {
                for (WithinStructure event : listeners) {
                    event.onWithinStructure(serverWorld, entity, structureStart);
                }
            }
    );

    @FunctionalInterface
    public interface Spawn {
        /**
         * Called on the server when an {@link Entity} is added to a world.
         *
         * <p> Return false to stop an entity from being added.
         *
         * @param serverWorld the world
         * @param entity the entity
         */
        boolean shouldSpawn(ServerWorld serverWorld, Entity entity);
    }

    @FunctionalInterface
    public interface WithinStructure {
        /**
         * Called on the server when an {@link Entity} is within the bounds of a structure.
         *
         * @param serverWorld the world
         * @param entity the entity
         * @param structureStart the structure
         */
        void onWithinStructure(ServerWorld serverWorld, Entity entity, StructureStart structureStart);
    }
}
