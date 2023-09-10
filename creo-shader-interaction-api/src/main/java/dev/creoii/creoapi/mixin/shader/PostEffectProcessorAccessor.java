package dev.creoii.creoapi.mixin.shader;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectProcessor;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(PostEffectProcessor.class)
public interface PostEffectProcessorAccessor {
    @Accessor("passes")
    List<PostEffectPass> getPasses();

    @Accessor("passes")
    void setPasses(List<PostEffectPass> passes);

    @Accessor("projectionMatrix")
    Matrix4f getProjectionMatrix();
}
