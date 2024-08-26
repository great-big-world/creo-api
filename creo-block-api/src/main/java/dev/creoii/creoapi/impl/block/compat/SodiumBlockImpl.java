package dev.creoii.creoapi.impl.block.compat;

import dev.creoii.creoapi.api.block.CreoBlock;
import me.jellysquid.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderCache;
import me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class SodiumBlockImpl {
    public static void applyRenderOverlayState(BlockState blockState, BlockPos blockPos, long seed, BlockPos.Mutable modelOffset, BlockRenderCache cache, BlockRenderContext context, ChunkBuildBuffers buffers) {
        if (blockState.getBlock() instanceof CreoBlock creoBlock) {
            BlockState state = creoBlock.getOverlayState(blockState, blockPos, Random.create(seed));
            context.update(blockPos, modelOffset, state, cache.getBlockModels().getModel(state), seed);
            cache.getBlockRenderer().renderModel(context, buffers);
        }
    }
}
