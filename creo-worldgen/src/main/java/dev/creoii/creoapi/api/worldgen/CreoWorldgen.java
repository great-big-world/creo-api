package dev.creoii.creoapi.api.worldgen;

import net.fabricmc.api.ModInitializer;

public class CreoWorldgen implements ModInitializer {
    public static final String NAMESPACE = "creo";

    @Override
    public void onInitialize() {
        CreoDensityFunctionTypes.register();
        CreoFeatures.register();
        CreoMaterialConditions.register();
        CreoPlacementModifierTypes.register();
        CreoStructurePlacementTypes.register();
        CreoStructureProcessorTypes.register();
    }
}
