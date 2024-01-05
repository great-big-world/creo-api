package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BlockImpl {
    public static void applyLookAtBlock(Entity entity) {
        HitResult hitResult = entity.raycast(64d, 0f, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockState state = entity.getWorld().getBlockState(blockHitResult.getBlockPos());

            if (state.getBlock() instanceof CreoBlock creoBlock)
                creoBlock.onLookedAt(entity.getWorld(), state, blockHitResult, entity, blockHitResult.squaredDistanceTo(entity));
        }
    }

    public static void applyOnPlacedByStructure(ServerWorldAccess world, BlockPos pivot, StructurePlacementData placementData, Random random, StructureTemplate.StructureBlockInfo structureBlockInfo, BlockPos pos, FluidState fluidState, BlockState state, StructureTemplate structureTemplate) {
        if (state.getBlock() instanceof CreoBlock creoBlock) {
            creoBlock.onPlacedByStructure(world, pos, state, fluidState, random, pivot, structureTemplate, placementData, structureBlockInfo);
        }
    }
}
