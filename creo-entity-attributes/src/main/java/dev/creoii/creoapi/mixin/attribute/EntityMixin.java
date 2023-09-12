package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.MaxAirAttributeImpl;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "getMaxAir", at = @At("HEAD"), cancellable = true)
    private void creo_applyMaxAir(CallbackInfoReturnable<Integer> cir) {
        MaxAirAttributeImpl.applyMaxAir((Entity) (Object) this, cir);
    }
}
