package dev.creoii.creoapi.api.worldgen.materialrule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.impl.worldgen.util.WorldAwareNoiseConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.jetbrains.annotations.Nullable;

public class ExposedMaterialCondition implements MaterialRules.MaterialCondition {
    public static final Codec<ExposedMaterialCondition> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.BLOCK).optionalFieldOf("can_be_exposed_to", RegistryEntryList.of(RegistryEntry.of(Blocks.AIR))).forGetter(predicate -> {
            return predicate.canBeExposedTo;
        }), Codec.intRange(1, 4).optionalFieldOf("min_exposed_faces", 1).forGetter(predicate -> {
            return predicate.minExposedFaces;
        }), Codec.STRING.optionalFieldOf("axis", "horizontal").forGetter(predicate -> {
            return predicate.axis;
        })).apply(instance, ExposedMaterialCondition::new);
    });
    public static final CodecHolder<ExposedMaterialCondition> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final RegistryEntryList<Block> canBeExposedTo;
    private final int minExposedFaces;
    private final String axis;

    public ExposedMaterialCondition(RegistryEntryList<Block> canBeExposedTo, int minExposedFaces, String axis) {
        this.canBeExposedTo = canBeExposedTo;
        this.minExposedFaces = minExposedFaces;
        if (!(axis.equalsIgnoreCase("horizontal") || axis.equalsIgnoreCase("vertical"))) {
            this.axis = "horizontal";
        } else
            this.axis = axis;
    }

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext context) {
        return new ExposedPredicate(context);
    }

    private class ExposedPredicate extends MaterialRules.HorizontalLazyAbstractPredicate {
        public ExposedPredicate(MaterialRules.MaterialRuleContext context) {
            super(context);
        }

        @Override
        @SuppressWarnings("deprecation")
        protected boolean test() {
            World world = ((WorldAwareNoiseConfig) context.noiseConfig).creo_getWorld();
            Chunk chunk = context.chunk;
            BlockPos pos = new BlockPos(context.blockX, chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, context.blockX, context.blockZ), context.blockZ);

            // east
            if (!world.isChunkLoaded(pos.add(16, 0, 0))) {
                int chunkX = ChunkSectionPos.getLocalCoord(pos.getX());
                if (chunkX == 0)
                    return testExposedFaces(chunk, pos, Direction.WEST);
            }
            // south
            if (!world.isChunkLoaded(pos.add(0, 0, 16))) {
                int chunkZ = ChunkSectionPos.getLocalCoord(pos.getZ());
                if (chunkZ == 0)
                    return testExposedFaces(chunk, pos, Direction.NORTH);
            }
            // west
            if (!world.isChunkLoaded(pos.add(-16, 0, 0))) {
                int chunkX = ChunkSectionPos.getLocalCoord(pos.getX());
                if (chunkX == 15)
                    return testExposedFaces(chunk, pos, Direction.EAST);
            }
            // north
            if (!world.isChunkLoaded(pos.add(0, 0, -16))) {
                int chunkZ = ChunkSectionPos.getLocalCoord(pos.getZ());
                if (chunkZ == 15)
                    return testExposedFaces(chunk, pos, Direction.SOUTH);
            }

            return testExposedFaces(chunk, pos, null);
        }

        private boolean testExposedFaces(Chunk chunk, BlockPos pos, @Nullable Direction ignore) {
            int exposedFaces = 0;
            for (Direction direction : Direction.Type.valueOf(axis.toUpperCase())) {
                if (direction == ignore)
                    continue;
                BlockState state = chunk.getBlockState(pos.offset(direction));
                if (state.isIn(canBeExposedTo))
                    ++exposedFaces;
            }

            return exposedFaces >= minExposedFaces;
        }
    }
}