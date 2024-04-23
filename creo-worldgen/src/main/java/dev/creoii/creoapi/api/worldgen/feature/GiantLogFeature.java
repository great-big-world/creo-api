package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.GiantLogFeatureConfig;
import dev.creoii.creoapi.api.worldgen.placementmodifier.FastNoisePlacementModifier;
import dev.creoii.creoapi.api.worldgen.placementmodifier.NoisePlacementModifier;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class GiantLogFeature extends Feature<GiantLogFeatureConfig> {
    public GiantLogFeature(Codec<GiantLogFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<GiantLogFeatureConfig> context) {
        final Random random = context.getRandom();
        Direction.Axis axis = Direction.Axis.pickRandomAxis(context.getRandom());
        StructureWorldAccess world = context.getWorld();
        GiantLogFeatureConfig config = context.getConfig();

        int length = config.length().get(random);
        int radius = config.radius().get(random);

        BlockPos origin = context.getOrigin();
        switch (axis) {
            case X -> origin = new BlockPos(origin.getY(), origin.getX(), origin.getZ());
            case Y -> origin = new BlockPos(origin.getX(), origin.getY(), origin.getZ());
            case Z -> origin = new BlockPos(origin.getX(), origin.getZ(), origin.getY());
        }

        int start = origin.getY();
        int end = start + length;

        int minStartOffset = Integer.MAX_VALUE;
        int minEndOffset = Integer.MAX_VALUE;
        for (int x = origin.getX() - radius; x <= origin.getX() + radius; ++x) {
            for (int z = origin.getZ() - radius; z <= origin.getZ() + radius; ++z) {
                int startOffset = config.endOffset().get(random);
                int endOffset = config.endOffset().get(random);

                if (startOffset < minStartOffset)
                    minStartOffset = startOffset;
                if (endOffset < minEndOffset)
                    minEndOffset = endOffset;

                Optional<Either<NoisePlacementModifier, FastNoisePlacementModifier>> optional = config.noisePlacementModifier();
                for (int y = start + startOffset; y < end - endOffset; ++y) {
                    double distance = Math.sqrt(Math.pow(x - origin.getX(), 2) + Math.pow(z - origin.getZ(), 2));
                    if (distance <= radius) {
                        BlockPos pos = switch (axis) {
                            case X -> new BlockPos(y, x, z);
                            case Y -> new BlockPos(x, y, z);
                            case Z -> new BlockPos(x, z, y);
                        };
                        if (Math.abs(radius - distance) < .9d) {
                            BlockState state = config.outerState().get(random, pos);
                            if (optional.isPresent()) {
                                Either<NoisePlacementModifier, FastNoisePlacementModifier> either = optional.get();
                                FeaturePlacementContext placementContext = new FeaturePlacementContext(world, context.getGenerator(), Optional.empty());
                                if (either.left().isPresent() && either.left().get().shouldPlace(placementContext, random, pos)) {
                                    world.setBlockState(pos, state.with(PillarBlock.AXIS, axis), 2);
                                } else if (either.right().isPresent() && either.right().get().shouldPlace(placementContext, random, pos)) {
                                    world.setBlockState(pos, state.with(PillarBlock.AXIS, axis), 2);
                                }
                            } else {
                                world.setBlockState(pos, state.with(PillarBlock.AXIS, axis), 2);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
