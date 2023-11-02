package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.ItemSettingsImpl;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Shadow private int pickupDelay;
    @Unique private final MutableInt creo_despawnTime = new MutableInt(6000);
    @Unique private final MutableBoolean creo_buoyant = new MutableBoolean(true);
    @Unique private final MutableDouble creo_gravity = new MutableDouble(-.04d);

    @Inject(method = "<init>(Lnet/minecraft/entity/ItemEntity;)V", at = @At("TAIL"))
    private void creo_applyCreoItemSettings(ItemEntity entity, CallbackInfo ci) {
        pickupDelay = ItemSettingsImpl.applyCreoItemSettings(entity.getStack(), creo_despawnTime, creo_buoyant, creo_gravity);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;DDD)V", at = @At("TAIL"))
    private void creo_applyCreoItemSettings(World world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
        pickupDelay = ItemSettingsImpl.applyCreoItemSettings(stack, creo_despawnTime, creo_buoyant, creo_gravity);
    }

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private int creo_tickDespawnTime(int constant) {
        return ItemSettingsImpl.applyDespawnTime(creo_despawnTime);
    }

    @ModifyConstant(method = "canMerge()Z", constant = @Constant(intValue = 6000))
    private int creo_mergeDespawnTime(int constant) {
        return ItemSettingsImpl.applyPickupDelay(creo_despawnTime);
    }

    @Inject(method = "applyWaterBuoyancy", at = @At("HEAD"), cancellable = true)
    private void creo_stopWaterBuoyancy(CallbackInfo ci) {
        ItemSettingsImpl.applyBuoyancy(creo_buoyant, ci);
    }

    @Inject(method = "applyLavaBuoyancy", at = @At("HEAD"), cancellable = true)
    private void creo_stopLavaBuoyancy(CallbackInfo ci) {
        ItemSettingsImpl.applyBuoyancy(creo_buoyant, ci);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = -.04d))
    private double creo_applyItemGravity(double constant) {
        return ItemSettingsImpl.applyGravity(creo_gravity);
    }
}
