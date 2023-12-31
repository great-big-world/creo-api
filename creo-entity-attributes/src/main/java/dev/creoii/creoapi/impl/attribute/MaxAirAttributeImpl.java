package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class MaxAirAttributeImpl {
    public static void addAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.GENERIC_MAX_AIR);
    }

    public static void applyMaxAir(Entity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.getAttributes() != null) {
            cir.setReturnValue((int) livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_MAX_AIR) * 10);
        }
    }
}
