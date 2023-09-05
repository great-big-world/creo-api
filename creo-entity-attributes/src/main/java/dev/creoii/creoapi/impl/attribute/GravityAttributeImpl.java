package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

public final class GravityAttributeImpl {
    private static final UUID SLOW_FALLING_MODIFIER_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    public static final EntityAttributeModifier SLOW_FALLING_MODIFIER = new EntityAttributeModifier(SLOW_FALLING_MODIFIER_ID, "Slow falling acceleration reduction", .125d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static void addAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.GENERIC_GRAVITY);
    }

    public static double modifyGravity(Entity entity, double d) {
        if (entity instanceof LivingEntity livingEntity && livingEntity instanceof Gravity gravity) {
            gravity.creo_setGravity(livingEntity.getAttributeInstance(CreoEntityAttributes.GENERIC_GRAVITY));
            return gravity.creo_getGravity().getValue();
        }
        return d;
    }

    public static double modifySlowFallingGravity(Entity entity, double d) {
        if (entity instanceof Gravity gravity && canModifyGravity(gravity) && !gravity.creo_getGravity().hasModifier(SLOW_FALLING_MODIFIER)) {
            gravity.creo_getGravity().addTemporaryModifier(SLOW_FALLING_MODIFIER);
            return gravity.creo_getGravity().getValue();
        }
        return d;
    }

    public static void removeSlowFallingModifier(Entity entity) {
        if (entity instanceof Gravity gravity && canModifyGravity(gravity)) {
            if (gravity.creo_getGravity().hasModifier(GravityAttributeImpl.SLOW_FALLING_MODIFIER))
                gravity.creo_getGravity().removeModifier(GravityAttributeImpl.SLOW_FALLING_MODIFIER);
        }
    }

    public static float adjustFallDamage(Entity entity, float value) {
        if (entity instanceof Gravity gravity && canModifyGravity(gravity)) {
            return value * (float) (((LivingEntity) entity).getAttributeValue(CreoEntityAttributes.GENERIC_GRAVITY) * 12.5f);
        }
        return value;
    }

    public static void knockDownwards(Entity entity, CallbackInfo ci) {
        if (entity instanceof Gravity gravity && canModifyGravity(gravity)) {
            entity.setVelocity(entity.getVelocity().add(0d, -.03999999910593033d * ((LivingEntity) entity).getAttributeValue(CreoEntityAttributes.GENERIC_GRAVITY), 0d));
            ci.cancel();
        }
    }

    private static boolean canModifyGravity(Gravity gravity) {
        return gravity.creo_getGravity() != null;
    }

    public interface Gravity {
        EntityAttributeInstance creo_getGravity();

        void creo_setGravity(EntityAttributeInstance instance);
    }
}
