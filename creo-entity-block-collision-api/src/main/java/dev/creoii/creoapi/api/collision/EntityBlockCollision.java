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

/**
 * Allows the specification of certain blocks that an {@link EntityType} will not collide with.
 */
public class EntityBlockCollision {
    private static final Map<EntityType<?>, EntityBlockCollision> INTERACTIONS = new HashMap<>();
    private final Predicate<EntityBlockCollisionContext> contextPredicate;

    private EntityBlockCollision(Predicate<EntityBlockCollisionContext> contextPredicate) {
        this.contextPredicate = contextPredicate;
    }

    /**
     * Creates a blacklist via a preset array of blocks.
     */
    public static EntityBlockCollision create(Predicate<EntityBlockCollisionContext> contextPredicate) {
        return new EntityBlockCollision(contextPredicate);
    }

    /**
     * Registers a blacklist to the specified entity type.
     */
    public static void register(EntityType<?> entityType, EntityBlockCollision blacklist) {
        INTERACTIONS.put(entityType, blacklist);
    }

    public static Map<EntityType<?>, EntityBlockCollision> getInteractions() {
        return INTERACTIONS;
    }

    public Predicate<EntityBlockCollisionContext> getPredicate() {
        return contextPredicate;
    }

    public record EntityBlockCollisionContext(CollisionView world, Entity entity, BlockPos.Mutable pos, BlockState state, BlockView chunk) { }
}
