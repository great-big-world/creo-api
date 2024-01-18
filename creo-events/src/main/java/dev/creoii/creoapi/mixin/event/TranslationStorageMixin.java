package dev.creoii.creoapi.mixin.event;

import dev.creoii.creoapi.impl.event.util.LocaleAwareLanguage;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.Resource;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Inject(method = "load(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Language;load(Ljava/io/InputStream;Ljava/util/function/BiConsumer;)V"))
    private static void creo_applyLocaleAwareLanguage(String langCode, List<Resource> resourceRefs, Map<String, String> translations, CallbackInfo ci) {
        if (Language.getInstance() != null)
            ((LocaleAwareLanguage) Language.getInstance()).creo_setLangCode(langCode);
    }
}
