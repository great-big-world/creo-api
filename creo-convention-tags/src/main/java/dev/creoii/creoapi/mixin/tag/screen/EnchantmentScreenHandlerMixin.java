package dev.creoii.creoapi.mixin.tag.screen;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
    @Redirect(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 1))
    private Slot creo_applyEnchantingFuel(EnchantmentScreenHandler instance, Slot slot) {
        return ItemTagImpl.applyEnchantingFuel((EnchantmentScreenHandler) (Object) this, slot);
    }

    @Redirect(method = "quickMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean creo_applyEnchantingFuelQuickMove(ItemStack instance, Item item) {
        return ItemTagImpl.applyEnchantingFuelQuickMove(instance);
    }
}
