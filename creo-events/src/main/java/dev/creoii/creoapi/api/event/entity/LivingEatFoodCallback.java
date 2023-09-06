package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Callback for when a {@link LivingEntity} eats food.
 */
public interface LivingEatFoodCallback {
    Event<LivingEatFoodCallback> EVENT = EventFactory.createArrayBacked(LivingEatFoodCallback.class,
            (listeners) -> (world, livingEntity, stack) -> {
                for (LivingEatFoodCallback event : listeners) {
                    return event.eatFood(world, livingEntity, stack);
                }

                return stack;
            }
    );

    ItemStack eatFood(World world, LivingEntity livingEntity, ItemStack stack);
}
