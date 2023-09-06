package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class DistanceFromZeroPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<DistanceFromZeroPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("distance").forGetter(predicate -> {
            return (int) MathHelper.sqrt(predicate.squaredDistance);
        })).apply(instance, DistanceFromZeroPlacementModifier::new);
    });
    private final int squaredDistance;

    public DistanceFromZeroPlacementModifier(int squaredDistance) {
        this.squaredDistance = squaredDistance * squaredDistance;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.DISTANCE_FROM_ZERO;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        return pos.getSquaredDistance(Vec3d.ZERO) >= squaredDistance;
    }
}
