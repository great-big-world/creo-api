package dev.creoii.creoapi.mixin.food;

import dev.creoii.creoapi.impl.food.FoodComponentImpl;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow @Nullable public abstract FoodComponent getFoodComponent();
    @Shadow public abstract boolean isFood();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo_applyFoodMaxDamage(Item.Settings settings, CallbackInfo ci) {
        FoodComponentImpl.applyFoodMaxEatDurability((Item) (Object) this);
    }

    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_applyFoodEatTimes(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        FoodComponentImpl.applyFoodEatTimes(getFoodComponent(), cir);
    }

    @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    private void creo_applyFoodEatDurability(CallbackInfoReturnable<Integer> cir) {
        FoodComponentImpl.applyFoodEatDurability(getFoodComponent(), isFood(), cir);
    }
}
