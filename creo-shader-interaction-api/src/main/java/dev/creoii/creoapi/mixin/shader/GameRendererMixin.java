package dev.creoii.creoapi.mixin.shader;

import dev.creoii.creoapi.impl.shader.GameRendererImpl;
import dev.creoii.creoapi.impl.shader.PostProcessorInteraction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements PostProcessorInteraction {
    @Shadow @Nullable PostEffectProcessor postProcessor;
    @Shadow private boolean postProcessorEnabled;
    @Shadow @Final MinecraftClient client;

    @Nullable
    @Override
    public Identifier creo_getCurrentPostProcessor() {
        return GameRendererImpl.getCurrentPostProcessor(postProcessor);
    }

    @Override
    public void creo_setCurrentPostProcessor(Identifier id) {
        GameRendererImpl.setCurrentPostProcessor((GameRenderer) (Object) this, id);
    }

    @Override
    public boolean creo_addPostProcessPass(Identifier id) {
        return GameRendererImpl.addPostProcessPass(client, postProcessor, id, postProcessorEnabled);
    }

    @Override
    public boolean creo_removePostProcessPass(Identifier id) {
        return GameRendererImpl.removePostProcessPass(postProcessor, id);
    }

    @Override
    public void creo_clearPostProcessors() {
        GameRendererImpl.clearPostProcessors((GameRenderer) (Object) this);
    }
}
