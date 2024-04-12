package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.item.CreoItem;
import dev.creoii.creoapi.api.item.CreoItemSettings;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
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
                        .hopperTransferRate(1)
                        .requiredFuels(ItemTags.COALS)
        ));

        Registry.register(Registries.ITEM, new Identifier("test", "xray"), new XrayItem(new CreoItemSettings()));
    }

    private static class XrayItem extends Item implements CreoItem {
        public XrayItem(Settings settings) {
            super(settings);
        }

        @Override
        public void onAttackThroughBlock(MinecraftClient client, ItemStack stack, Entity target) {
            System.out.println(target.getType().getTranslationKey());
        }

        @Override
        public boolean canAttackThroughBlock(MinecraftClient client, ItemStack stack, Entity target) {
            return true;
        }
    }
}
