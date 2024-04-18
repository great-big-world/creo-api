package dev.creoii.creoapi.api.item;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class ItemEvents {
    /**
     * An event that is called when an item attacks an entity through a block.
     * @since 0.3.0
     */
    public static final Event<AttackThroughBlock> ATTACK_THROUGH_BLOCK = EventFactory.createArrayBacked(AttackThroughBlock.class,
            listeners -> (player, stack, target) -> {
                for (AttackThroughBlock event : listeners) {
                    event.onAttackThroughBlock(player, stack, target);
                }
            }
    );

    /**
     * An event that is called when an item is picked up via clicking by a player.
     * @since 0.2.3
     */
    public static final Event<ClickPickup> CLICK_PICKUP = EventFactory.createArrayBacked(ClickPickup.class,
            listeners -> (itemEntity, player) -> {
                for (ClickPickup event : listeners) {
                    event.onClickPickUp(itemEntity, player);
                }
            }
    );

    @FunctionalInterface
    public interface AttackThroughBlock {
        /**
         * Called when an item attacks an entity through a block.
         * @param player the player
         * @param stack the itemstack used
         * @param target the entity that was attacked
         */
        void onAttackThroughBlock(PlayerEntity player, ItemStack stack, Entity target);
    }

    @FunctionalInterface
    public interface ClickPickup {
        /**
         * Called when an item is picked up via clicking by a player.
         * @param itemEntity the item
         * @param player the player that picked up the item
         */
        void onClickPickUp(ItemEntity itemEntity, PlayerEntity player);
    }
}
