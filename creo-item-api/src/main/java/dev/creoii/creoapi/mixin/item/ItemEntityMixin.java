package dev.creoii.creoapi.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.creoii.creoapi.impl.item.ItemSettingsImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    @Shadow public abstract ItemStack getStack();

    @Shadow private int pickupDelay;

    @Shadow @Nullable private UUID owner;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private int creo$tickDespawnTime(int constant) {
        return ItemSettingsImpl.applyDespawnTime(getStack(), constant);
    }

    @ModifyConstant(method = "canMerge()Z", constant = @Constant(intValue = 6000))
    private int creo$mergeDespawnTime(int constant) {
        return ItemSettingsImpl.applyPickupDelay(getStack(), constant);
    }

    @Inject(method = "applyWaterBuoyancy", at = @At("HEAD"), cancellable = true)
    private void creo$stopWaterBuoyancy(CallbackInfo ci) {
        ItemSettingsImpl.applyBuoyancy(getStack(), ci);
    }

    @Inject(method = "applyLavaBuoyancy", at = @At("HEAD"), cancellable = true)
    private void creo$stopLavaBuoyancy(CallbackInfo ci) {
        ItemSettingsImpl.applyBuoyancy(getStack(), ci);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = -.04d))
    private double creo$applyItemGravity(double constant) {
        return ItemSettingsImpl.applyGravity(getStack(), constant);
    }

    @ModifyReturnValue(method = "getRotation", at = @At("RETURN"))
    private float creo$applyItemRotationModifier(float original) {
        return ItemSettingsImpl.applyRotationModifier(getStack(), original);
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;isOnGround()Z", ordinal = 2))
    private boolean creo$applyItemHoverAnimation(boolean original) {
        return ItemSettingsImpl.applyHoverAnimation(getStack(), original);
    }

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    private void gbw$stopCollisionPickupIfClickPickup(PlayerEntity player, CallbackInfo ci) {
        if (ItemSettingsImpl.canApplyClickPickup(getStack()))
            ci.cancel();
    }

    @Override
    public boolean canHit() {
        return ItemSettingsImpl.canApplyClickPickup(getStack());
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        return ItemSettingsImpl.applyClickPickup(player, (ItemEntity) (Object) this, pickupDelay, owner);
    }
}
