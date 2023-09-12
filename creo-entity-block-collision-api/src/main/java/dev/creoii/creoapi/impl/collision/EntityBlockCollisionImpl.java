package dev.creoii.creoapi.impl.collision;

import dev.creoii.creoapi.api.collision.EntityBlockCollision;
import dev.creoii.creoapi.api.collision.EntityBlockCollisionRegistry;
import dev.creoii.creoapi.impl.collision.util.EntityBlockCollisionSpliterator;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.CollisionView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class EntityBlockCollisionImpl {
    public static void applyEntityBlockCollision(CollisionView collisionView, @Nullable Entity entity, Box box, CallbackInfoReturnable<Iterable<VoxelShape>> cir) {
        if (entity != null) {
            if (EntityBlockCollisionRegistry.getInteractions().containsKey(entity.getType())) {
                EntityBlockCollision collision = EntityBlockCollisionRegistry.getInteractions().get(entity.getType());
                cir.setReturnValue(() -> new EntityBlockCollisionSpliterator(collisionView, entity, box, collision.getPredicate()));
            }
        }
    }

    public static void applyEntityBlockCollisionLithium(World world, @Nullable Entity entity, Box box, CallbackInfoReturnable<List<VoxelShape>> cir) {
        if (entity != null) {
            if (EntityBlockCollisionRegistry.getInteractions().containsKey(entity.getType())) {
                EntityBlockCollision collision = EntityBlockCollisionRegistry.getInteractions().get(entity.getType());
                cir.setReturnValue(new EntityBlockCollisionSpliterator(world, entity, box, collision.getPredicate()).collectAll());
            }
        }
    }
}
