package dev.creoii.creoapi.mixin.tag.block.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private static boolean creo_applyBrewingFuel(ItemStack instance, Item item) {
        return ItemTagImpl.applyBrewingFuel(instance);
    }

    @Redirect(method = "isValid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean creo_applyValidBrewingFuel(ItemStack instance, Item item) {
        return ItemTagImpl.applyBrewingFuel(instance);
    }
}