package dev.creoii.creoapi.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public interface CreoBlock {
    /**
     * Called when an entity is looking at the block.
     * @param world the world
     * @param state the blockstate
     * @param hitResult the raycast
     * @param looker the entity looking at the block
     * @param squaredDistance the squared distance from the block to the looker
     */
    default void onLookAt(World world, BlockState state, BlockHitResult hitResult, Entity looker, double squaredDistance) {
    }

    /**
     * Called when a block is placed by a structure block, after data structure blocks have been processed.
     * @param structureTemplateManager the structure template manager
     * @param world the world
     * @param structureAccessor the structure accessor
     * @param chunkGenerator the chunk generator
     * @param pos the block pos
     * @param pivot the block pos to pivot relative from
     * @param rotation the block rotation
     * @param box the block box
     * @param random the random
     * @param keepJigsaws whether to keep jigsaw blocks or not
     */
    default void onPlacedByStructure(StructureTemplateManager structureTemplateManager, StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, BlockPos pos, BlockPos pivot, BlockRotation rotation, BlockBox box, Random random, boolean keepJigsaws) {
    }
}
