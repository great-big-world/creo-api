package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.GravityAttributeImpl;
import dev.creoii.creoapi.impl.attribute.JumpStrengthAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MaxAirAttributeImpl;
import dev.creoii.creoapi.impl.attribute.MovementSpeedAttributeImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Vec3d;
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
    private static void creo_createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        GravityAttributeImpl.addAttribute(cir);
        JumpStrengthAttributeImpl.addAttribute(cir);
        MaxAirAttributeImpl.addAttribute(cir);
        MovementSpeedAttributeImpl.addLivingAttributes(cir);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = .08d))
    private double creo_modifyGravityValue(double constant) {
        return GravityAttributeImpl.modifyGravity((LivingEntity) (Object) this, constant);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = .01d))
    private double creo_modifySlowFallingGravityValue(double constant) {
        return GravityAttributeImpl.modifySlowFallingGravity((LivingEntity) (Object) this, constant);
    }

    @Inject(method = "knockDownwards", at = @At("HEAD"), cancellable = true)
    private void creo_applyKnockbackSwimSpeed(CallbackInfo ci) {
        GravityAttributeImpl.knockDownwards((LivingEntity) (Object) this, ci);
    }

    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;", shift = At.Shift.BEFORE))
    private void creo_removeSlowFallingModifier(Vec3d movementInput, CallbackInfo ci) {
        GravityAttributeImpl.removeSlowFallingModifier((LivingEntity) (Object) this);
    }

    @ModifyVariable(method = "computeFallDamage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float creo_handleFallDamageGravity(float value) {
        return GravityAttributeImpl.adjustFallDamage((LivingEntity) (Object) this, value);
    }

    @Inject(method = "swimUpward", at = @At("HEAD"), cancellable = true)
    private void creo_applyUpwardSwimSpeed(TagKey<Fluid> fluid, CallbackInfo ci) {
        MovementSpeedAttributeImpl.applyUpwardSwimSpeed((LivingEntity) (Object) this, ci);
    }

    @ModifyConstant(method = "travel", constant = @Constant(floatValue = .02f))
    private float creo_applySwimSpeed(float constant) {
        return MovementSpeedAttributeImpl.applySwimSpeed((LivingEntity) (Object) this);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = .2d, ordinal = 0))
    private double creo_applyClimbingSpeed(double constant) {
        return MovementSpeedAttributeImpl.applyClimbingSpeed((LivingEntity) (Object) this);
    }

    @ModifyConstant(method = "applyMovementInput", constant = @Constant(doubleValue = .2d))
    private double creo_applyClimbingInputSpeed(double constant) {
        return MovementSpeedAttributeImpl.applyClimbingSpeed((LivingEntity) (Object) this);
    }

    @ModifyConstant(method = "getJumpVelocity", constant = @Constant(floatValue = .42f))
    private float creo_applyJumpStrength(float constant) {
        return JumpStrengthAttributeImpl.applyJumpStrength((LivingEntity) (Object) this);
    }
}
