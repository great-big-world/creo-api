package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * Callback for when a {@link LivingEntity} equips an item.
 * Is called on both the server and the client.
 * <p>
 * Return false to stop the item from being equipped.
 */
public interface LivingEquipStackCallback {
    Event<LivingEquipStackCallback> EVENT = EventFactory.createArrayBacked(LivingEquipStackCallback.class,
            (listeners) -> (livingEntity, slot, oldStack, newStack) -> {
                for (LivingEquipStackCallback event : listeners) {
                    return event.equipStack(livingEntity, slot, oldStack, newStack);
                }

                return true;
            }
    );

    boolean equipStack(LivingEntity livingEntity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack);
}
