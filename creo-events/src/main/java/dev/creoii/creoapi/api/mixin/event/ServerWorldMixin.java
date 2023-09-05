package dev.creoii.creoapi.api.mixin.event;

import dev.creoii.creoapi.api.event.EntitySpawnCallback;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void creo_entitySpawnCallback(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean result = EntitySpawnCallback.EVENT.invoker().spawn((ServerWorld) (Object) this, entity);

        if (!result)
            cir.cancel();
    }
}
