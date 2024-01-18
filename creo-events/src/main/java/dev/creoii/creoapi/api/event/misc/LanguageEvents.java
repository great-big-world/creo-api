package dev.creoii.creoapi.api.event.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import org.jetbrains.annotations.Nullable;

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
    public static final Event<LoadTranslation> LOAD_TRANSLATION = EventFactory.createArrayBacked(LoadTranslation.class,
            listeners -> (langCode, consumer, translationKey, translated) -> {
                for (LoadTranslation event : listeners) {
                    return event.onLoadTranslation(langCode, consumer, translationKey, translated);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface LoadTranslation {
        /**
         * Called when each translation key for a locale is translated and stored.
         * @param langCode the language code
         * @param consumer adds the translated text to the translations list
         * @param translationKey the translation key
         * @param translated the translated text
         * @return true to translate the translation or false to ignore the default translation
         */
        boolean onLoadTranslation(@Nullable String langCode, BiConsumer<String, String> consumer, String translationKey, String translated);
    }
}
