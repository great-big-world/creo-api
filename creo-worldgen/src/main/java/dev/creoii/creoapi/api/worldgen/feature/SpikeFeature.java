package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.SpikeFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.CaveSurface;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Places a spike like {@link net.minecraft.world.gen.feature.UndergroundConfiguredFeatures#LARGE_DRIPSTONE}
 */
public class SpikeFeature extends Feature<SpikeFeatureConfig> {
    public SpikeFeature(Codec<SpikeFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<SpikeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        SpikeFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        if (!canGenerate(world, blockPos)) {
            return false;
        } else {
            Optional<CaveSurface> optional = CaveSurface.create(world, blockPos, config.floorToCeilingSearchRange(), DripstoneHelper::canGenerate, DripstoneHelper::canReplaceOrLava);
            if (optional.isPresent() && optional.get() instanceof CaveSurface.Bounded bounded) {
                if (bounded.getHeight() < 4) {
                    return false;
                } else {
                    int i = (int)((float)bounded.getHeight() * config.maxColumnRadiusToCaveHeightRatio());
                    int j = MathHelper.clamp(i, config.columnRadius().getMin(), config.columnRadius().getMax());
                    int k = MathHelper.nextBetween(random, config.columnRadius().getMin(), j);
                    DripstoneGenerator dripstoneGenerator = createGenerator(blockPos.withY(bounded.getCeiling() - 1), false, random, k, config.stalactiteBluntness(), config.heightScale());
                    DripstoneGenerator dripstoneGenerator2 = createGenerator(blockPos.withY(bounded.getFloor() + 1), true, random, k, config.stalagmiteBluntness(), config.heightScale());
                    WindModifier windModifier2;
                    if (dripstoneGenerator.generateWind(config) && dripstoneGenerator2.generateWind(config)) {
                        windModifier2 = new WindModifier(blockPos.getY(), random, config.windSpeed());
                    } else {
                        windModifier2 = WindModifier.create();
                    }

                    boolean bl = dripstoneGenerator.canGenerate(world, windModifier2);
                    boolean bl2 = dripstoneGenerator2.canGenerate(world, windModifier2);
                    if (bl) {
                        dripstoneGenerator.generate(world, random, windModifier2, config);
                    }

                    if (bl2) {
                        dripstoneGenerator2.generate(world, random, windModifier2, config);
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private static DripstoneGenerator createGenerator(BlockPos pos, boolean isStalagmite, Random random, int scale, FloatProvider bluntness, FloatProvider heightScale) {
        return new DripstoneGenerator(pos, isStalagmite, scale, bluntness.get(random), heightScale.get(random));
    }

    static final class DripstoneGenerator {
        private BlockPos pos;
        private final boolean isStalagmite;
        private int scale;
        private final double bluntness;
        private final double heightScale;

        DripstoneGenerator(BlockPos blockPos, boolean bl, int i, double d, double e) {
            this.pos = blockPos;
            this.isStalagmite = bl;
            this.scale = i;
            this.bluntness = d;
            this.heightScale = e;
        }

        private int getBaseScale() {
            return this.scale(0.0F);
        }

        boolean canGenerate(StructureWorldAccess world, WindModifier wind) {
            while(this.scale > 1) {
                BlockPos.Mutable mutable = this.pos.mutableCopy();
                int i = Math.min(10, this.getBaseScale());

                for(int j = 0; j < i; ++j) {
                    if (world.getBlockState(mutable).isOf(Blocks.LAVA)) {
                        return false;
                    }

                    if (canGenerateBase(world, wind.modify(mutable), this.scale)) {
                        this.pos = mutable;
                        return true;
                    }

                    mutable.move(this.isStalagmite ? Direction.DOWN : Direction.UP);
                }

                this.scale /= 2;
            }

            return false;
        }

        private int scale(float height) {
            return (int)scaleHeightFromRadius(height, scale, heightScale, bluntness);
        }

        void generate(StructureWorldAccess world, Random random, WindModifier wind, SpikeFeatureConfig config) {
            BlockPos basePos = pos;
            for(int i = -scale; i <= scale; ++i) {
                for(int j = -scale; j <= scale; ++j) {
                    float f = MathHelper.sqrt((float)(i * i + j * j));
                    if (!(f > (float)scale)) {
                        int k = scale(f);
                        if (k > 0) {
                            if ((double)random.nextFloat() < 0.2D) {
                                k = (int)((float)k * MathHelper.nextBetween(random, 0.8F, 1.0F));
                            }

                            BlockPos.Mutable mutable = pos.add(i, 0, j).mutableCopy();
                            boolean bl = false;

                            for(int l = 0; l < k; ++l) {
                                BlockPos blockPos = wind.modify(mutable);
                                if (canGenerateOrLava(world, blockPos)) {
                                    bl = true;
                                    Block block = config.state().get(random, blockPos).getBlock();
                                    world.setBlockState(blockPos, block.getDefaultState(), 2);
                                } else if (bl) {
                                    break;
                                }

                                mutable.move(isStalagmite ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }
        }

        boolean generateWind(SpikeFeatureConfig config) {
            return scale >= config.minRadiusForWind() && bluntness >= (double)config.minBluntnessForWind();
        }
    }

    protected static double scaleHeightFromRadius(double radius, double scale, double heightScale, double bluntness) {
        if (radius < bluntness) {
            radius = bluntness;
        }

        double d = 0.384D;
        double e = radius / scale * d;
        double f = 0.75D * Math.pow(e, 1.3333333333333333D);
        double g = Math.pow(e, 0.6666666666666666D);
        double h = 0.3333333333333333D * Math.log(e);
        double i = heightScale * (f - g - h);
        i = Math.max(i, 0.0D);
        return i / d * scale;
    }

    protected static boolean canGenerateBase(StructureWorldAccess world, BlockPos pos, int height) {
        if (canGenerateOrLava(world, pos)) {
            return false;
        } else {
            float f = 6.0F;
            float g = f / (float)height;

            for(float h = 0.0F; h < 6.2831855F; h += g) {
                int i = (int)(MathHelper.cos(h) * (float)height);
                int j = (int)(MathHelper.sin(h) * (float)height);
                if (canGenerateOrLava(world, pos.add(i, 0, j))) {
                    return false;
                }
            }

            return true;
        }
    }

    protected static boolean canGenerate(WorldAccess world, BlockPos pos) {
        return world.testBlockState(pos, DripstoneHelper::canGenerate);
    }

    protected static boolean canGenerateOrLava(WorldAccess world, BlockPos pos) {
        return world.testBlockState(pos, DripstoneHelper::canGenerateOrLava);
    }

    private static final class WindModifier {
        private final int y;
        @Nullable private final Vec3d wind;

        WindModifier(int y, Random random, FloatProvider wind) {
            this.y = y;
            float f = wind.get(random);
            float g = MathHelper.nextBetween(random, 0.0F, 3.1415927F);
            this.wind = new Vec3d(MathHelper.cos(g) * f, 0.0D, MathHelper.sin(g) * f);
        }

        private WindModifier() {
            y = 0;
            wind = null;
        }

        static WindModifier create() {
            return new WindModifier();
        }

        BlockPos modify(BlockPos pos) {
            if (wind == null) {
                return pos;
            } else {
                int i = y - pos.getY();
                Vec3d vec3d = wind.multiply(i);
                return pos.add((int) vec3d.x, 0, (int) vec3d.z);
            }
        }
    }
}