package dev.creoii.creoapi.impl.food;

import dev.creoii.creoapi.api.food.CreoFoodComponent;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class FoodComponentImpl {
    public static void applyFoodMaxEatDurability(Item item) {
        if (item.isFood() && item.getFoodComponent() instanceof CreoFoodComponent creoFoodComponent) {
            item.maxDamage = creoFoodComponent.getEatDurability();
        }
    }

    public static void applyFoodEatTimes(FoodComponent foodComponent, CallbackInfoReturnable<Integer> cir) {
        if (foodComponent instanceof CreoFoodComponent creoFoodComponent) {
            cir.setReturnValue(creoFoodComponent.getEatSpeed());
        }
    }

    public static void applyFoodEatDurability(FoodComponent foodComponent, boolean isFood, CallbackInfoReturnable<Integer> cir) {
        if (isFood && foodComponent instanceof CreoFoodComponent creoFoodComponent && creoFoodComponent.hasEatDurability()) {
            cir.setReturnValue(creoFoodComponent.getEatDurability());
        }
    }

    public static void applyFoodEatDurabilityNbt(ItemStack stack) {
        if (stack.isFood() && stack.getItem().getFoodComponent() instanceof CreoFoodComponent creoFoodComponent) {
            stack.setDamage(creoFoodComponent.getEatDurability());
        }
    }
}
