package dev.creoii.creoapi.api.shader;

import dev.creoii.creoapi.impl.shader.ShaderInteractionImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class ShaderInteractions {
    /**
     * Get the {@link Identifier} of the current post processor.
     * @return The identifier of the current post processor.
     */
    @Nullable
    public static Identifier getCurrentPostProcessor() {
        return ShaderInteractionImpl.getCurrentPostProcessorInternal();
    }

    /**
     * Set the current post processor.
     * @param id The {@link Identifier} of the new post processor.
     */
    public static void setCurrentPostProcessor(Identifier id) {
        ShaderInteractionImpl.setCurrentPostProcessorInternal(id);
    }

    public static void addPostProcessPass(Identifier id) {
        ShaderInteractionImpl.addPostProcessPassInternal(id);
    }

    public static void removePostProcessPass(Identifier id) {
        ShaderInteractionImpl.removePostProcessPassInternal(id);
    }

    public static void clearShaders() {
        ShaderInteractionImpl.clearPostProcessorsInternal();
    }
}
