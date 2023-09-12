package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.item.CreoItemSettings;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemTest implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("test", "test_item"), new Item(
                new CreoItemSettings()
                        .pickupDelay(25)
                        .despawnTime(3000)
                        .notBuoyant()
                        .gravity(0d)
        ));
    }
}
