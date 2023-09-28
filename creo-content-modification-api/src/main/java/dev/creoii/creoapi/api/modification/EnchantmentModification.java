package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.EnchantmentModificationImpl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

@ApiStatus.NonExtendable
public interface EnchantmentModification {
    EnchantmentModification INSTANCE = new EnchantmentModificationImpl();

    void setRarity(Enchantment enchantment, Enchantment.Rarity rarity);

    void setAcceptableItems(Enchantment enchantment, Predicate<ItemStack> acceptableItemPredicate);

    void setMinLevel(Enchantment enchantment, int minLevel);

    void setMaxLevel(Enchantment enchantment, int maxLevel);
}
