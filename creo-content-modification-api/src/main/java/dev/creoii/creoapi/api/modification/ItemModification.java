package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.ItemModificationImpl;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public interface ItemModification {
    ItemModification INSTANCE = new ItemModificationImpl();

    void setRarity(Item item, Rarity rarity);

    Rarity getRarity(Item item);

    void setMaxCount(Item item, int rarity);

    int getMaxCount(Item item);

    void setMaxDamage(Item item, int rarity);

    int getMaxDamage(Item item);

    void setFireproof(Item item, boolean rarity);

    boolean isFireproof(Item item);

    void setRecipeRemainder(Item item, Item rarity);

    Item getRecipeRemainder(Item item);

    void setFoodComponent(Item item, FoodComponent rarity);

    FoodComponent getFoodComponent(Item item);
}