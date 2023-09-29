package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.EnchantmentModificationImpl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

@ApiStatus.NonExtendable
public interface EnchantmentModification {
    EnchantmentModification INSTANCE = new EnchantmentModificationImpl();

    /**
     * Set the {@link net.minecraft.enchantment.Enchantment.Rarity} of the enchantment.
     * @param enchantment the enchantment
     * @param rarity the new {@link net.minecraft.enchantment.Enchantment.Rarity}
     */
    void setRarity(Enchantment enchantment, Enchantment.Rarity rarity);

    /**
     * Set the acceptable items of the enchantment.
     * @param enchantment the enchantment
     * @param acceptableItemPredicate predicate for which items can support the enchantment
     */
    void setAcceptableItems(Enchantment enchantment, Predicate<ItemStack> acceptableItemPredicate);

    /**
     * Set the minimum level of the enchantment.
     * @param enchantment the enchantment
     * @param minLevel the new minimum level
     */
    void setMinLevel(Enchantment enchantment, int minLevel);

    /**
     * Set the maxiumum level of the enchantment.
     * @param enchantment the enchantment
     * @param maxLevel the new maxiumum level
     */
    void setMaxLevel(Enchantment enchantment, int maxLevel);
}
