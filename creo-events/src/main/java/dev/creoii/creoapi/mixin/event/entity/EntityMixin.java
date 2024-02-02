package dev.creoii.creoapi.mixin.event.entity;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
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

    @Inject(method = "tick", at = @At("TAIL"))
    private void creo_enterStructureEvent(CallbackInfo ci) {
        EntityEventImpl.applyWithinStructureEvent(world, (Entity) (Object) this, getBlockPos(), getChunkPos());
    }

    @Inject(method = "onStruckByLightning", at = @At("HEAD"))
    private void creo_struckByLightningEvent(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        EntityEventImpl.applyEntityStruckByLightningEvent(world, (Entity) (Object) this, lightning);
    }

    @Inject(method = "moveToWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;create(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", shift = At.Shift.BY, by = 2, ordinal = 0))
    private void creo_changeDimensionEvent(ServerWorld destination, CallbackInfoReturnable<Entity> cir, @Local TeleportTarget teleportTarget, @Local Entity entity) {
        EntityEventImpl.applyEntityChangeDimensionEvent(world, destination, (Entity) (Object) this, entity, teleportTarget, cir);
    }
}
