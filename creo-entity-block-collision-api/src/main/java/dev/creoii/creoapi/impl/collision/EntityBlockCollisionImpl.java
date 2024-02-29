package dev.creoii.creoapi.impl.collision;

import dev.creoii.creoapi.api.collision.EntityBlockCollisionContext;
import dev.creoii.creoapi.api.collision.EntityBlockCollisionRegistry;
import dev.creoii.creoapi.api.collision.util.EntityBlockCollisionSpliterator;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.CollisionView;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@ApiStatus.Internal
public final class EntityBlockCollisionImpl {
    public static void applyEntityBlockCollision(CollisionView collisionView, @Nullable Entity entity, Box box, CallbackInfoReturnable<Iterable<VoxelShape>> cir) {
        if (entity != null) {
            Predicate<EntityBlockCollisionContext> collision = EntityBlockCollisionRegistry.getCollisionOfType(entity.getType());
            if (collision != null) {
                cir.setReturnValue(() -> new EntityBlockCollisionSpliterator(collisionView, entity, box, collision));
            }
        }
    }

    public static void applyEntityBlockCollisionLithium(World world, @Nullable Entity entity, Box box, CallbackInfoReturnable<List<VoxelShape>> cir) {
        if (entity != null) {
            Predicate<EntityBlockCollisionContext> collision = EntityBlockCollisionRegistry.getCollisionOfType(entity.getType());
            if (collision != null) {
                cir.setReturnValue(new EntityBlockCollisionSpliterator(world, entity, box, collision).collectAll());
            }
        }
    }
}
