package dev.creoii.creoapi.impl.item.util;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.RequiredFuelsImpl;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.screen.slot.Slot;

public class FurnaceItemSlot extends Slot {
    public FurnaceItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        ItemStack fuel = inventory.getStack(1);
        if (fuel.isEmpty())
            return true;

        Item.Settings settings = ((AccessibleItem) stack.getItem()).creo_getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            RegistryEntryList<Item> requiredFuels = creoItemSettings.getRequiredFuels();
            if (requiredFuels != null)
                return RequiredFuelsImpl.isRequiredFuel(requiredFuels, fuel);
        }

        return true;
    }
}
