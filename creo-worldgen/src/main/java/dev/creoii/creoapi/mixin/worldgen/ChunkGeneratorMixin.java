package dev.creoii.creoapi.mixin.worldgen;

import dev.creoii.creoapi.impl.worldgen.ExtraAware;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    @Inject(method = "setStructureStarts", at = @At("TAIL"))
    private void creo_extendStructurePlacements(DynamicRegistryManager registryManager, StructurePlacementCalculator placementCalculator, StructureAccessor structureAccessor, Chunk chunk, StructureTemplateManager structureTemplateManager, CallbackInfo ci) {
        placementCalculator.getStructureSets().forEach(entry -> {
            if (entry.hasKeyAndValue()) {
                StructurePlacement placement = entry.value().placement();
                if (placement instanceof ExtraAware extraAware) {
                    extraAware.creo_setChunkGenerator((ChunkGenerator) (Object) this);
                    extraAware.creo_setRegistryManager(registryManager);
                }
            }
        });
    }
}
