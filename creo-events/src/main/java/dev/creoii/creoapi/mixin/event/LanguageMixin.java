package dev.creoii.creoapi.mixin.event;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

@Mixin(Language.class)
public class LanguageMixin {
    @Shadow @Final private static Pattern TOKEN_PATTERN;

    @Inject(method = "load(Ljava/io/InputStream;Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Ljava/util/function/BiConsumer;accept(Ljava/lang/Object;Ljava/lang/Object;)V"), cancellable = true)
    private static void creo_applyTranslationLoadEvent(InputStream inputStream, BiConsumer<String, String> entryConsumer, CallbackInfo ci, @Local Iterator<Map.Entry<String, JsonElement>> var3, @Local Map.Entry<String, JsonElement> entry, @Local String string) {
        MiscEventImpl.applyLanguageTranslationLoadEvent(var3, TOKEN_PATTERN, entryConsumer, entry, string, ci);
    }
}
