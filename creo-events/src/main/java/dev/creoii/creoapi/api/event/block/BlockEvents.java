package dev.creoii.creoapi.api.event.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Events related to {@link Block}.
 */
public final class BlockEvents {
    /**
     * An event called when a block is placed via a {@link net.minecraft.item.BlockItem}.
     */
    public static final Event<Place> PLACE = EventFactory.createArrayBacked(Place.class,
            listeners -> (block, context) -> {
                for (Place event : listeners) {
                    return event.onPlace(block, context);
                }

                return true;
            }
    );

    /**
     * An event called on the server before a block is broken.
     */
    public static final Event<Break> BREAK = EventFactory.createArrayBacked(Break.class,
            listeners -> (world, player, state, pos) -> {
                for (Break event : listeners) {
                    return event.onBreak(world, player, state, pos);
                }

                return true;
            }
    );

    /**
     * An event called on both the client and server when a blockstate is changed.
     */
    public static final Event<Change> CHANGE = EventFactory.createArrayBacked(Change.class,
            listeners -> (world, pos, newState, oldState, moved) -> {
                for (Change event : listeners) {
                    return event.onChange(world, pos, newState, oldState, moved);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface Place {
        /**
         * Called when a block is placed via a {@link net.minecraft.item.BlockItem}.
         *
         * @param state the blockstate
         * @param context the placement context
         *
         * @return true to place the block or false to ignore the placement.
         */
        boolean onPlace(BlockState state, ItemPlacementContext context);
    }

    @FunctionalInterface
    public interface Break {
        /**
         * Called on the server before a block is broken.
         *
         * @param world the world
         * @param player the player breaking the block
         * @param state the blockstate being broken
         * @param pos the block position
         *
         * @return true to break the block or false to keep the block.
         */
        boolean onBreak(ServerWorld world, ServerPlayerEntity player, BlockState state, BlockPos pos);
    }

    @FunctionalInterface
    public interface Change {
        /**
         * Called on both the client and server when a blockstate is changed.
         * Is not called if {@param newState} or {@param oldState} are considered air.
         *
         * @param world the world
         * @param pos the block position
         * @param newState the state being changed to
         * @param oldState the state being changed from
         * @param moved whether this state change was the result of a move
         */
        boolean onChange(World world, BlockPos pos, BlockState newState, BlockState oldState, boolean moved);
    }
}
