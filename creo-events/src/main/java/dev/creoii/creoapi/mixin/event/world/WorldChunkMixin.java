package dev.creoii.creoapi.mixin.event.world;

import dev.creoii.creoapi.impl.event.BlockEventImpl;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin {
    @Shadow public abstract BlockState getBlockState(BlockPos pos);
    @Shadow @Final World world;

    @Inject(method = "setBlockState", at = @At("HEAD"), cancellable = true)
    private void creo_applyBlockChangeEvent(BlockPos pos, BlockState state, boolean moved, CallbackInfoReturnable<BlockState> cir) {
        BlockEventImpl.applyBlockChangeEvent(world, pos, state, getBlockState(pos), moved, cir);
    }
}
