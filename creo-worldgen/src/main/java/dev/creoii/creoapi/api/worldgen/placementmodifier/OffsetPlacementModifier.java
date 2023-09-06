package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.Stream;

public class OffsetPlacementModifier extends PlacementModifier {
    public static final Codec<OffsetPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(IntProvider.createValidatingCodec(0, 16).fieldOf("x_offset").forGetter(modifier -> {
            return modifier.offsetX;
        }), IntProvider.createValidatingCodec(0, 16).optionalFieldOf("y_offset", ConstantIntProvider.ZERO).forGetter(modifier -> {
            return modifier.offsetY;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("z_offset").forGetter(modifier -> {
            return modifier.offsetZ;
        })).apply(instance, OffsetPlacementModifier::new);
    });
    private final IntProvider offsetX;
    private final IntProvider offsetY;
    private final IntProvider offsetZ;

    public OffsetPlacementModifier(IntProvider offsetX, IntProvider offsetY, IntProvider offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public OffsetPlacementModifier(IntProvider offsetX, IntProvider offsetZ) {
        this(offsetX, ConstantIntProvider.ZERO, offsetZ);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.OFFSET;
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        return Stream.of(context.getWorld().getChunk(pos).getPos().getStartPos().add(offsetX.get(random), offsetY.get(random), offsetZ.get(random)));
    }
}
