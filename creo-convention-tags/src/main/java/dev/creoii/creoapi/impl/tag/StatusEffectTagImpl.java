package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoStatusEffectTags;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class StatusEffectTagImpl {
    public static void applyMilkDoesNotCure(ItemStack stack, LivingEntity living, CallbackInfoReturnable<ItemStack> cir) {
        if (living.getWorld().isClient) {
            return;
        }
        for (StatusEffectInstance statusEffectInstance : living.getActiveStatusEffects().values()) {
            if (TagUtil.isIn(CreoStatusEffectTags.UNAFFECTED_BY_MILK, statusEffectInstance.getEffectType().value()))
                continue;
            living.onStatusEffectRemoved(statusEffectInstance);
            living.getActiveStatusEffects().remove(statusEffectInstance.getEffectType(), statusEffectInstance);
        }
        cir.setReturnValue(stack);
    }

    public static boolean applyCuresZombieVillagers(ZombieVillagerEntity zombieVillager) {
        for (StatusEffectInstance statusEffect : zombieVillager.getStatusEffects()) {
            RegistryEntry<StatusEffect> entry = zombieVillager.getWorld().getRegistryManager().get(RegistryKeys.STATUS_EFFECT).getEntry(statusEffect.getEffectType().value());
            if (entry.isIn(CreoStatusEffectTags.CURES_ZOMBIE_VILLAGERS))
                return true;
        }
        return false;
    }
}
