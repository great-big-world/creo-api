package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.FoodComponentImpl;
import net.minecraft.component.ComponentMapImpl;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;ILnet/minecraft/component/ComponentMapImpl;)V", at = @At("TAIL"))
    private void creo$applyFoodDurabilityNbt(ItemConvertible item, int count, ComponentMapImpl components, CallbackInfo ci) {
        FoodComponentImpl.applyFoodEatDurabilityNbt((ItemStack) (Object) this);
    }

    @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    private void creo$applyFoodEatDurability(CallbackInfoReturnable<Integer> cir) {
        FoodComponentImpl.applyFoodEatDurability((ItemStack) (Object) this, cir);
    }
}
