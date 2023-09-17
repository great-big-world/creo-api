package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

/**
 * Events related to {@link net.minecraft.entity.LivingEntity}.
 */
public final class LivingEntityEvents {
    /**
     * An event that is called when a {@link LivingEntity} drops loot.
     */
    public static final Event<DropLoot> DROP_LOOT = EventFactory.createArrayBacked(DropLoot.class,
            (listeners) -> (livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer) -> {
                for (DropLoot event : listeners) {
                    return event.dropLoot(livingEntity, identifier, lootTable, damageSource, lootContextParameterSet, causedByPlayer);
                }

                return true;
            }
    );

    /**
     * An event that is called when a {@link LivingEntity} equips an item to an equipment slot.
     */
    public static final Event<EquipStack> EQUIP_STACK = EventFactory.createArrayBacked(EquipStack.class,
            (listeners) -> (livingEntity, slot, oldStack, newStack) -> {
                for (EquipStack event : listeners) {
                    return event.equipStack(livingEntity, slot, oldStack, newStack);
                }

                return true;
            }
    );

    /**
     * An event that is called when a {@link LivingEntity} eats food.
     */
    public static final Event<EatFood> EAT_FOOD = EventFactory.createArrayBacked(EatFood.class,
            (listeners) -> (world, livingEntity, stack) -> {
                for (EatFood event : listeners) {
                    return event.eatFood(world, livingEntity, stack);
                }

                return stack;
            }
    );

    @FunctionalInterface
    public interface DropLoot {
        /**
         * Called when a {@link LivingEntity} drops loot.
         *
         * <p> Return false to stop the loot from being dropped.
         *
         * @param livingEntity the living entity
         * @param identifier the original loot table id
         * @param lootTable the loot table instance
         * @param damageSource the damageSource resulting in the death of the living entity
         * @param lootContextParameterSet the loot context parameters
         * @param causedByPlayer whether the death was caused by a player
         */
        boolean dropLoot(LivingEntity livingEntity, Identifier identifier, LootTable lootTable, DamageSource damageSource, LootContextParameterSet lootContextParameterSet, boolean causedByPlayer);
    }

    @FunctionalInterface
    public interface EquipStack {
        /**
         * Called when a {@link LivingEntity} equips an item, on both the server and the client.
         *
         * <p> Return false to stop the item from being equipped.
         *
         * @param livingEntity the living entity
         * @param slot the slot being equipped
         * @param oldStack the previous itemstack in the slot
         * @param newStack the new itemstack in the slot
         */
        boolean equipStack(LivingEntity livingEntity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack);
    }

    @FunctionalInterface
    public interface EatFood {
        /**
         * Called when a {@link LivingEntity} eats food.
         *
         * @param world the world
         * @param livingEntity the living entity
         * @param stack the itemstack being eaten
         */
        ItemStack eatFood(World world, LivingEntity livingEntity, ItemStack stack);
    }
}
