package dev.creoii.creoapi.impl.shader;

import dev.creoii.creoapi.mixin.shader.PostEffectProcessorAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class GameRendererImpl {
    public static Identifier getCurrentPostProcessor(@Nullable PostEffectProcessor postProcessor) {
        if (postProcessor == null)
            return null;
        return Identifier.tryParse(postProcessor.getName());
    }

    public static void setCurrentPostProcessor(GameRenderer gameRenderer, Identifier id) {
        gameRenderer.loadPostProcessor(id);
    }

    public static boolean addPostProcessPass(MinecraftClient client, PostEffectProcessor postProcessor, Identifier id, boolean postProcessorEnabled) {
        PostEffectProcessorAccessor accessor = ((PostEffectProcessorAccessor) postProcessor);
        if (postProcessor == null || !postProcessorEnabled || accessor.getPasses().isEmpty()) {
            setCurrentPostProcessor(client.gameRenderer, id);
            return true;
        } else {
            try {
                PostEffectPass prevPass = accessor.getPasses().get(accessor.getPasses().size() - 1);
                PostEffectPass newPass = new PostEffectPass(client.getResourceManager(), id.getPath().replace("shaders/post/", "").replace(".json", ""), prevPass.input, prevPass.output);
                System.out.println(prevPass.getName() + " " + newPass.getName());
                if (prevPass.getName().equals(newPass.getName()))
                    return false;
                newPass.setProjectionMatrix(accessor.getProjectionMatrix());
                accessor.getPasses().add(accessor.getPasses().size(), newPass);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean removePostProcessPass(PostEffectProcessor postProcessor, Identifier id) {
        MutableBoolean success = new MutableBoolean(true);
        if (postProcessor == null)
            return false;
        PostEffectProcessorAccessor accessor = ((PostEffectProcessorAccessor) postProcessor);
        List<PostEffectPass> newPasses = new ArrayList<>();
        for (PostEffectPass postEffectPass : accessor.getPasses()) {
            if (!id.getPath().contains(postEffectPass.getName()))
                newPasses.add(postEffectPass);
        }
        accessor.setPasses(newPasses);
        return success.booleanValue();
    }

    public static void clearPostProcessors(GameRenderer gameRenderer) {
        gameRenderer.disablePostProcessor();
    }
}
