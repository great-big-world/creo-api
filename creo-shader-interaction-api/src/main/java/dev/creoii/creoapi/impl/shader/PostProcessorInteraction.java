package dev.creoii.creoapi.impl.shader;

import net.minecraft.util.Identifier;

public interface PostProcessorInteraction {
    Identifier creo_getCurrentPostProcessor();

    void creo_setCurrentPostProcessor(Identifier id);

    boolean creo_addPostProcessPass(Identifier id);

    boolean creo_removePostProcessPass(Identifier id);

    void creo_clearPostProcessors();
}
