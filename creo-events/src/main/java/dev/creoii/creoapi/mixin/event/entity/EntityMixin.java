package dev.creoii.creoapi.mixin.event.entity;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
}
