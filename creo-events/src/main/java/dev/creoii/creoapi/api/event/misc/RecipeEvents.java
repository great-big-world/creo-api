package dev.creoii.creoapi.api.event.misc;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;

/**
 * Events related to recipes.
 */
public final class RecipeEvents {
    /**
     * An event called when a recipe is deserialized, before being loaded into the game.
     */
    public static final Event<LoadRecipe> LOAD_RECIPE = EventFactory.createArrayBacked(LoadRecipe.class,
            listeners -> (builder, recipeEntry) -> {
                for (LoadRecipe event : listeners) {
                    return event.onLoadRecipe(builder, recipeEntry);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface LoadRecipe {
        /**
         * Called when a recipe is deserialized, before being loaded into the game.
         * @param builder the builder
         * @param recipeEntry the {@link RecipeEntry} being loaded
         * @return true to load the recipe, or false to ignore it.
         */
        boolean onLoadRecipe(ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder, RecipeEntry<?> recipeEntry);
    }
}
