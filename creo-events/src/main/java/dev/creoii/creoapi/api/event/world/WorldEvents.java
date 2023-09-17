package dev.creoii.creoapi.api.event.world;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

/**
 * Events related to {@link World}.
 */
public final class WorldEvents {
    /**
     * An event called when an explosion occurs, before it affects the world.
     *
     * @see World#createExplosion(Entity, DamageSource, ExplosionBehavior, double, double, double, float, boolean, World.ExplosionSourceType, boolean)
     */
    public static final Event<Explode> EXPLODE = EventFactory.createArrayBacked(Explode.class,
            listeners -> (world, explosion, x, y, z, behavior, destructionType, explosionSourceType, power, createFire, particles) -> {
                for (Explode event : listeners) {
                    return event.onExplode(world, explosion, x, y, z, behavior, destructionType, explosionSourceType, power, createFire, particles);
                }

                return true;
            }
    );

    public interface Explode {
        /**
         * Called when an explosion occurs, before it affects the world.
         *
         * @param world the world
         * @param explosion the explosion
         * @param x the x position
         * @param y the y position
         * @param z the z position
         * @param behavior the explosion behavior
         * @param destructionType the explosion destruction type
         * @param explosionSourceType the explosion source type
         * @param power the explosion strength
         * @param createFire whether the explosion should create fire
         * @param particles whether the explosion should create particles
         *
         * @return true for the explosion to affect the world, or false otherwise.
         *
         * @see World#createExplosion(Entity, DamageSource, ExplosionBehavior, double, double, double, float, boolean, World.ExplosionSourceType, boolean)
         */
        boolean onExplode(World world, Explosion explosion, double x, double y, double z, ExplosionBehavior behavior, Explosion.DestructionType destructionType, World.ExplosionSourceType explosionSourceType, float power, boolean createFire, boolean particles);
    }
}
