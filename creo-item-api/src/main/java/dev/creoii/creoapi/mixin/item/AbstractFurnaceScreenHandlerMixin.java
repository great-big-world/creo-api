package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.impl.item.AccessibleItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceScreenHandler.class)
public abstract class AbstractFurnaceScreenHandlerMixin extends AbstractRecipeScreenHandler<Inventory> {
    @Shadow @Final private Inventory inventory;

    public AbstractFurnaceScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @Redirect(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/recipe/book/RecipeBookCategory;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/AbstractFurnaceScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 0))
    private Slot creo_restrictItemSlot(AbstractFurnaceScreenHandler instance, Slot slot) {
        return addSlot(new Slot(inventory, 0, 56, 17) {
            @Override
            public boolean canInsert(ItemStack stack) {
                Item.Settings settings = ((AccessibleItem) stack.getItem()).creo_getItemSettings();
                if (settings instanceof CreoItemSettings creoItemSettings) {
                    Item[] requiredFuels = creoItemSettings.getRequiredFuels();
                    if (requiredFuels != null) {
                        return isRequiredFuel(requiredFuels, inventory.getStack(1));
                    }
                }

                return true;
            }
        });
    }

    @Inject(method = "isSmeltable", at = @At(value = "RETURN"), cancellable = true)
    private void creo_applyItemRequiredFuels(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        Item.Settings settings = ((AccessibleItem) itemStack.getItem()).creo_getItemSettings();
        if (settings instanceof CreoItemSettings creoItemSettings) {
            Item[] requiredFuels = creoItemSettings.getRequiredFuels();
            if (requiredFuels != null) {
                cir.setReturnValue(isRequiredFuel(requiredFuels, inventory.getStack(1)));
            }
        }
    }

    @Unique
    private boolean isRequiredFuel(Item[] required, ItemStack fuel) {
        for (Item item : required) {
            if (fuel.isOf(item)) return true;
        }
        return false;
    }
}
