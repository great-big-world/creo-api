package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.CraterFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Consumer;

public class CraterFeature extends Feature<CraterFeatureConfig> {
    public CraterFeature(Codec<CraterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<CraterFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, context.getOrigin()).down();
        Random random = context.getRandom();
        CraterFeatureConfig config = context.getConfig();
        int radius = config.radius().get(random);
        int depth = config.depth().get(random);
        if (depth > radius) {
            return false;
        }
        int k1 = (depth * depth + radius * radius) / (2 * depth);
        BlockPos pos2 = pos.up(k1 - depth);
        BlockPos.Mutable mutable = pos.mutableCopy();
        Consumer<WorldAccess> consumer = worldAccess -> {
            for (int k = -depth; k <= k1; ++k) {
                boolean bl = false;
                for (int l = -k; l <= k; ++l) {
                    for (int m = -k; m <= k; ++m) {
                        mutable.set(pos, l, k, m);
                        if (!(mutable.getSquaredDistance(pos2) < (double)(k * k)) || worldAccess.getBlockState(mutable).isAir()) continue;
                        bl = true;
                        worldAccess.setBlockState(mutable, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                    }
                }
                if (!bl && k > 0) break;
            }
        };
        if (k1 < 15) consumer.accept(world);
        else {
            ServerWorld serverWorld = world.toServerWorld();
            serverWorld.getServer().execute(() -> consumer.accept(serverWorld));
        }
        return true;
    }
}
