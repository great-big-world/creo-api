package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.ItemModificationImpl;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public interface ItemModification {
    ItemModification INSTANCE = new ItemModificationImpl();

    void setRarity(Item item, Rarity rarity);

    Rarity getRarity(Item item);
}