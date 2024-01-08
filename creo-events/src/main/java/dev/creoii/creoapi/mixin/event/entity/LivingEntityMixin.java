package dev.creoii.creoapi.mixin.event.entity;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onEquipStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isClient()Z"), cancellable = true)
    private void creo_livingEquipStackCallback(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        EntityEventImpl.applyLivingEquipStackEvent((LivingEntity) (Object) this, slot, oldStack, newStack, ci);
    }

    @Inject(method = "dropLoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/LootTable;generateLoot(Lnet/minecraft/loot/context/LootContextParameterSet;JLjava/util/function/Consumer;)V"), cancellable = true)
    private void creo_livingDropLootCallback(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci, @Local Identifier identifier, @Local LootTable lootTable, @Local LootContextParameterSet.Builder builder, @Local LootContextParameterSet lootContextParameterSet) {
        EntityEventImpl.applyLivingDropLootEvent((LivingEntity) (Object) this, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer, ci);
    }

    @Inject(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), cancellable = true)
    private void creo_livingDropLootCallback(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        EntityEventImpl.applyLivingEatFoodEvent(world, (LivingEntity) (Object) this, stack, cir);
    }
}
