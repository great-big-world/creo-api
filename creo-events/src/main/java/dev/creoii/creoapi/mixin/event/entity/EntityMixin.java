package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow private World world;
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract ChunkPos getChunkPos();
    @Shadow @Final protected DataTracker dataTracker;

    @Inject(method = "tick", at = @At("TAIL"))
    private void creo_enterStructureEvent(CallbackInfo ci) {
        EntityEventImpl.applyWithinStructureEvent(world, (Entity) (Object) this, getBlockPos(), getChunkPos());
    }

    @Inject(method = "writeNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", shift = At.Shift.AFTER))
    private void creo_writeNbtEvent(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        EntityEventImpl.applyWriteNbtEvent((Entity) (Object) this, nbt);
    }

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;initDataTracker()V", shift = At.Shift.AFTER))
    private void creo_writeNbtEvent(EntityType<?> type, World world, CallbackInfo ci) {
        EntityEventImpl.applyDataTrackEvent((Entity) (Object) this, dataTracker);
    }

    @Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
    private void creo_struckByLightningEvent(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        EntityEventImpl.applyEntityStruckByLightningEvent(world, (Entity) (Object) this, lightning);
    }

    @Inject(method = "moveToWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;create(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", shift = At.Shift.BY, by = 2, ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_changeDimensionEvent(ServerWorld destination, CallbackInfoReturnable<Entity> cir, TeleportTarget teleportTarget, Entity entity) {
        EntityEventImpl.applyEntityChangeDimensionEvent(world, destination, (Entity) (Object) this, entity, teleportTarget, cir);
    }
}
