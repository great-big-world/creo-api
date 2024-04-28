package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class NotPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final MapCodec<NotPlacementModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(PlacementModifier.CODEC.fieldOf("modifier").forGetter(predicate -> {
            return predicate.modifier;
        })).apply(instance, NotPlacementModifier::new);
    });
    private final PlacementModifier modifier;

    public NotPlacementModifier(PlacementModifier modifier) {
        this.modifier = modifier;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.NOT;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (modifier instanceof AbstractConditionalPlacementModifier conditional) {
            return !conditional.shouldPlace(context, random, pos);
        } else return true;
    }
}
