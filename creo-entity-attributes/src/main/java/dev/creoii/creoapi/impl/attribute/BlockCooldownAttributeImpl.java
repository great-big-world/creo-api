package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BlockCooldownAttributeImpl {
    public static void addAttributes(DefaultAttributeContainer.Builder builder) {
        builder.add(CreoEntityAttributes.PLACE_COOLDOWN).add(CreoEntityAttributes.BREAK_COOLDOWN);
    }
}
