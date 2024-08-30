package dev.creoii.creoapi.api.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

/**
 * General-purpose Creo-provided extensions for blocks.
 */
public interface CreoBlock {
    /**
     * Called when an entity is looking at the block.
     * @param world the world
     * @param state the blockstate
     * @param hitResult the raycast
     * @param looker the entity looking at the block
     * @param squaredDistance the squared distance from the block to the looker
     */
    default void onLookedAt(World world, BlockState state, BlockHitResult hitResult, Entity looker, double squaredDistance) {
    }

    /**
     * Called when a block is placed by a structure block, after data structure blocks have been processed.
     * @param world the world
     * @param pos the block pos
     * @param state the blockstate
     * @param fluidState the fluidstate
     * @param random a random instance
     * @param pivot the block pos to pivot relative from
     * @param structureTemplate the structure template
     * @param placementData the structure placement data
     * @param structureBlockInfo the block information at the block pos
     */
    default void onPlacedByStructure(ServerWorldAccess world, BlockPos pos, BlockState state, FluidState fluidState, Random random, BlockPos pivot, StructureTemplate structureTemplate, StructurePlacementData placementData, StructureTemplate.StructureBlockInfo structureBlockInfo) {
    }

    /**
     * Determines whether an entity can collide adjacent, thus called {@link CreoBlock#onAdjacentEntityCollision}.
     * @param entity the entity
     * @param state the blockstate
     * @param pos the block's position
     * @return whether the entity can collide adjacent with the blockstate
     * @since 0.2.1
     */
    default boolean canEntityCollideAdjacent(Entity entity, BlockState state, BlockPos pos) {
        return false;
    }

    /**
     * Called when an entity is next to a block.
     * @param entity the entity
     * @param state the blockstate
     * @param pos the block's position
     * @since 0.2.1
     */
    default void onAdjacentEntityCollision(Entity entity, BlockState state, BlockPos pos) {
    }

    /**
     * Called client-side to render a blockstate as an overlay to the block.
     * @param state the blockstate
     * @param pos the block's position
     * @param random a random instance
     * @return the block to render as an overlay to the block
     * @since 0.2.2
     */
    @Environment(EnvType.CLIENT)
    default BlockState getOverlayState(BlockState state, BlockPos pos, Random random) {
        return Blocks.AIR.getDefaultState();
    }
}
