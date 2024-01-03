package dev.creoii.creoapi.mixin.event;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "respawnPlayer", at = @At("RETURN"))
    private void creo_applyPlayerRespawnEvent(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        EntityEventImpl.applyPlayerRespawnEvent(player, alive);
    }
}
