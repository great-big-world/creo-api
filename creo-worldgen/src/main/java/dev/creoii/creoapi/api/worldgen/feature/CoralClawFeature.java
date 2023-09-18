package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;
import java.util.stream.Stream;

public class CoralClawFeature extends Feature<SimpleBlockFeatureConfig> {
    public CoralClawFeature(Codec<SimpleBlockFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleBlockFeatureConfig> context) {
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos pos = context.getOrigin();
        BlockState state = context.getConfig().toPlace().get(random, pos);

        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = random.nextInt(2) + 2;
        List<Direction> list = Util.copyShuffled(Stream.of(direction, direction.rotateYClockwise(), direction.rotateYCounterclockwise()), random);
        List<Direction> list2 = list.subList(0, i);
        block0: for (Direction direction2 : list2) {
            int l;
            int k;
            Direction direction3;
            BlockPos.Mutable mutable = pos.mutableCopy();
            int j = random.nextInt(2) + 1;
            mutable.move(direction2);
            if (direction2 == direction) {
                direction3 = direction;
                k = random.nextInt(3) + 2;
            } else {
                mutable.move(Direction.UP);
                Direction[] directions = new Direction[]{direction2, Direction.UP};
                direction3 = Util.getRandom(directions, random);
                k = random.nextInt(3) + 3;
            }
            for (l = 0; l < j && structureWorldAccess.setBlockState(mutable, state, Block.NOTIFY_ALL); ++l) {
                mutable.move(direction3);
            }
            mutable.move(direction3.getOpposite());
            mutable.move(Direction.UP);
            for (l = 0; l < k; ++l) {
                mutable.move(direction);
                if (!structureWorldAccess.setBlockState(mutable, state, Block.NOTIFY_ALL))
                    continue block0;
                if (!(random.nextFloat() < .25f))
                    continue;
                mutable.move(Direction.UP);
            }
        }
        return true;
    }
}
