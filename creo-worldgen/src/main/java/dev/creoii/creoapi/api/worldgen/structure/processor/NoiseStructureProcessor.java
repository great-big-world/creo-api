package dev.creoii.creoapi.api.worldgen.structure.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructureProcessorTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NoiseStructureProcessor extends StructureProcessor {
    public static final Codec<NoiseStructureProcessor> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.BLOCK).optionalFieldOf("replaceable_blocks").forGetter(processor -> {
            return processor.replaceableBlocks;
        }), RegistryKey.createCodec(RegistryKeys.NOISE_PARAMETERS).fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", -1d).forGetter(processor -> {
            return processor.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", 1d).forGetter(processor -> {
            return processor.maxThreshold;
        }), BlockState.CODEC.optionalFieldOf("result", Blocks.AIR.getDefaultState()).forGetter(processor -> {
            return processor.result;
        })).apply(instance, NoiseStructureProcessor::new);
    });
    private final Optional<RegistryEntryList<Block>> replaceableBlocks;
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final double minThreshold;
    private final double maxThreshold;
    private final BlockState result;

    private NoiseStructureProcessor(Optional<RegistryEntryList<Block>> replaceableBlocks, RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, double minThreshold, double maxThreshold, BlockState result) {
        this.replaceableBlocks = replaceableBlocks;
        this.noise = noise;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.result = result;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CreoStructureProcessorTypes.NOISE;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        if (currentBlockInfo.state == result)
            return currentBlockInfo;
        ServerWorld serverWorld = ((ServerWorldAccess) world).toServerWorld();

        BlockPos blockPos = currentBlockInfo.pos;
        double value = serverWorld.getChunkManager().getNoiseConfig().getOrCreateSampler(noise).sample(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        if (value >= minThreshold && value <= maxThreshold) {
            if (replaceableBlocks.isPresent() && !currentBlockInfo.state.isIn(replaceableBlocks.get())) {
                return currentBlockInfo;
            }
            if (result.isAir())
                return null;
            return new StructureTemplate.StructureBlockInfo(blockPos, result, currentBlockInfo.nbt);
        }
        return currentBlockInfo;
    }
}
