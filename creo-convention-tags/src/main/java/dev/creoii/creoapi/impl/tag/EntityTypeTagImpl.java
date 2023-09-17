package dev.creoii.creoapi.impl.tag;

import dev.creoii.creoapi.api.tag.CreoEntityTypeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class EntityTypeTagImpl {
    public static void applyTripwireIgnores(Entity entity, CallbackInfo ci) {
        if (entity.getType().isIn(CreoEntityTypeTags.TRIPWIRE_IGNORES))
            ci.cancel();
    }

    public static void applyProjectilesIgnore(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType().isIn(CreoEntityTypeTags.PROJECTILES_IGNORE))
            cir.setReturnValue(false);
    }

    public static void applyAvoidsTraps(EntityType<?> entityType, CallbackInfoReturnable<Boolean> cir) {
        if (entityType.isIn(CreoEntityTypeTags.AVOIDS_TRAPS)) {
            cir.setReturnValue(true);
        }
    }

    public static void applyCollidable(EntityType<?> entityType, CallbackInfoReturnable<Boolean> cir) {
        if (entityType.isIn(CreoEntityTypeTags.COLLIDABLE)) {
            cir.setReturnValue(true);
        }
    }

    public static void applyZoglinIgnores(EntityType<?> entityType, CallbackInfoReturnable<Boolean> cir) {
        if (entityType.isIn(CreoEntityTypeTags.ZOGLIN_IGNORES))
            cir.setReturnValue(false);
    }

    public static void applyFishingRodCannotHook(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!entity.getType().isIn(CreoEntityTypeTags.FISHING_ROD_CANNOT_HOOK));
    }

    public static void applyScaresCreepers(CreeperEntity creeperEntity, GoalSelector goalSelector, CallbackInfo ci) {
        goalSelector.add(3, new FleeEntityGoal<>(creeperEntity, LivingEntity.class, 6f, 1d, 1.2d, livingEntity -> {
            return livingEntity.getType().isIn(CreoEntityTypeTags.SCARES_CREEPERS);
        }));
    }

    public static void applyWardenIgnores(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity != null && entity.getType().isIn(CreoEntityTypeTags.WARDEN_IGNORES))
            cir.setReturnValue(false);
    }
}
