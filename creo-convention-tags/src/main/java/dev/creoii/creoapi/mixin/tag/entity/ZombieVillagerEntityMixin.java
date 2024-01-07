package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.StatusEffectTagImpl;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieVillagerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    private boolean creo_applyCuresZombieVillagers(ZombieVillagerEntity instance, StatusEffect statusEffect) {
        return StatusEffectTagImpl.applyCuresZombieVillagers(instance);
    }
}
