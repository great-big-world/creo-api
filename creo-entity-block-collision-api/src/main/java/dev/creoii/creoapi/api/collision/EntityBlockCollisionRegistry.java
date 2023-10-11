package dev.creoii.creoapi.api.collision;

import net.minecraft.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Allows the specification of certain blocks that an {@link EntityType} will not collide with.
 */
public final class EntityBlockCollisionRegistry {
    private static final Map<EntityType<?>, Predicate<EntityBlockCollisionContext>> INTERACTIONS = new HashMap<>();

    /**
     * Registers a block collision interaction to the specified entity type.
     */
    public static void register(EntityType<?> entityType, Predicate<EntityBlockCollisionContext> collision) {
        INTERACTIONS.put(entityType, collision);
    }

    public static Map<EntityType<?>, Predicate<EntityBlockCollisionContext>> getCollisionInteractions() {
        return INTERACTIONS;
    }

    @Nullable
    public static Predicate<EntityBlockCollisionContext> getCollisionOfType(EntityType<?> entityType) {
        return INTERACTIONS.getOrDefault(entityType, null);
    }
}
