package dev.creoii.creoapi.mixin.tag.item;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    private void creo_shearsMineables(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        BlockTagImpl.applyShearsMineables(state, cir);
    }
}