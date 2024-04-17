package dev.creoii.creoapi.api.event.item;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Events related to {@link net.minecraft.item.Item}.
 */
public final class ItemEvents {
    /**
     * An event that is called when an item is crafted.
     */
    public static final Event<Craft> CRAFT = EventFactory.createArrayBacked(Craft.class,
            listeners -> (world, stack, player, amount) -> {
                for (Craft event : listeners) {
                    event.onCraft(world, stack, player, amount);
                }
            }
    );

    /**
     * An event that is called when an enchantment is added to an item.
     */
    public static final Event<Enchant> ENCHANT = EventFactory.createArrayBacked(Enchant.class,
            listeners -> (stack, enchantment, level) -> {
                for (Enchant event : listeners) {
                    return event.onEnchant(stack, enchantment, level);
                }

                return true;
            }
    );

    /**
     * An event that is called when an item is picked up by an entity.
     * <p>
     * <b>Note:</b> this event called server-side.
     * @since 0.1.2
     */
    public static final Event<PickUp> PICK_UP = EventFactory.createArrayBacked(PickUp.class,
            listeners -> (itemEntity, living) -> {
                for (PickUp event : listeners) {
                    event.onPickUp(itemEntity, living);
                }
            }
    );

    @FunctionalInterface
    public interface Craft {
        /**
         * Called before an item is crafted.
         *
         * @param world the world
         * @param stack the stack being crafted
         * @param player the player crafting
         * @param amount the amount being crafted
         */
        void onCraft(World world, ItemStack stack, @Nullable PlayerEntity player, int amount);
    }

    @FunctionalInterface
    public interface Enchant {
        /**
         * Called before an {@link net.minecraft.enchantment.Enchantment} is added to an itemstack.
         *
         * <p> Return false to stop the enchantment from being added.
         *
         * @param stack the stack being enchanted
         * @param enchantment the enchantment being added
         * @param level the enchantment level
         */
        boolean onEnchant(ItemStack stack, Enchantment enchantment, int level);
    }

    @FunctionalInterface
    public interface PickUp {
        /**
         * Called when an item is picked up by an entity.
         * @param itemEntity the item
         * @param living the entity that picked up the item
         */
        void onPickUp(ItemEntity itemEntity, LivingEntity living);
    }
}
