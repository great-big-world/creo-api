package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class HeightFilterPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<HeightFilterPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Heightmap.Type.CODEC.fieldOf("heightmap").forGetter(predicate -> {
            return predicate.heightmap;
        }), HeightProvider.CODEC.fieldOf("lower").forGetter(predicate -> {
            return predicate.lower;
        }), HeightProvider.CODEC.fieldOf("upper").forGetter(predicate -> {
            return predicate.upper;
        })).apply(instance, HeightFilterPlacementModifier::new);
    });
    private final Heightmap.Type heightmap;
    private final HeightProvider lower;
    private final HeightProvider upper;

    public HeightFilterPlacementModifier(Heightmap.Type heightmap, HeightProvider lower, HeightProvider upper) {
        this.heightmap = heightmap;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.HEIGHT_FILTER;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        int topY = context.getTopY(heightmap, pos.getX(), pos.getZ());
        return topY >= lower.get(random, context) && topY <= upper.get(random, context);
    }
}
