package dev.creoii.creoapi.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public interface CreoBlock {
    /**
     * Called when an entity is looking at the block.
     * @param world the world
     * @param state the blockstate
     * @param hitResult the raycast
     * @param looker the entity looking at the block
     * @param squaredDistance the squared distance from the block to the looker
     */
    void onLookAt(World world, BlockState state, BlockHitResult hitResult, Entity looker, double squaredDistance);
}
