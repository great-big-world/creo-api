package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.util.AccessibleItem;
import dev.creoii.creoapi.impl.item.util.FurnaceItemSlot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class RequiredFuelsImpl {
    public static Slot addSlot(AbstractFurnaceScreenHandler instance, Inventory inventory) {
        return instance.addSlot(new FurnaceItemSlot(inventory, 0, 56, 17));
    }

    public static void applySmeltable(ItemStack stack, Inventory inventory, CallbackInfoReturnable<Boolean> cir) {
        Item.Settings settings = ((AccessibleItem) stack.getItem()).creo_getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            RegistryEntryList<Item> requiredFuels = creoItemSettings.getRequiredFuels();
            if (requiredFuels != null)
                cir.setReturnValue(isRequiredFuel(requiredFuels, inventory.getStack(1)));
        }
    }

    public static void applyFurnaceFuelSlot(ItemStack stack, Inventory inventory, CallbackInfoReturnable<Boolean> cir) {
        ItemStack item = inventory.getStack(0);
        if (item.isEmpty())
            cir.setReturnValue(true);

        Item.Settings settings = ((AccessibleItem) item.getItem()).creo_getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            RegistryEntryList<Item> requiredFuels = creoItemSettings.getRequiredFuels();
            if (requiredFuels != null) {
                cir.setReturnValue(isRequiredFuel(requiredFuels, stack));
            }
        }
    }

    static boolean isRequiredFuel(RegistryEntryList<Item> items, ItemStack fuel) {
        return items.contains(fuel.getRegistryEntry());
    }
}
