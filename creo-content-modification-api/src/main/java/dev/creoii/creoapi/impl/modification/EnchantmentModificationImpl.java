package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.EnchantmentModification;
import dev.creoii.creoapi.impl.modification.util.ExtendedEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

@ApiStatus.Internal
public class EnchantmentModificationImpl implements EnchantmentModification {
    @Override
    public void setAcceptableItems(Enchantment enchantment, Predicate<ItemStack> acceptableItemPredicate) {
        ((ExtendedEnchantment) enchantment).creo$setAcceptableItemPredicate(acceptableItemPredicate);
    }

    @Override
    public void setMinLevel(Enchantment enchantment, int minLevel) {
        ((ExtendedEnchantment) enchantment).creo$setMinLevel(minLevel);
    }

    @Override
    public void setMaxLevel(Enchantment enchantment, int maxLevel) {
        ((ExtendedEnchantment) enchantment).creo$setMaxLevel(maxLevel);
    }
}
