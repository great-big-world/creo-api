package dev.creoii.creoapi.mixin.event;

import com.google.common.collect.ImmutableMap;
import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @SuppressWarnings("unchecked")
    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;"))
    private <K, V> ImmutableMap.Builder<K, V> gbw$(ImmutableMap.Builder<K, V> instance, K key, V value) {
        return (ImmutableMap.Builder<K, V>) MiscEventImpl.applyRecipeLoadEvent((ImmutableMap.Builder<Identifier, RecipeEntry<?>>) instance, (Identifier) key, (RecipeEntry<?>) value);
    }
}
