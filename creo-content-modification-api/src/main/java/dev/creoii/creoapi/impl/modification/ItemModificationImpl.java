package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.ItemModification;
import dev.creoii.creoapi.mixin.modification.item.ItemAccessor;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ItemModificationImpl implements ItemModification {
    @Override
    public void setRarity(Item item, Rarity rarity) {
        ((ItemAccessor) item).setRarity(rarity);
    }

    @Override
    public Rarity getRarity(Item item) {
        return ((ItemAccessor) item).getRarity();
    }

    @Override
    public void setMaxCount(Item item, int maxCount) {
        ((ItemAccessor) item).setMaxCount(maxCount);
    }

    @Override
    public void setMaxDamage(Item item, int maxDamage) {
        ((ItemAccessor) item).setMaxDamage(maxDamage);
    }

    @Override
    public void setFireproof(Item item, boolean fireproof) {
        ((ItemAccessor) item).setFireproof(fireproof);
    }

    @Override
    public void setRecipeRemainder(Item item, Item recipeRemainder) {
        ((ItemAccessor) item).setRecipeRemainder(recipeRemainder);
    }

    @Override
    public void setFoodComponent(Item item, FoodComponent foodComponent) {
        ((ItemAccessor) item).setFoodComponent(foodComponent);
    }
}