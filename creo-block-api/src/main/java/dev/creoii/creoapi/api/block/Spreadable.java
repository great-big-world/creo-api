package dev.creoii.creoapi.api.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An interface for Blocks to have spreading behavior, like how {@link Blocks#GRASS_BLOCK} spread to {@link Blocks#DIRT}.
 */
public interface Spreadable {
    /**
     * @return the block the parent decays into when underneath another block.
     */
    Block getDead();

    /**
     * @param state The blockstate
     * @param world The server world
     * @param pos The block pos
     * @return Whether the parent block can survive or not. If it cannot, it decays into {@link Spreadable#getDead()}.
     */
    default boolean canSurvive(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        return defaultCanSurvive(state, world, pos, random);
    }

    /**
     * @param state The blockstate
     * @param world The server world
     * @param pos The block pos
     * @return Whether the parent block can spread or not.
     */
    default boolean canSpread(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        return defaultCanSpread(state, world, pos, random);
    }

    /**
     * @return A list of {@link Spread} instances which determine what blocks can be spread to and what those blocks turn into.
     */
    List<Spread> getSpreads();

    /**
     * @param state The blockstate
     * @param world The server world
     * @param pos The block pos
     * Called post-spread to allow for behavior after spreading has occurred.
     */
    default void onSpread(BlockState state, ServerWorld world, BlockPos pos, Random random) {}

    static boolean defaultCanSurvive(BlockState state, WorldView world, BlockPos pos, Random random) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        }
        if (blockState.getFluidState().getLevel() == 8) {
            return false;
        }
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    static boolean defaultCanSpread(BlockState state, WorldView world, BlockPos pos, Random random) {
        return world.getLightLevel(pos.up()) >= 9;
    }

    /**
     * @param target Target converted block.
     * @param conversion Block to convert to, or null to use the parent block.
     */
    record Spread(Block target, @Nullable Block conversion) {
        public static Spread of(Block target) {
            return new Spread(target, null);
        }

        public static Spread of(Block target, @Nullable Block conversion) {
            return new Spread(target, conversion);
        }
    }
}
