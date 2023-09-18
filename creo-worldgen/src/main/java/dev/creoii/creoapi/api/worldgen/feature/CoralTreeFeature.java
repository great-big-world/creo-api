package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class CoralTreeFeature extends Feature<SimpleBlockFeatureConfig> {
    public CoralTreeFeature(Codec<SimpleBlockFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleBlockFeatureConfig> context) {
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos pos = context.getOrigin();
        BlockState state = context.getConfig().toPlace().get(random, pos);

        BlockPos.Mutable mutable = pos.mutableCopy();
        int i = random.nextInt(3) + 1;
        for (int j = 0; j < i; ++j) {
            if (!structureWorldAccess.setBlockState(mutable, state, Block.NOTIFY_ALL)) {
                return true;
            }
            mutable.move(Direction.UP);
        }
        BlockPos blockPos = mutable.toImmutable();
        int k = random.nextInt(3) + 2;
        List<Direction> list = Direction.Type.HORIZONTAL.getShuffled(random);
        List<Direction> list2 = list.subList(0, k);
        for (Direction direction : list2) {
            mutable.set(blockPos);
            mutable.move(direction);
            int l = random.nextInt(5) + 2;
            int m = 0;
            for (int n = 0; n < l && structureWorldAccess.setBlockState(mutable, state, Block.NOTIFY_ALL); ++n) {
                mutable.move(Direction.UP);
                if (n != 0 && (++m < 2 || !(random.nextFloat() < .25f)))
                    continue;
                mutable.move(direction);
                m = 0;
            }
        }
        return true;
    }
}
