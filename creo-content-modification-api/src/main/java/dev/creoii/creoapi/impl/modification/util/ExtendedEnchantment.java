package dev.creoii.creoapi.impl.modification.util;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

@ApiStatus.NonExtendable
public interface ExtendedEnchantment {
    void creo_setAcceptableItemPredicate(Predicate<ItemStack> acceptableItemPredicate);

    void creo_setMinLevel(int minLevel);

    void creo_setMaxLevel(int maxLevel);
}
