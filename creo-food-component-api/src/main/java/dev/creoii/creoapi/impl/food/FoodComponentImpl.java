package dev.creoii.creoapi.impl.food;

import dev.creoii.creoapi.api.food.CreoFoodComponent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
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
        if (stack.isFood() && stack.getItem().getFoodComponent() instanceof CreoFoodComponent creoFoodComponent && creoFoodComponent.hasEatDurability()) {
            stack.setDamage(creoFoodComponent.getEatDurability());
        }
    }

    public static boolean applyFoodSprintEdibles(ClientPlayerEntity player) {
        if (player.getActiveItem().getItem().getFoodComponent() instanceof CreoFoodComponent creoFoodComponent) {
            return player.isUsingItem() ? !creoFoodComponent.isSprintEdible() : player.isUsingItem();
        }
        return player.isUsingItem();
    }
}
