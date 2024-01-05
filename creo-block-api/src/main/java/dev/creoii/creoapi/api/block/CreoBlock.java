package dev.creoii.creoapi.api.block;

import net.minecraft.block.BlockState;
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
 * General-purpose Creo-provided extensions for Block subclasses.
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
}
