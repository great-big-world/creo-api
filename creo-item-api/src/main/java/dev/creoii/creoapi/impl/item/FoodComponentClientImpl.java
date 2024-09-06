package dev.creoii.creoapi.impl.item;

import dev.creoii.creoapi.api.item.CreoDataComponentTypes;
import dev.creoii.creoapi.api.item.CreoFoodComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;

@Environment(EnvType.CLIENT)
public final class FoodComponentClientImpl {
    public static boolean applyFoodSprintEdibles(ClientPlayerEntity player) {
        CreoFoodComponent foodComponent = player.getActiveItem().get(CreoDataComponentTypes.FOOD);
        if (foodComponent != null) {
            return player.isUsingItem() ? !foodComponent.canSprintEat() : player.isUsingItem();
        }
        return player.isUsingItem();
    }
}
