package dev.creoii.creoapi.api.worldgen;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreoWorldgen implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final Logger LOGGER = LoggerFactory.getLogger(CreoWorldgen.class);

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
