package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.ItemModification;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class ItemModificationImpl implements ItemModification {
    @Override
    public void setRarity(Item item, Rarity rarity) {
        
    }

    @Override
    public Rarity getRarity(Item item) {
        return null;
    }

    @Override
    public void setMaxCount(Item item, int rarity) {

    }

    @Override
    public int getMaxCount(Item item) {
        return 0;
    }

    @Override
    public void setMaxDamage(Item item, int rarity) {

    }

    @Override
    public int getMaxDamage(Item item) {
        return 0;
    }

    @Override
    public void setFireproof(Item item, boolean rarity) {

    }

    @Override
    public boolean isFireproof(Item item) {
        return false;
    }

    @Override
    public void setRecipeRemainder(Item item, Item rarity) {

    }

    @Override
    public Item getRecipeRemainder(Item item) {
        return null;
    }

    @Override
    public void setFoodComponent(Item item, FoodComponent rarity) {

    }

    @Override
    public FoodComponent getFoodComponent(Item item) {
        return null;
    }
}