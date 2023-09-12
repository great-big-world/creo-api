package dev.creoii.creoapi.api.collision;

import net.minecraft.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the specification of certain blocks that an {@link EntityType} will not collide with.
 */
public final class EntityBlockCollisionRegistry {
    private static final Map<EntityType<?>, EntityBlockCollision> INTERACTIONS = new HashMap<>();

    /**
     * Registers a block collision interaction to the specified entity type.
     */
    public static void register(EntityType<?> entityType, EntityBlockCollision blacklist) {
        INTERACTIONS.put(entityType, blacklist);
    }

    public static Map<EntityType<?>, EntityBlockCollision> getInteractions() {
        return INTERACTIONS;
    }
}
