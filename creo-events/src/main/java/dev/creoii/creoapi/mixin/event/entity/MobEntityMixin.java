package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Shadow @Final protected GoalSelector goalSelector;

    @Shadow @Final protected GoalSelector targetSelector;

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private void creo_mobInitializeCallback(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        EntityEventImpl.applyMobInitializeEvent(world, (MobEntity) (Object) this, difficulty, spawnReason, entityData, entityNbt, cir);
    }

    @Inject(method = "initGoals", at = @At("HEAD"), cancellable = true)
    private void creo_mobInitGoalsPreCallback(CallbackInfo ci) {
        EntityEventImpl.applyMobPreInitGoalsEvent(((MobEntity) (Object) this).getWorld(), (MobEntity) (Object) this, goalSelector, targetSelector);
    }

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;initGoals()V", shift = At.Shift.AFTER), cancellable = true)
    private void creo_mobInitGoalsPostCallback(EntityType<?> entityType, World world, CallbackInfo ci) {
        EntityEventImpl.applyMobPostInitGoalsEvent(world, (MobEntity) (Object) this, goalSelector, targetSelector);
    }
}
