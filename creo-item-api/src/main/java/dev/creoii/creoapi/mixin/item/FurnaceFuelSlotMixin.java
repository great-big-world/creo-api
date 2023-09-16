package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.AccessibleItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceFuelSlot.class)
public abstract class FurnaceFuelSlotMixin extends Slot {
    public FurnaceFuelSlotMixin(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void creo_dontInsertRestrictedFuel(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Item.Settings settings = ((AccessibleItem) inventory.getStack(0).getItem()).creo_getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            RegistryEntryList<Item> requiredFuels = creoItemSettings.getRequiredFuels();
            if (requiredFuels != null) {
                cir.setReturnValue(isRequiredFuel(requiredFuels, stack));
            }
        }
    }

    @Unique
    private boolean isRequiredFuel(RegistryEntryList<Item> required, ItemStack fuel) {
        return required.contains(fuel.getRegistryEntry());
    }
}
