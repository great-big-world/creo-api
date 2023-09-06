package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin {
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean creo_applyRepairsIronGolems(ItemStack instance, Item item) {
        return ItemTagImpl.applyRepairsIronGolems(instance);
    }
}
