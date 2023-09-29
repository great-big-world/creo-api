package dev.creoii.creoapi.impl.shader;

import dev.creoii.creoapi.impl.shader.util.PostProcessorInteraction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public final class ShaderInteractionImpl {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    @Nullable
    public static Identifier getCurrentPostProcessorInternal() {
        if (checkSafe())
            return ((PostProcessorInteraction) CLIENT.gameRenderer).creo_getCurrentPostProcessor();
        return null;
    }

    public static void setCurrentPostProcessorInternal(Identifier id) {
        if (checkSafe())
            ((PostProcessorInteraction) CLIENT.gameRenderer).creo_setCurrentPostProcessor(fixId(id));
    }

    public static void clearPostProcessorsInternal() {
        if (checkSafe())
            ((PostProcessorInteraction) CLIENT.gameRenderer).creo_clearPostProcessors();
    }

    private static boolean checkSafe() {
        return CLIENT != null && CLIENT.gameRenderer != null;
    }

    private static Identifier fixId(Identifier id) {
        return id.withPrefixedPath("shaders/").withSuffixedPath(".json");
    }
}
