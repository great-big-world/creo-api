package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.MaxAirAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void creo$createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        MaxAirAttributeImpl.addAttribute(cir.getReturnValue());
        MovementSpeedAttributeImpl.addLivingAttributes(cir.getReturnValue());
    }

    @Inject(method = "swimUpward", at = @At("HEAD"), cancellable = true)
    private void creo$applyUpwardSwimSpeed(TagKey<Fluid> fluid, CallbackInfo ci) {
        MovementSpeedAttributeImpl.applyUpwardSwimSpeed((LivingEntity) (Object) this, ci);
    }

    @ModifyConstant(method = "travel", constant = @Constant(floatValue = .02f))
    private float creo$applySwimSpeed(float constant) {
        return MovementSpeedAttributeImpl.applySwimSpeed((LivingEntity) (Object) this);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = .2d, ordinal = 0))
    private double creo$applyClimbingSpeed(double constant) {
        return MovementSpeedAttributeImpl.applyClimbingSpeed((LivingEntity) (Object) this);
    }

    @ModifyConstant(method = "applyMovementInput", constant = @Constant(doubleValue = .2d))
    private double creo$applyClimbingInputSpeed(double constant) {
        return MovementSpeedAttributeImpl.applyClimbingSpeed((LivingEntity) (Object) this);
    }
}
