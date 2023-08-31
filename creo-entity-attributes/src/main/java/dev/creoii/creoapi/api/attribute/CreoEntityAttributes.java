package dev.creoii.creoapi.api.attribute;

import net.fabricmc.api.ModInitializer;

public class CreoEntityAttributes implements ModInitializer {
    public static final String NAMESPACE = "creo";

    @Override
    public void onInitialize() {
        CEntityAttributes.register();
    }
}
