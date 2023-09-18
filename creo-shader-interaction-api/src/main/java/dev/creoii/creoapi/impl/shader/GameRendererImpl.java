package dev.creoii.creoapi.impl.shader;

import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public final class GameRendererImpl {
    public static Identifier getCurrentPostProcessor(@Nullable PostEffectProcessor postProcessor) {
        if (postProcessor == null)
            return null;
        return Identifier.tryParse(postProcessor.getName());
    }

    public static void setCurrentPostProcessor(GameRenderer gameRenderer, Identifier id) {
        gameRenderer.loadPostProcessor(id);
    }

    public static void clearPostProcessors(GameRenderer gameRenderer) {
        gameRenderer.disablePostProcessor();
    }
}
