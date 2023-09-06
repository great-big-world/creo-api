package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.api.event.entity.LivingDropLootCallback;
import dev.creoii.creoapi.api.event.entity.LivingEquipStackCallback;
import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onEquipStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isClient()Z"), cancellable = true)
    private void creo_livingEquipStackCallback(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        EntityEventImpl.applyLivingEquipStackCallback((LivingEntity) (Object) this, slot, oldStack, newStack, ci);
    }

    @Inject(method = "dropLoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/LootTable;generateLoot(Lnet/minecraft/loot/context/LootContextParameterSet;JLjava/util/function/Consumer;)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_livingDropLootCallback(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci, Identifier identifier, LootTable lootTable, LootContextParameterSet.Builder builder, LootContextParameterSet lootContextParameterSet) {
        EntityEventImpl.applyLivingDropLootCallback((LivingEntity) (Object) this, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer, ci);
    }
}
