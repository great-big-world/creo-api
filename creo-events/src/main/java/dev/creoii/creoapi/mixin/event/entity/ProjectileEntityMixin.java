package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo_applyProjectFireEvent(EntityType<? extends ProjectileEntity> entityType, World world, CallbackInfo ci) {
        EntityEventImpl.applyProjectileFireEvent((ProjectileEntity) (Object) this);
    }

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void creo_applyProjectileImpactEvent(HitResult hitResult, CallbackInfo ci) {
        EntityEventImpl.applyProjectileImpactEvent((ProjectileEntity) (Object) this, hitResult, ci);
    }
}
