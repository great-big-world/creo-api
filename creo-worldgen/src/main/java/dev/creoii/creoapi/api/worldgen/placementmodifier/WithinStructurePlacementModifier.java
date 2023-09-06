package dev.creoii.creoapi.api.worldgen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.creoapi.api.worldgen.CreoPlacementModifierTypes;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.structure.Structure;

public class WithinStructurePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<WithinStructurePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.STRUCTURE).fieldOf("structure").forGetter(placement -> {
            return placement.structures;
        })).apply(instance, WithinStructurePlacementModifier::new);
    });
    private final RegistryEntryList<Structure> structures;

    public WithinStructurePlacementModifier(RegistryEntryList<Structure> structures) {
        this.structures = structures;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return CreoPlacementModifierTypes.WITHIN_STRUCTURE;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld().isClient()) return false;

        StructureAccessor structureManager = context.getWorld().toServerWorld().getStructureAccessor();
        for (RegistryEntry<Structure> entry : structures) {
            if (!entry.hasKeyAndValue())
                continue;

            if (structureManager.getStructureAt(pos, entry.value()).hasChildren()) {
                return true;
            }
        }
        return false;
    }
}