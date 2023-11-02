package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.ItemSettingsImpl;
import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Redirect(method = "insertAndExtract", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/HopperBlockEntity;setTransferCooldown(I)V"))
    private static void creo_applyItemHopperTransferRate(HopperBlockEntity instance, int transferCooldown) {
        ItemSettingsImpl.applyHopperTransferRate(instance);
    }
}
