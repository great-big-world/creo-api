package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
    @Inject(method = "onCollision", at = @At(value = "HEAD"), cancellable = true)
    private void creo_projectilesIgnore(HitResult hitResult, CallbackInfo ci) {
        BlockTagImpl.applyProjectilesIgnore((ProjectileEntity) (Object) this, hitResult, ci);
    }

    @Inject(method = "canHit", at = @At("HEAD"), cancellable = true)
    private void creo_projectilesIgnoreEntities(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyProjectilesIgnore(entity, cir);
    }
}
