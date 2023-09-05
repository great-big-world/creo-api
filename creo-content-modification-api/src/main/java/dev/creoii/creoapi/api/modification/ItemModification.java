package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.ItemModificationImpl;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public interface ItemModification {
    ItemModification INSTANCE = new ItemModificationImpl();

    void setRarity(Item item, Rarity rarity);

    Rarity getRarity(Item item);

    void setMaxCount(Item item, int maxCount);

    int getMaxCount(Item item);

    void setMaxDamage(Item item, int maxDamage);

    int getMaxDamage(Item item);

    void setFireproof(Item item, boolean fireproof);

    boolean isFireproof(Item item);

    void setRecipeRemainder(Item item, Item recipeRemainder);

    Item getRecipeRemainder(Item item);

    void setFoodComponent(Item item, FoodComponent foodComponent);

    FoodComponent getFoodComponent(Item item);
}