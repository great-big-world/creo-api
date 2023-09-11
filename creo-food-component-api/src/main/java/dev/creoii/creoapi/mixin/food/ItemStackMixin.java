package dev.creoii.creoapi.mixin.food;

import dev.creoii.creoapi.impl.food.FoodComponentImpl;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V", at = @At("TAIL"))
    private void creo_applyFoodDurabilityNbt(ItemConvertible item, int count, CallbackInfo ci) {
        FoodComponentImpl.applyFoodEatDurabilityNbt((ItemStack) (Object) this);
    }

    @Inject(method = "<init>(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
    private void creo_applyFoodDurabilityNbt(NbtCompound nbt, CallbackInfo ci) {
        FoodComponentImpl.applyFoodEatDurabilityNbt((ItemStack) (Object) this);
    }
}
