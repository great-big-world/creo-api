package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoStatusEffectTags;
import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class StatusEffectTagImpl {
    public static void cureStatusEffects(ItemStack stack, LivingEntity living, CallbackInfoReturnable<ItemStack> cir) {
        if (living.getWorld().isClient) {
            return;
        }
        for (StatusEffectInstance statusEffectInstance : living.getActiveStatusEffects().values()) {
            if (TagUtil.isIn(CreoStatusEffectTags.MILK_DOES_NOT_CURE, statusEffectInstance.getEffectType()))
                continue;
            living.onStatusEffectRemoved(statusEffectInstance);
            living.getActiveStatusEffects().remove(statusEffectInstance.getEffectType(), statusEffectInstance);
        }
        cir.setReturnValue(stack);
    }
}
