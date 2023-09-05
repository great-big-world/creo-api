package dev.creoii.creoapi.mixin.modification.block;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow protected abstract BlockState asBlockState();

    @Inject(method = "hasEmissiveLighting", at = @At("HEAD"), cancellable = true)
    private void creo_applyEmissiveLighting(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockModificationImpl.applyEmissiveLighting(asBlockState(), world, pos, cir);
    }

    @Inject(method = "shouldPostProcess", at = @At("HEAD"), cancellable = true)
    private void creo_applyPostProcessing(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockModificationImpl.applyPostProcessing(asBlockState(), world, pos, cir);
    }

    @Inject(method = "shouldSuffocate", at = @At("HEAD"), cancellable = true)
    private void creo_applySuffocation(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockModificationImpl.applySuffocates(asBlockState(), world, pos, cir);
    }

    @Inject(method = "shouldBlockVision", at = @At("HEAD"), cancellable = true)
    private void creo_applyBlocksVision(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockModificationImpl.applyBlocksVision(asBlockState(), world, pos, cir);
    }
}