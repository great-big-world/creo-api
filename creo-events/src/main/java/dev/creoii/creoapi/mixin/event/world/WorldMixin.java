package dev.creoii.creoapi.mixin.event.world;

import dev.creoii.creoapi.impl.event.WorldEventImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;Z)Lnet/minecraft/world/explosion/Explosion;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/explosion/Explosion;affectWorld(Z)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyWorldExplodeEvent(Entity entity, DamageSource damageSource, ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, boolean particles, CallbackInfoReturnable<Explosion> cir, Explosion.DestructionType destructionType, Explosion explosion) {
        WorldEventImpl.applyWorldExplodeEvent((World) (Object) this, explosion, x, y, z, behavior, destructionType, explosionSourceType, power, createFire, particles, cir);
    }
}
