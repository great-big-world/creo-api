package dev.creoii.creoapi.api.collision;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;

public record EntityBlockCollisionContext(CollisionView world, Entity entity, BlockPos.Mutable pos, BlockState state, BlockView chunk) {
}
