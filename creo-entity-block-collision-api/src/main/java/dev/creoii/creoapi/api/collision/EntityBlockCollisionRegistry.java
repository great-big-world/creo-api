package dev.creoii.creoapi.api.collision;

import net.minecraft.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * Allows the specification of certain blocks that an {@link EntityType} will not collide with.
 */
public final class EntityBlockCollisionRegistry {
    private static final List<EntityBlockCollision> INTERACTIONS = new LinkedList<>();

    /**
     * Registers a block collision interaction to the specified entity type.
     */
    public static void register(EntityBlockCollision collision) {
        INTERACTIONS.add(collision);
    }

    @Nullable
    public static EntityBlockCollision getCollisionOfType(EntityType<?> entityType) {
        for (EntityBlockCollision collision : INTERACTIONS) {
            if (collision.getEntityType() == entityType)
                return collision;
        }
        return null;
    }
}
