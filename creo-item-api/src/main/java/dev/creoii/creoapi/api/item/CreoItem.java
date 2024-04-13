package dev.creoii.creoapi.api.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface CreoItem {
    /**
     * Performs an attack through blocks.
     * <p>
     * <b>Note:</b> Assume that {@link MinecraftClient#player} and {@link MinecraftClient#interactionManager} are nonnull.
     * @param client a {@link MinecraftClient} instance
     * @param stack the itemstack used
     * @param target the target attacked
     */
    default void onAttackThroughBlock(MinecraftClient client, ItemStack stack, Entity target) {
    }

    /**
     * Returns whether the item can attack through blocks.
     * @param client a {@link MinecraftClient} instance
     * @param stack the itemstack used
     * @param target the target attacked
     * @return whether the item can attack through blocks.
     */
    default boolean canAttackThroughBlock(MinecraftClient client, ItemStack stack, Entity target) {
        return false;
    }
}
