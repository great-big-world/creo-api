package dev.creoii.creoapi.mixin.attribute;

import dev.creoii.creoapi.impl.attribute.GravityAttributeImpl;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, GravityAttributeImpl.Gravity {
    @Unique private EntityAttributeInstance gravity;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void creo_createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        GravityAttributeImpl.addAttribute(cir);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.08))
    private double creo_modifyGravityValue(double constant) {
        return GravityAttributeImpl.modifyGravity(this, constant);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.01))
    private double creo_modifySlowFallingGravityValue(double constant) {
        return GravityAttributeImpl.modifySlowFallingGravity(this, constant);
    }

    @Inject(method = "knockDownwards", at = @At("HEAD"), cancellable = true)
    private void creo_applyKnockbackSwimSpeed(CallbackInfo ci) {
        GravityAttributeImpl.knockDownwards(this, ci);
    }

    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;", shift = At.Shift.BEFORE))
    private void creo_removeSlowFallingModifier(Vec3d movementInput, CallbackInfo ci) {
        GravityAttributeImpl.removeSlowFallingModifier(this);
    }

    @ModifyVariable(method = "computeFallDamage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float creo_handleFallDamageGravity(float value) {
        return GravityAttributeImpl.adjustFallDamage(this, value);
    }

    @Override
    public EntityAttributeInstance creo_getGravity() {
        return gravity;
    }

    @Override
    public void creo_setGravity(EntityAttributeInstance gravity) {
        this.gravity = gravity;
    }
}
