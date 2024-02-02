package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

/**
 * Events related to {@link Entity}.
 */
public final class EntityEvents {
    /**
     * An event that is called on the server when an {@link Entity} is added to a world.
     */
    public static final Event<Spawn> SPAWN = EventFactory.createArrayBacked(Spawn.class,
            listeners -> (world, entity) -> {
                for (Spawn event : listeners) {
                    return event.onSpawn(world, entity);
                }

                return true;
            }
    );

    /**
     * Called on the server when an {@link Entity} is within the bounds of a structure.
     */
    public static final Event<WithinStructure> WITHIN_STRUCTURE = EventFactory.createArrayBacked(WithinStructure.class,
            listeners -> (serverWorld, entity, structureStart) -> {
                for (WithinStructure event : listeners) {
                    event.onWithinStructure(serverWorld, entity, structureStart);
                }
            }
    );

    /**
     * An event that is called when an entity is struck by a {@link LightningEntity}.
     */
    public static final Event<StruckByLightning> STRUCK_BY_LIGHTNING = EventFactory.createArrayBacked(StruckByLightning.class,
            listeners -> (serverWorld, entity, lightning) -> {
                for (StruckByLightning event : listeners) {
                    event.onStruckByLightning(serverWorld, entity, lightning);
                }
            }
    );

    /**
     * An event that is called when an entity moves to another dimension. This is not called for players.
     */
    public static final Event<ChangeDimension> CHANGE_DIMENSION = EventFactory.createArrayBacked(ChangeDimension.class,
            listeners -> (world, destination, entity, copy, teleportTarget) -> {
                for (ChangeDimension event : listeners) {
                    return event.onChangeDimension(world, destination, entity, copy, teleportTarget);
                }

                return true;
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
        boolean onSpawn(ServerWorld serverWorld, Entity entity);
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

    @FunctionalInterface
    public interface StruckByLightning {
        /**
         * Called when an entity is struck by a {@link LightningEntity}.
         * @param serverWorld the world
         * @param entity the entity
         * @param lightning the lightning bolt
         */
        void onStruckByLightning(ServerWorld serverWorld, Entity entity, LightningEntity lightning);
    }

    @FunctionalInterface
    public interface ChangeDimension {
        /**
         * Called on the server for non-player entities when changing dimensions.
         * @param world the world
         * @param destination the destination world
         * @param entity the entity changing dimension
         * @param copy the copy of that entity in the destination world
         * @param teleportTarget the teleport target information
         * @return true to change dimensions or false to cancel the change
         */
        boolean onChangeDimension(World world, World destination, Entity entity, Entity copy, TeleportTarget teleportTarget);
    }
}
