package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.world.WorldEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class WorldEventImpl {
    public static void applyWorldExplodeEvent(World world, Explosion explosion, double x, double y, double z, ExplosionBehavior behavior, Explosion.DestructionType destructionType, World.ExplosionSourceType explosionSourceType, float power, boolean createFire, boolean particles, CallbackInfoReturnable<Explosion> cir) {
        boolean result = WorldEvents.EXPLODE.invoker().shouldExplode(world, explosion, x, y, z, behavior, destructionType, explosionSourceType, power, createFire, particles);

        if (!result) {
            cir.setReturnValue(null);
        }
    }

    public static void cancelWorldExplodeEvent(Explosion explosion, CallbackInfoReturnable<Explosion> cir) {
        if (explosion == null)
            cir.setReturnValue(null);
    }
}
