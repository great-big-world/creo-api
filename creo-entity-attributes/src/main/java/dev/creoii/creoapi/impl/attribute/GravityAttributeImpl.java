package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@ApiStatus.Internal
public final class GravityAttributeImpl {
    private static final UUID SLOW_FALLING_MODIFIER_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    public static final EntityAttributeModifier SLOW_FALLING_MODIFIER = new EntityAttributeModifier(SLOW_FALLING_MODIFIER_ID, "Slow falling acceleration reduction", .125d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static void addAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.GENERIC_GRAVITY);
    }

    public static double modifyGravity(LivingEntity livingEntity, double d) {
        if (canModifyGravity(livingEntity)) {
            return livingEntity.getAttributeInstance(CreoEntityAttributes.GENERIC_GRAVITY).getValue();
        }
        return d;
    }

    public static double modifySlowFallingGravity(LivingEntity livingEntity, double d) {
        EntityAttributeInstance gravity = livingEntity.getAttributeInstance(CreoEntityAttributes.GENERIC_GRAVITY);
        if (canModifyGravity(livingEntity) && !gravity.hasModifier(SLOW_FALLING_MODIFIER)) {
            gravity.addTemporaryModifier(SLOW_FALLING_MODIFIER);
            return gravity.getValue();
        }
        return d;
    }

    public static void removeSlowFallingModifier(LivingEntity livingEntity) {
        if (canModifyGravity(livingEntity)) {
            EntityAttributeInstance gravity = livingEntity.getAttributeInstance(CreoEntityAttributes.GENERIC_GRAVITY);
            if (gravity.hasModifier(GravityAttributeImpl.SLOW_FALLING_MODIFIER))
                gravity.removeModifier(GravityAttributeImpl.SLOW_FALLING_MODIFIER_ID);
        }
    }

    public static float adjustFallDamage(LivingEntity livingEntity, float value) {
        if (canModifyGravity(livingEntity)) {
            return value * (float) (livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_GRAVITY) * 12.5f);
        }
        return value;
    }

    public static void knockDownwards(LivingEntity livingEntity, CallbackInfo ci) {
        if (canModifyGravity(livingEntity)) {
            livingEntity.setVelocity(livingEntity.getVelocity().add(0d, -.03999999910593033d * livingEntity.getAttributeValue(CreoEntityAttributes.GENERIC_GRAVITY), 0d));
            ci.cancel();
        }
    }

    private static boolean canModifyGravity(LivingEntity livingEntity) {
        return livingEntity.getAttributes() != null && livingEntity.getAttributeInstance(CreoEntityAttributes.GENERIC_GRAVITY) != null;
    }
}
