package dev.creoii.creoapi.impl.shader;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class ShaderUtilsImpl {
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

    public static void addPostProcessPassInternal(Identifier id) {
        /*if (checkSafe())
            ((PostProcessorInteraction) CLIENT.gameRenderer).creo_addPostProcessPass(fixId(id));*/
    }

    public static void removePostProcessPassInternal(Identifier id) {
        /*if (checkSafe())
            ((PostProcessorInteraction) CLIENT.gameRenderer).creo_removePostProcessPass(fixId(id));*/
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
