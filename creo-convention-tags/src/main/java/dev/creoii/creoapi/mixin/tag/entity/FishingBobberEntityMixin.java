package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Inject(method = "canHit", at = @At("RETURN"), cancellable = true)
    private void creo_fishingRodIgnores(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyFishingRodCannotHook(entity, cir);
    }
}
