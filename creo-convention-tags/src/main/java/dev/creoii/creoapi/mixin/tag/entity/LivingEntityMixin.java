package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "disablesShield", at = @At("HEAD"), cancellable = true)
    private void creo_applyDisablesShield(CallbackInfoReturnable<Boolean> cir) {
        ItemTagImpl.applyDisablesShield((LivingEntity) (Object) this, cir);
    }
}
