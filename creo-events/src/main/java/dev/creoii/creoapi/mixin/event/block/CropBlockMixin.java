package dev.creoii.creoapi.mixin.event.block;

import dev.creoii.creoapi.impl.event.BlockEventImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @Shadow public abstract BlockState withAge(int age);
    @Shadow public abstract int getAge(BlockState state);
    @Shadow protected abstract int getGrowthAmount(World world);
    @Shadow
    protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
        return 0;
    }

    @Inject(method = "canGrow", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void creo_applyCropGrowBonemealEvent(World world, Random random, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        int i = getAge(state) + getGrowthAmount(world);
        float f = getAvailableMoisture((CropBlock) (Object) this, world, pos);
        BlockEventImpl.applyCropGrowEvent(world, pos, state, withAge(i), i, f, cir);
    }

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void creo_applyCropGrowRandomEvent(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, int i, float f) {
        BlockEventImpl.applyCropGrowRandomEvent(world, pos, state, withAge(i), i, f, ci);
    }
}
