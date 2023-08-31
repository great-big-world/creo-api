package dev.creoii.creoapi.mixin.tag.block;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin {
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void creo_netherWartPlaceables(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockTagImpl.applyNetherWartPlantableOn(floor, cir);
    }
}
