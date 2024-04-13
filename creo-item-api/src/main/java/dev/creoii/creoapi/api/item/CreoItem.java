package dev.creoii.creoapi.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public interface CreoItem {
    /**
     * Performs an attack through blocks.
     * @param player the player
     * @param stack the itemstack used
     * @param target the target attacked
     */
    default void onAttackThroughBlock(ServerPlayerEntity player, ItemStack stack, Entity target) {
    }

    /**
     * Returns whether the item can attack through blocks.
     * @param player the player
     * @param stack the itemstack used
     * @param target the target attacked
     * @return whether the item can attack through blocks.
     */
    default boolean canAttackThroughBlock(ServerPlayerEntity player, ItemStack stack, Entity target) {
        return false;
    }
}
