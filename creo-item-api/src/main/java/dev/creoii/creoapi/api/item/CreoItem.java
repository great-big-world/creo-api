package dev.creoii.creoapi.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.HitResult;

/**
 * General-purpose Creo-provided extensions for items.
 */
public interface CreoItem {
    /**
     * Called when a player left-clicks with an item, or an attack.
     * @param player the player
     * @param stack the itemstack used
     * @param type the {@link HitResult.Type}
     * @since 0.2.2
     */
    default void onAttack(PlayerEntity player, ItemStack stack, HitResult.Type type) {
    }

    /**
     * Performs an attack through blocks.
     * @param player the player
     * @param stack the itemstack used
     * @param target the target attacked
     * @since 0.1.2
     */
    default void onAttackThroughBlock(PlayerEntity player, ItemStack stack, Entity target) {
    }

    /**
     * Returns whether the item can attack through blocks.
     * @param player the player
     * @param stack the itemstack used
     * @param target the target attacked
     * @return whether the item can attack through blocks.
     * @since 0.1.2
     */
    default boolean canAttackThroughBlock(PlayerEntity player, ItemStack stack, Entity target) {
        return false;
    }
}
