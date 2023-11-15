package dev.creoii.creoapi.mixin.block;

import dev.creoii.creoapi.impl.block.SpreadableImpl;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void creo_applySpreadables(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        SpreadableImpl.applySpreadables(state, world, pos, random, ci);
    }
}
