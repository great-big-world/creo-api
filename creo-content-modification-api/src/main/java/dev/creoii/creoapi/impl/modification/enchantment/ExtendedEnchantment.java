package dev.creoii.creoapi.impl.modification.enchantment;

import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public interface ExtendedEnchantment {
    void creo_setAcceptableItemPredicate(Predicate<ItemStack> acceptableItemPredicate);

    void creo_setMinLevel(int minLevel);

    void creo_setMaxLevel(int maxLevel);
}
