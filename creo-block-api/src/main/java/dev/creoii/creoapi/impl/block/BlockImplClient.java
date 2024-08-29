package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import net.minecraft.client.render.chunk.ChunkRendererRegion;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class BlockImplClient {
    public static void applyRenderOverlayState(BlockState blockState, BlockPos pos, Random random, BlockBufferBuilderStorage storage, BlockRenderManager blockRenderManager, ChunkRendererRegion chunkRendererRegion, MatrixStack matrixStack) {
        if (blockState.getBlock() instanceof CreoBlock creoBlock) {
            BlockState state = creoBlock.getOverlayState(blockState, pos, random);
            BufferBuilder bufferBuilder = storage.get(RenderLayers.getBlockLayer(state));
            if (bufferBuilder.isBuilding() && state != Blocks.AIR.getDefaultState()) {
                blockRenderManager.renderBlock(state, pos, chunkRendererRegion, matrixStack, bufferBuilder, true, random);
            }
        }
    }
}
