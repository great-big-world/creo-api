package dev.creoii.creoapi.api.item;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class CreoComponentTypes {
    public static final ComponentType<CreoFoodComponent> FOOD = ComponentType.<CreoFoodComponent>builder().codec(CreoFoodComponent.CODEC).packetCodec(CreoFoodComponent.PACKET_CODEC).cache().build();

    static void register() {
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CreoItemApi.NAMESPACE, "food"), FOOD);
    }
}
