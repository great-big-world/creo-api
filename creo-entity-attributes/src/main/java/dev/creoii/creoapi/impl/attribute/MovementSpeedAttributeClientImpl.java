package dev.creoii.creoapi.impl.attribute;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class MovementSpeedAttributeClientImpl {
    public static void applySpectatorFlyingSpeed(ClientPlayerEntity player) {
        if (!player.isSpectator())
            player.getAbilities().setFlySpeed((float) player.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED));
    }
}
