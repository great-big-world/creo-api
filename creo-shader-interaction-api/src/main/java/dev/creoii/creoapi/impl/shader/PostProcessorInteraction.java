package dev.creoii.creoapi.impl.shader;

import net.minecraft.util.Identifier;

public interface PostProcessorInteraction {
    Identifier creo_getCurrentPostProcessor();

    void creo_setCurrentPostProcessor(Identifier id);

    void creo_clearPostProcessors();
}
