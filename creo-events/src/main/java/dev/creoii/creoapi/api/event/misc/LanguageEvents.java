package dev.creoii.creoapi.api.event.misc;

import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.Map;
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
            listeners -> (consumer, translation, translated) -> {
                for (TranslationLoad event : listeners) {
                    return event.onTranslationLoad(consumer, translation, translated);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface TranslationLoad {
        /**
         * Called when each translation key for a locale is translated and stored.
         * @param consumer adds the translated text to the translations list
         * @param translation the mapping of translation key to an {@link JsonElement} which stores the translated text
         * @param translated the parsed translated text
         * @return true to translate and store the translation key or false to ignore the default translation.
         */
        boolean onTranslationLoad(BiConsumer<String, String> consumer, Map.Entry<String, JsonElement> translation, String translated);
    }
}
