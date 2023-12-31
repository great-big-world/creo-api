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

    /**
     * Clears all post processors from the screen.
     */
    public static void clearPostProcessors() {
        ShaderInteractionImpl.clearPostProcessorsInternal();
    }
}
