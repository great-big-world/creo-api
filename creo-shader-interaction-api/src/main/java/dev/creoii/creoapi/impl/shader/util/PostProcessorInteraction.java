package dev.creoii.creoapi.impl.shader.util;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface PostProcessorInteraction {
    Identifier creo$getCurrentPostProcessor();

    void creo$setCurrentPostProcessor(Identifier id);

    void creo$clearPostProcessors();
}
