package dev.creoii.creoapi.api.collision;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;

import java.util.function.Predicate;

public class EntityBlockCollision {
    private final EntityType<?> entityType;
    private final Predicate<EntityBlockCollisionContext> contextPredicate;

    public EntityBlockCollision(EntityType<?> entityType, Predicate<EntityBlockCollisionContext> contextPredicate) {
        this.entityType = entityType;
        this.contextPredicate = contextPredicate;
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public Predicate<EntityBlockCollisionContext> getPredicate() {
        return contextPredicate;
    }

    public record EntityBlockCollisionContext(CollisionView world, Entity entity, BlockPos.Mutable pos, BlockState state, BlockView chunk) { }
}
