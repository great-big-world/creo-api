package dev.creoii.creoapi.mixin.block;

import dev.creoii.creoapi.impl.block.BlockImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V"))
    private void creo$lookAtBlock(CallbackInfo ci) {
        BlockImpl.applyLookAtBlock((Entity) (Object) this);
    }

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 2))
    private void creo$collideAdjacentBlock(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        if ((Entity) (Object) this instanceof ServerPlayerEntity serverPlayer)
            BlockImpl.applyCollideAdjacent(serverPlayer);
    }
}