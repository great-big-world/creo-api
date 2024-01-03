package dev.creoii.creoapi.mixin.event.entity;

import com.mojang.datafixers.util.Either;
import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "trySleep", at = @At("RETURN"), cancellable = true)
    private void creo_applySleepSleepEvent(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {
        MiscEventImpl.applySleepSleepEvent((ServerPlayerEntity) (Object) this, pos, cir.getReturnValue(), cir);
    }
}
