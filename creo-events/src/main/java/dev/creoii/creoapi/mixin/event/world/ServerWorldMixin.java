package dev.creoii.creoapi.mixin.event.world;

import dev.creoii.creoapi.impl.event.EntityEventImpl;
import dev.creoii.creoapi.impl.event.WorldEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void creo_entitySpawnCallback(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityEventImpl.applyEntitySpawnEvent((ServerWorld) (Object) this, entity, cir);
    }

    @Inject(method = "createExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/explosion/Explosion;shouldDestroy()Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_cancelWorldExplodeEvent(Entity entity, DamageSource damageSource, ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, ParticleEffect particle, ParticleEffect emitterParticle, SoundEvent soundEvent, CallbackInfoReturnable<Explosion> cir, Explosion explosion) {
        WorldEventImpl.cancelWorldExplodeEvent(explosion, cir);
    }

    @Inject(method = "setWeather", at = @At("HEAD"), cancellable = true)
    private void creo_applyWeatherEventSet(int clearDuration, int rainDuration, boolean raining, boolean thundering, CallbackInfo ci) {
        WorldEventImpl.applyWorldWeatherEvent((ServerWorld) (Object) this, clearDuration, rainDuration, rainDuration, raining, thundering, ci);
    }

    @Inject(method = "resetWeather", at = @At("HEAD"), cancellable = true)
    private void creo_applyWeatherEventReSet(CallbackInfo ci) {
        WorldEventImpl.applyWorldWeatherEvent((ServerWorld) (Object) this, 0, 0, 0, false, false, ci);
    }
}
