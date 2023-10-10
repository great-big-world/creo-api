package dev.creoii.creoapi.api.worldgen.structure.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoStructureProcessorTypes;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseLite;
import dev.creoii.creoapi.api.worldgen.fastnoise.FastNoiseParameters;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FastNoiseStructureProcessor extends StructureProcessor {
    public static final Codec<FastNoiseStructureProcessor> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.BLOCK).optionalFieldOf("replaceable_blocks").forGetter(processor -> {
            return processor.replaceableBlocks;
        }), FastNoiseParameters.REGISTRY_ENTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Codec.DOUBLE.optionalFieldOf("min_threshold", -1d).forGetter(processor -> {
            return processor.minThreshold;
        }), Codec.DOUBLE.optionalFieldOf("max_threshold", 1d).forGetter(processor -> {
            return processor.maxThreshold;
        }), BlockState.CODEC.optionalFieldOf("result", Blocks.AIR.getDefaultState()).forGetter(processor -> {
            return processor.result;
        })).apply(instance, FastNoiseStructureProcessor::new);
    });
    private final Optional<RegistryEntryList<Block>> replaceableBlocks;
    private final RegistryEntry<FastNoiseParameters> noise;
    private final double minThreshold;
    private final double maxThreshold;
    private final BlockState result;

    private FastNoiseStructureProcessor(Optional<RegistryEntryList<Block>> replaceableBlocks, RegistryEntry<FastNoiseParameters> noise, double minThreshold, double maxThreshold, BlockState result) {
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
        if (!noise.hasKeyAndValue() || currentBlockInfo.state() == result)
            return currentBlockInfo;
        ServerWorld serverWorld = ((ServerWorldAccess) world).toServerWorld();

        FastNoiseLite fastNoiseLite = new FastNoiseLite(noise.value());
        if (noise.value().seed() == 1337L)
            fastNoiseLite.seed(serverWorld.getSeed());
        BlockPos blockPos = currentBlockInfo.pos();
        double value = fastNoiseLite.getNoise(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        if (value >= minThreshold && value <= maxThreshold) {
            if (replaceableBlocks.isPresent() && !currentBlockInfo.state().isIn(replaceableBlocks.get())) {
                return currentBlockInfo;
            }
            if (result.isAir())
                return null;
            return new StructureTemplate.StructureBlockInfo(blockPos, result, currentBlockInfo.nbt());
        }
        return currentBlockInfo;
    }
}
