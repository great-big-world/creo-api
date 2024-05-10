package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import dev.creoii.creoapi.impl.event.ItemEventImpl;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
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
    private void creo$mobInitializeCallback(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        EntityEventImpl.applyMobInitializeEvent(world, (MobEntity) (Object) this, difficulty, spawnReason, entityData, cir);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;initGoals()V", shift = At.Shift.BY, by = 2))
    private void creo$mobInitGoalsPostCallback(EntityType<?> entityType, World world, CallbackInfo ci) {
        EntityEventImpl.applyMobPostInitGoalsEvent(world, (MobEntity) (Object) this, goalSelector, targetSelector);
    }

    @Inject(method = "loot", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;sendPickup(Lnet/minecraft/entity/Entity;I)V", shift = At.Shift.AFTER))
    private void creo$applyItemPickUpEventMob(ItemEntity item, CallbackInfo ci) {
        ItemEventImpl.applyItemPickUpEvent(item, (MobEntity) (Object) this);
    }
}
