package dev.creoii.creoapi.impl.shader;

import dev.creoii.creoapi.mixin.shader.PostEffectProcessorAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
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
                if (prevPass.getName().equals(newPass.getName()))
                    return false;
                newPass.setProjectionMatrix(accessor.getProjectionMatrix());
                accessor.setPasses(copyWith(accessor.getPasses(), newPass));
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
        PostEffectProcessorAccessor accessor = (PostEffectProcessorAccessor) postProcessor;
        accessor.setPasses(copyWithout(accessor.getPasses(), id));
        return success.booleanValue();
    }

    public static void clearPostProcessors(GameRenderer gameRenderer) {
        gameRenderer.disablePostProcessor();
    }

    private static List<PostEffectPass> copyWithout(List<PostEffectPass> list, Identifier id) {
        List<PostEffectPass> newList = new ArrayList<>();
        for (PostEffectPass pass : list) {
            if (!id.getPath().contains(pass.getName())) {
                newList.add(pass);
            }
        }
        return newList;
    }

    private static List<PostEffectPass> copyWith(List<PostEffectPass> list, PostEffectPass newPass) {
        List<PostEffectPass> newList = new ArrayList<>(list);
        newList.add(newPass);
        return newList;
    }
}
