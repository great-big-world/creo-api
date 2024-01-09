package dev.creoii.creoapi.mixin.event;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.BiConsumer;

@Mixin(Language.class)
public class LanguageMixin {
    @WrapWithCondition(method = "load(Ljava/io/InputStream;Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Ljava/util/function/BiConsumer;accept(Ljava/lang/Object;Ljava/lang/Object;)V"))
    private static boolean creo_applyTranslationLoadEvent(BiConsumer<String, String> entryConsumer, Object key, Object value) {
        return MiscEventImpl.applyLanguageTranslationLoadEvent(entryConsumer, (String) key, (String) value);
    }
}
