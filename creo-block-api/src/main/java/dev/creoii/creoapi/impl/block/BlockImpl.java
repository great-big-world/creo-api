package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public final class BlockImpl {
    public static void lookAtBlock(Entity entity) {
        HitResult hitResult = entity.raycast(64d, 0f, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockState state = entity.getWorld().getBlockState(blockHitResult.getBlockPos());

            if (state.getBlock() instanceof CreoBlock creoBlock)
                creoBlock.onLookAt(entity.getWorld(), state, blockHitResult, entity, blockHitResult.squaredDistanceTo(entity));
        }
    }
}
