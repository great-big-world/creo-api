package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoEntityTypeTags;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public final class EntityTypeTagImpl {
    public static void applyTripwireIgnores(Entity entity, CallbackInfo ci) {
        if (entity.getType().isIn(CreoEntityTypeTags.TRIPWIRE_IGNORES))
            ci.cancel();
    }
}
