package dev.creoii.creoapi.api.tag;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.fabricmc.api.ModInitializer;

public class CreoConventionTags implements ModInitializer {
    public static final String COMMON_NAMESPACE = "c";

    @Override
    public void onInitialize() {
        ItemTagImpl.applyArmorRepairIngredients();
        ItemTagImpl.applyToolRepairIngredients();
    }
}
