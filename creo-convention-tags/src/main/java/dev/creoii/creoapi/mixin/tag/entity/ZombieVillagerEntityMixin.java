package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.StatusEffectTagImpl;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieVillagerEntity;hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z"))
    private boolean creo$applyCuresZombieVillagers(ZombieVillagerEntity instance, RegistryEntry<StatusEffect> registryEntry) {
        return StatusEffectTagImpl.applyCuresZombieVillagers(registryEntry);
    }
}
