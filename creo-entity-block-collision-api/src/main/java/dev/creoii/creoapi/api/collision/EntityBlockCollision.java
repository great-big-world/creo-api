package dev.creoii.creoapi.api.collision;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EntityBlockCollision {
    private final Predicate<EntityBlockCollisionContext> contextPredicate;

    private EntityBlockCollision(Predicate<EntityBlockCollisionContext> contextPredicate) {
        this.contextPredicate = contextPredicate;
    }

    /**
     * Creates an block collision interaction.
     */
    public static EntityBlockCollision create(Predicate<EntityBlockCollisionContext> contextPredicate) {
        return new EntityBlockCollision(contextPredicate);
    }

    public Predicate<EntityBlockCollisionContext> getPredicate() {
        return contextPredicate;
    }

    public record EntityBlockCollisionContext(CollisionView world, Entity entity, BlockPos.Mutable pos, BlockState state, BlockView chunk) { }
}
