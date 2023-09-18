package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.BlobSpreadFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * Places a blob like {@link net.minecraft.world.gen.feature.NetherConfiguredFeatures#GLOWSTONE_EXTRA}
 */
public class BlobSpreadFeature extends Feature<BlobSpreadFeatureConfig> {
    public BlobSpreadFeature(Codec<BlobSpreadFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<BlobSpreadFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        if (!world.isAir(origin)) {
            return false;
        }
        Random random = context.getRandom();
        BlobSpreadFeatureConfig config = context.getConfig();
        BlockState state = config.state();
        world.setBlockState(origin, state, Block.NOTIFY_LISTENERS);
        int xSpread = config.xSpread().get(random);
        int ySpread = config.ySpread().get(random);
        int zSpread = config.zSpread().get(random);
        for (int i = 0; i < config.tries().get(random); ++i) {
            BlockPos pos = origin.add(random.nextInt(xSpread) - random.nextInt(xSpread), -random.nextInt(ySpread), random.nextInt(zSpread) - random.nextInt(zSpread));
            if (!world.getBlockState(pos).isAir()) continue;
            int j = 0;
            for (Direction direction : Direction.values()) {
                if (world.getBlockState(pos.offset(direction)).isOf(state.getBlock())) {
                    ++j;
                }
                if (j > 1) break;
            }
            if (j != 1) continue;
            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
        }
        return true;
    }
}
