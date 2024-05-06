package dev.creoii.creoapi.api.item;

import net.minecraft.component.DataComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class CreoDataComponentTypes {
    public static final DataComponentType<CreoFoodComponent> FOOD = DataComponentType.<CreoFoodComponent>builder().codec(CreoFoodComponent.CODEC).packetCodec(CreoFoodComponent.PACKET_CODEC).cache().build();

    static void register() {
        Registry.register(Registries.DATA_COMPONENT_TYPE, new Identifier(CreoItemApi.NAMESPACE, "food"), FOOD);
    }
}
