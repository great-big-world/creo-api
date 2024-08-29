package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.CreoBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.chunk.BlockBufferAllocatorStorage;
import net.minecraft.client.render.chunk.ChunkRendererRegion;
import net.minecraft.client.render.chunk.SectionBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class BlockImplClient {
    public static void applyRenderOverlayState(SectionBuilder sectionBuilder, Map<RenderLayer, BufferBuilder> map, BlockBufferAllocatorStorage allocatorStorage, BlockRenderManager blockRenderManager, BlockState blockState, BlockPos pos, ChunkRendererRegion renderRegion, MatrixStack matrixStack, Random random) {
        if (blockState.getBlock() instanceof CreoBlock creoBlock) {
            BlockState overlayState = creoBlock.getOverlayState(blockState, pos, random);
            BufferBuilder bufferBuilder = sectionBuilder.beginBufferBuilding(map, allocatorStorage, RenderLayers.getBlockLayer(overlayState));
            if (overlayState != Blocks.AIR.getDefaultState()) {
                blockRenderManager.renderBlock(overlayState, pos, renderRegion, matrixStack, bufferBuilder, true, random);
            }
        }
    }
}
