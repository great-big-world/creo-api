package dev.creoii.creoapi.mixin.tag.entity;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ShulkerEntity.class)
public class ShulkerEntityMixin {
    @Inject(method = "isInvalidPosition", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void creo_invalidShulkerStates(BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local BlockState blockState) {
        BlockTagImpl.applyInvalidForShulkerTeleport(blockState, cir);
    }
}
