package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Events related to {@link net.minecraft.entity.player.PlayerEntity}.
 */
public final class PlayerEntityEvents {
    /**
     * An event called when a {@link PlayerEntity} levels up.
     */
    public static final Event<LevelUp> LEVEL_UP = EventFactory.createArrayBacked(LevelUp.class,
            listeners -> (player, levels) -> {
                for (LevelUp event : listeners) {
                    return event.onLevelUp(player, levels);
                }

                return true;
            }
    );

    public static final Event<Respawn> RESPAWN = EventFactory.createArrayBacked(Respawn.class,
            listeners -> (player, alive) -> {
                for (Respawn event : listeners) {
                    event.onRespawn(player, alive);
                }
            }
    );

    @FunctionalInterface
    public interface LevelUp {
        /**
         * Called when a player levels up.
         *
         * @param player the player
         * @param levels the amount of levels to increase by
         * @return true to level up, or false to stop the level up.
         */
        boolean onLevelUp(PlayerEntity player, int levels);
    }

    @FunctionalInterface
    public interface Respawn {
        /**
         * Called server-side when a player respawns.
         * @param player the player
         * @param alive whether the player is alive or not
         */
        void onRespawn(PlayerEntity player, boolean alive);
    }
}
