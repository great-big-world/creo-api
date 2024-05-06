package dev.creoii.creoapi.impl.modification.util;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

@ApiStatus.NonExtendable
public interface ExtendedEnchantment {
    void creo$setAcceptableItemPredicate(Predicate<ItemStack> acceptableItemPredicate);

    void creo$setMinLevel(int minLevel);

    void creo$setMaxLevel(int maxLevel);
}
