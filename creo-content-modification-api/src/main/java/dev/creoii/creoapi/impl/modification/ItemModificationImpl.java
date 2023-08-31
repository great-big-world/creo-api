package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.ItemModification;
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
}