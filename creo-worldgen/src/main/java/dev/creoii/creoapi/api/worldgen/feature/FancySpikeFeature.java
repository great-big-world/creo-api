package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.FancySpikeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * Places a fancy spike like {@link net.minecraft.world.gen.feature.MiscConfiguredFeatures#ICE_SPIKE}
 */
public class FancySpikeFeature extends Feature<FancySpikeFeatureConfig> {
    public FancySpikeFeature(Codec<FancySpikeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<FancySpikeFeatureConfig> context) {
        int l;
        int k;
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        FancySpikeFeatureConfig config = context.getConfig();
        while (world.isAir(blockPos) && blockPos.getY() > world.getBottomY() + 2) {
            blockPos = blockPos.down();
        }
        blockPos = blockPos.up(random.nextInt(4));
        int i = config.baseHeight().get(random);
        int j = i / 4 + random.nextInt(2);
        if (random.nextFloat() <= config.extraHeightChance()) {
            blockPos = blockPos.up(config.extraHeight().get(random));
        }
        BlockPos pos;
        for (k = 0; k < i; ++k) {
            float f = (1.0f - (float)k / (float)i) * (float)j;
            l = MathHelper.ceil(f);
            for (int m = -l; m <= l; ++m) {
                float g = (float)MathHelper.abs(m) - 0.25f;
                for (int n = -l; n <= l; ++n) {
                    float h = (float)MathHelper.abs(n) - 0.25f;
                    if ((m != 0 || n != 0) && g * g + h * h > f * f || (m == -l || m == l || n == -l || n == l) && random.nextFloat() > 0.75f) continue;
                    BlockState blockState = world.getBlockState(blockPos.add(m, k, n));
                    if (blockState.isAir() || IceSpikeFeature.isSoil(blockState) || blockState.isOf(Blocks.SNOW_BLOCK) || blockState.isOf(Blocks.ICE)) {
                        pos = blockPos.add(m, k, n);
                        setBlockState(world, pos, config.state().get(random, pos));
                    }
                    if (k == 0 || l <= 1 || !(blockState = world.getBlockState(blockPos.add(m, -k, n))).isAir() && !IceSpikeFeature.isSoil(blockState) && !blockState.isOf(Blocks.SNOW_BLOCK) && !blockState.isOf(Blocks.ICE)) continue;
                    pos = blockPos.add(m, -k, n);
                    setBlockState(world, pos, config.state().get(random, pos));
                }
            }
        }
        k = j - 1;
        if (k < 0) {
            k = 0;
        } else if (k > 1) {
            k = 1;
        }
        for (int o = -k; o <= k; ++o) {
            for (l = -k; l <= k; ++l) {
                BlockState blockState2;
                BlockPos blockPos2 = blockPos.add(o, -1, l);
                int p = 50;
                if (Math.abs(o) == 1 && Math.abs(l) == 1) {
                    p = random.nextInt(5);
                }
                while (blockPos2.getY() > 50 && ((blockState2 = world.getBlockState(blockPos2)).isAir() || IceSpikeFeature.isSoil(blockState2) || blockState2.isOf(Blocks.SNOW_BLOCK) || blockState2.isOf(Blocks.ICE) || blockState2.isOf(Blocks.PACKED_ICE))) {
                    setBlockState(world, blockPos2, config.state().get(random, blockPos2));
                    blockPos2 = blockPos2.down();
                    if (--p > 0) continue;
                    blockPos2 = blockPos2.down(random.nextInt(5) + 1);
                    p = random.nextInt(5);
                }
            }
        }
        return true;
    }
}
