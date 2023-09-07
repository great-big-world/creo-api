package dev.creoii.creoapi.mixin.collision;

import dev.creoii.creoapi.impl.collision.EntityBlockCollisionImpl;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.CollisionView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CollisionView.class)
public interface CollisionViewMixin {
    @Inject(method = "getBlockCollisions", at = @At("HEAD"), cancellable = true)
    private void creo_entityBlockCollisions(@Nullable Entity entity, Box box, CallbackInfoReturnable<Iterable<VoxelShape>> cir) {
        EntityBlockCollisionImpl.applyEntityBlockCollision((CollisionView) this, entity, box, cir);
    }
}
