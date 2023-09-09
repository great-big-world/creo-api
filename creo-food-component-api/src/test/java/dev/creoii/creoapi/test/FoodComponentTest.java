package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.food.CreoFoodComponent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FoodComponentTest implements ModInitializer {
    @Override
    public void onInitialize() {
        Item slowFood = new Item(new FabricItemSettings().food(new CreoFoodComponent.Builder().eatSpeed(64).alwaysEdible().build()));
        Item fastFood = new Item(new FabricItemSettings().food(new CreoFoodComponent.Builder().eatSpeed(1).alwaysEdible().build()));
        Item durabilityFood = new Item(new FabricItemSettings().food(new CreoFoodComponent.Builder().eatDurability(4).alwaysEdible().build()));

        Registry.register(Registries.ITEM, new Identifier("test", "slow_food"), slowFood);
        Registry.register(Registries.ITEM, new Identifier("test", "fast_food"), fastFood);
        Registry.register(Registries.ITEM, new Identifier("test", "durability_food"), durabilityFood);
    }
}
