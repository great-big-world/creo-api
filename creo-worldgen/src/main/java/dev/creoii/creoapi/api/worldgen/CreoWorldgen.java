package dev.creoii.creoapi.api.worldgen;

import net.fabricmc.api.ModInitializer;

public class CreoWorldgen implements ModInitializer {
    public static final String NAMESPACE = "creo";

    @Override
    public void onInitialize() {
        CreoPlacementModifierTypes.register();
        CreoStructurePlacementTypes.register();
    }
}
