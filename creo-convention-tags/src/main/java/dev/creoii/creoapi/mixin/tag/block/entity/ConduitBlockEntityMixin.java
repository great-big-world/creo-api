package dev.creoii.creoapi.mixin.tag.block.entity;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {
    @Inject(method = "updateActivatingBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void creo_applyConduitFrameBaseBlocks(World world, BlockPos pos, List<BlockPos> activatingBlocks, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int m, int n, BlockPos blockPos2, BlockState blockState) {
        BlockTagImpl.applyConduitFrameBaseBlocks(blockState, blockPos2, activatingBlocks, cir);
    }

    @Redirect(method = "updateActivatingBlocks", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean creo_cancelActivatingBlocks(List<BlockPos> instance, E e) {
        return false;
    }
}
