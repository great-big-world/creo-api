package dev.creoii.creoapi.mixin.tag.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
    @ModifyExpressionValue(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean creo$applyBlocksEndermanStare(boolean original, @Local ItemStack itemStack) {
        return ItemTagImpl.applyBlocksEndermanStare(itemStack);
    }
}
