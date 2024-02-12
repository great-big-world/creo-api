package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class AnyOfPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<AnyOfPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacementModifier.CODEC.listOf().fieldOf("placements").forGetter(predicate -> {
            return predicate.placements;
        })).apply(instance, AnyOfPlacementModifier::new);
    });
    private final List<PlacementModifier> placements;

    public AnyOfPlacementModifier(List<PlacementModifier> placements) {
        this.placements = placements;
        if (placements.size() == 1) {
            CreoWorldgen.LOGGER.error("Instance of {} contains 1 placement entry. This is redundant.", Registries.PLACEMENT_MODIFIER_TYPE.getId(getType()));
        }
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.ANY_OF;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        for (PlacementModifier modifier : placements) {
            if (modifier instanceof AbstractConditionalPlacementModifier conditional && conditional.shouldPlace(context, random, pos)) {
                return true;
            }
        }
        return false;
    }
}
