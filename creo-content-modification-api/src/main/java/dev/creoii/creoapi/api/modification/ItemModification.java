package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.ItemModificationImpl;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ItemModification {
    ItemModification INSTANCE = new ItemModificationImpl();

    /**
     * Set the rarity of the item.
     * @param item the item
     * @param rarity the new {@link Rarity}
     */
    void setRarity(Item item, Rarity rarity);

    /**
     * Get the {@link Rarity} of the item.
     * @param item the item
     * @return the rarity of the item.
     */
    Rarity getRarity(Item item);

    /**
     * Set the max count of the item.
     * @param item the item
     * @param maxCount the new max count
     */
    void setMaxCount(Item item, int maxCount);

    /**
     * Set the max damage of the item.
     * @param item the item
     * @param maxDamage the new max damage
     */
    void setMaxDamage(Item item, int maxDamage);

    /**
     * Set whether the item is fireproof.
     * @param item the item
     * @param fireproof whether the item is fireproof
     */
    void setFireproof(Item item, boolean fireproof);

    /**
     * Set the recipe remainder of the item.
     * @param item the item
     * @param recipeRemainder the new recipe remainder
     */
    void setRecipeRemainder(Item item, Item recipeRemainder);

    /**
     * Set the food component of the item.
     * @param item the item
     * @param foodComponent the new {@link FoodComponent}
     */
    void setFoodComponent(Item item, FoodComponent foodComponent);
}