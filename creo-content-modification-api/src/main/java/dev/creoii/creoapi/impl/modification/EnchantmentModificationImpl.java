package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.EnchantmentModification;
import dev.creoii.creoapi.mixin.modification.enchantment.EnchantmentAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class EnchantmentModificationImpl implements EnchantmentModification {
    @Override
    public void setRarity(Enchantment enchantment, Enchantment.Rarity rarity) {
        ((EnchantmentAccessor) enchantment).setRarity(rarity);
    }

    @Override
    public void setAcceptableItems(Enchantment enchantment, Predicate<ItemStack> acceptableItemPredicate) {
        ((ExtendedEnchantment) enchantment).creo_setAcceptableItemPredicate(acceptableItemPredicate);
    }

    @Override
    public void setMinLevel(Enchantment enchantment, int minLevel) {
        ((ExtendedEnchantment) enchantment).creo_setMinLevel(minLevel);
    }

    @Override
    public void setMaxLevel(Enchantment enchantment, int maxLevel) {
        ((ExtendedEnchantment) enchantment).creo_setMaxLevel(maxLevel);
    }
}
