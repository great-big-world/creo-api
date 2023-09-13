package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.EntityTypeTagImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZoglinEntity.class)
public class ZoglinEntityMixin {
    @Inject(method = "shouldAttack", at = @At("RETURN"), cancellable = true)
    private void creo_zoglinIgnoreEntities(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityTypeTagImpl.applyZoglinIgnores(entity.getType(), cir);
    }
}
