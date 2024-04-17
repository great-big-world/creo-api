package dev.creoii.creoapi.api.item;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

public final class ItemEvents {
    /**
     * An event that is called when an item is picked up via clicking by a player.
     */
    public static final Event<ClickPickup> CLICK_PICKUP = EventFactory.createArrayBacked(ClickPickup.class,
            listeners -> (itemEntity, player) -> {
                for (ClickPickup event : listeners) {
                    event.onClickPickUp(itemEntity, player);
                }
            }
    );

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
