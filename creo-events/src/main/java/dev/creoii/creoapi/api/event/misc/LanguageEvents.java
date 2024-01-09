package dev.creoii.creoapi.api.event.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.function.BiConsumer;

/**
 * Events related to languages and translation.
 *
 * @see net.minecraft.util.Language
 * @see net.minecraft.client.resource.language.TranslationStorage
 */
public final class LanguageEvents {
    /**
     * An event called when each translation key for a locale is translated and stored.
     */
    public static final Event<TranslationLoad> TRANSLATION_LOAD = EventFactory.createArrayBacked(TranslationLoad.class,
            listeners -> (consumer, translationKey, translated) -> {
                for (TranslationLoad event : listeners) {
                    return event.onTranslationLoad(consumer, translationKey, translated);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface TranslationLoad {
        /**
         * Called when each translation key for a locale is translated and stored.
         * @param consumer adds the translated text to the translations list
         * @param translationKey the translation key
         * @param translated the translated text
         * @return true to translate the translation or false to ignore the default translation
         */
        boolean onTranslationLoad(BiConsumer<String, String> consumer, String translationKey, String translated);
    }
}
