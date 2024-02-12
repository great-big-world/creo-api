package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;
import java.util.stream.Stream;

public class RandomPlacementModifier extends PlacementModifier {
    public static final Codec<RandomPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacementModifier.CODEC.listOf().fieldOf("placements").forGetter(predicate -> {
            return predicate.placements;
        })).apply(instance, RandomPlacementModifier::new);
    });
    private final List<PlacementModifier> placements;

    public RandomPlacementModifier(List<PlacementModifier> placements) {
        this.placements = placements;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.RANDOM;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (placements.size() == 1) {
            CreoWorldgen.LOGGER.warn("Instance of creo:random {} contains 1 placement entry. This is redundant.", context.getPlacedFeature().get().feature().getKey().get());
        }
        PlacementModifier modifier = placements.get(random.nextInt(placements.size()));
        if (modifier instanceof AbstractConditionalPlacementModifier conditional && !conditional.shouldPlace(context, random, pos)) {
            return Stream.of();
        } else
            return modifier.getPositions(context, random, pos);
    }
}
