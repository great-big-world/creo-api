package dev.creoii.creoapi.api.event.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Events related to {@link net.minecraft.block.CropBlock}.
 */
public final class CropEvents {
    /**
     * An event called when a {@link net.minecraft.block.CropBlock} grows naturally or when bone-mealed.
     */
    public static final Event<Grow> GROW = EventFactory.createArrayBacked(Grow.class,
            listeners -> (world, pos, state, growState, age, moisture) -> {
                for (Grow event : listeners) {
                    return event.onGrow(world, pos, state, growState, age, moisture);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface Grow {
        /**
         * Called when a {@link net.minecraft.block.CropBlock} grows naturally or when bone-mealed.
         * @param world the world
         * @param pos the block pos
         * @param state the blockstate
         * @param growState the grown blockstate
         * @param age the age of the crop
         * @param moisture the moisture of the farmland
         * @return true to grow the crop or false to ignore the growth.
         */
        boolean onGrow(World world, BlockPos pos, BlockState state, BlockState growState, int age, float moisture);
    }
}