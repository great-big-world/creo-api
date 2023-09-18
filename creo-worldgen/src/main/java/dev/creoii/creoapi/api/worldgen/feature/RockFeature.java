package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.RockFeatureConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * Places a rock like {@link net.minecraft.world.gen.feature.MiscConfiguredFeatures#FOREST_ROCK}
 */
public class RockFeature extends Feature<RockFeatureConfig> {
    public RockFeature(Codec<RockFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<RockFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        RockFeatureConfig config;
        for (config = context.getConfig(); origin.getY() > world.getBottomY() + 3; origin = origin.down()) {
            if (!world.isAir(origin.down())) {
                break;
            }
        }

        if (origin.getY() <= world.getBottomY() + 3) return false;
        else {
            for (int i = 0; i < 3; ++i) {
                int j = random.nextInt(config.size().get(random));
                int k = random.nextInt(config.height().get(random));
                int l = random.nextInt(config.size().get(random));
                float f = (float)(j + k + l) * 0.333F + 0.5F;

                for (BlockPos pos2 : BlockPos.iterate(origin.add(-j, -k, -l), origin.add(j, k, l))) {
                    if (pos2.getSquaredDistance(origin) <= (double) (f * f)) {
                        world.setBlockState(pos2, config.state().get(random, pos2), 4);
                    }
                }
                origin = origin.add(-1 + random.nextInt(config.size().get(random)), -random.nextInt(config.size().get(random)), -1 + random.nextInt(config.size().get(random)));
            }
            return true;
        }
    }
}