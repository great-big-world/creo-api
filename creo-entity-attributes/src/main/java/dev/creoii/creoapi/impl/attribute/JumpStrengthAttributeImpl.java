package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class JumpStrengthAttributeImpl {
    public static void addAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.GENERIC_JUMP_STRENGTH);
    }

    public static float applyJumpStrength(LivingEntity livingEntity) {
        return (float) livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_JUMP_STRENGTH);
    }
}
