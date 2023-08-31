package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class AllOfPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<AllOfPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacementModifier.CODEC.listOf().fieldOf("placements").forGetter(predicate -> {
            return predicate.placements;
        })).apply(instance, AllOfPlacementModifier::new);
    });
    private final List<PlacementModifier> placements;

    public AllOfPlacementModifier(List<PlacementModifier> placements) {
        this.placements = placements;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CPlacementModifierTypes.ALL_OF;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        for (PlacementModifier modifier : placements) {
            if (modifier instanceof AbstractConditionalPlacementModifier conditional && !conditional.shouldPlace(context, random, pos)) {
                return false;
            }
        }
        return true;
    }
}
