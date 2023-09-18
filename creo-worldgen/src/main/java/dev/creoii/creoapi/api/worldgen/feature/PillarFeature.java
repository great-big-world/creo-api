package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.PillarFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

/**
 * Places a thin pillar like {@link net.minecraft.world.gen.feature.NetherConfiguredFeatures#BASALT_PILLAR}
 */
public class PillarFeature extends Feature<PillarFeatureConfig> {
    public PillarFeature(Codec<PillarFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<PillarFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockStateProvider state = context.getConfig().state();
        if (!world.isAir(blockPos) || world.isAir(blockPos.up())) {
            return false;
        }
        BlockPos.Mutable mutable = blockPos.mutableCopy();
        BlockPos.Mutable mutable2 = blockPos.mutableCopy();
        boolean bl = true;
        boolean bl2 = true;
        boolean bl3 = true;
        boolean bl4 = true;
        while (world.isAir(mutable)) {
            if (world.isOutOfHeightLimit(mutable)) {
                return true;
            }
            world.setBlockState(mutable, state.get(random, mutable), Block.NOTIFY_LISTENERS);
            bl = bl && stopOrPlaceState(world, random, mutable2.set(mutable, Direction.NORTH), state);
            bl2 = bl2 && stopOrPlaceState(world, random, mutable2.set(mutable, Direction.SOUTH), state);
            bl3 = bl3 && stopOrPlaceState(world, random, mutable2.set(mutable, Direction.WEST), state);
            bl4 = bl4 && stopOrPlaceState(world, random, mutable2.set(mutable, Direction.EAST), state);
            mutable.move(Direction.DOWN);
        }
        mutable.move(Direction.UP);
        tryPlaceState(world, random, mutable2.set(mutable, Direction.NORTH), state);
        tryPlaceState(world, random, mutable2.set(mutable, Direction.SOUTH), state);
        tryPlaceState(world, random, mutable2.set(mutable, Direction.WEST), state);
        tryPlaceState(world, random, mutable2.set(mutable, Direction.EAST), state);
        mutable.move(Direction.DOWN);
        BlockPos.Mutable mutable3 = new BlockPos.Mutable();
        int radius = context.getConfig().radius().get(random);
        for (int i = -radius; i < radius + 1; ++i) {
            for (int j = -radius; j < radius + 1; ++j) {
                int k = MathHelper.abs(i) * MathHelper.abs(j);
                if (random.nextInt(10) >= 10 - k)
                    continue;
                mutable3.set(mutable.add(i, 0, j));
                while (world.isAir(mutable2.set(mutable3, Direction.DOWN)))
                    mutable3.move(Direction.DOWN);
                if (world.isAir(mutable2.set(mutable3, Direction.DOWN)))
                    continue;
                world.setBlockState(mutable3, state.get(random, mutable3), Block.NOTIFY_LISTENERS);
            }
        }
        return true;
    }

    private void tryPlaceState(WorldAccess world, Random random, BlockPos pos, BlockStateProvider state) {
        if (random.nextBoolean()) world.setBlockState(pos, state.get(random, pos), 2);
    }

    private boolean stopOrPlaceState(WorldAccess world, Random random, BlockPos pos, BlockStateProvider state) {
        if (random.nextInt(10) != 0) {
            world.setBlockState(pos, state.get(random, pos), 2);
            return true;
        } else return false;
    }
}