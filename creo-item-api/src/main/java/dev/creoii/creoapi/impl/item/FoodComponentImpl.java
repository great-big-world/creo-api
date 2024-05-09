package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoFoodComponent;
import dev.creoii.creoapi.api.item.CreoDataComponentTypes;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class FoodComponentImpl {
    public static void eatCreoFoodComponentItem(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.contains(CreoDataComponentTypes.FOOD)) {
            cir.setReturnValue(user.eatFood(world, stack));
        }
    }

    public static void eatCreoFoodComponentPlayer(PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        CreoFoodComponent foodComponent = stack.get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            if (user.canConsume(foodComponent.canAlwaysEat())) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(stack));
            } else {
                cir.setReturnValue(TypedActionResult.fail(stack));
            }
        }
    }

    public static void eatCreoFoodComponent(HungerManager manager, ItemStack stack, CallbackInfo ci) {
        CreoFoodComponent foodComponent = stack.get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            manager.addInternal(foodComponent.nutrition(), foodComponent.saturation());
            ci.cancel();
        }
    }

    public static void applyFoodEatSpeed(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        CreoFoodComponent foodComponent = stack.get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            cir.setReturnValue(foodComponent.getEatTicks());
        }
    }

    public static boolean applyFoodSprintEdibles(ClientPlayerEntity player) {
        CreoFoodComponent foodComponent = player.getActiveItem().get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            return player.isUsingItem() ? !foodComponent.canSprintEat() : player.isUsingItem();
        }
        return player.isUsingItem();
    }

    public static void applyFoodEatLiving(World world, LivingEntity living, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        CreoFoodComponent foodComponent = stack.get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            world.playSound(null, living.getX(), living.getY(), living.getZ(), living.getEatSound(stack), SoundCategory.NEUTRAL, 1f, 1f + (world.random.nextFloat() - world.random.nextFloat()) * .4f);
            applyFoodEffects(living, foodComponent);
            if (foodComponent.healsHealth()) {
                living.heal(foodComponent.nutrition());
            }
            stack.decrementUnlessCreative(1, living);
            living.emitGameEvent(GameEvent.EAT);
            cir.setReturnValue(stack);
        }
    }

    private static void applyFoodEffects(LivingEntity living, CreoFoodComponent component) {
        if (!living.getWorld().isClient) {
            for (FoodComponent.StatusEffectEntry entry : component.effects()) {
                if (living.getRandom().nextFloat() < entry.probability()) {
                    living.addStatusEffect(entry.effect());
                }
            }
        }
    }
}
