package dev.creoii.creoapi.api.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;

/**
 * Events related to {@link ProjectileEntity}.
 */
public final class ProjectileEntityEvents {
    /**
     * An event called when a {@link ProjectileEntity} is created.
     */
    public static final Event<Fire> FIRE = EventFactory.createArrayBacked(Fire.class,
            listeners -> projectile -> {
                for (Fire event : listeners) {
                    event.onFire(projectile);
                }
            }
    );

    /**
     * An event called when a {@link ProjectileEntity} impacts either a block or an entity.
     *
     * @see ProjectileEntity#onCollision(HitResult)
     */
    public static final Event<Impact> IMPACT = EventFactory.createArrayBacked(Impact.class,
            listeners -> (projectile, hitResult) -> {
                for (Impact event : listeners) {
                    return event.onImpact(projectile, hitResult);
                }

                return true;
            }
    );

    public interface Fire {
        /**
         * Called when a {@link ProjectileEntity} is created.
         *
         * @param projectile the projectile
         */
        void onFire(ProjectileEntity projectile);
    }

    public interface Impact {
        /**
         * Called when a {@link ProjectileEntity} impacts either a block or an entity.
         *
         * @param projectile the projectile
         * @param hitResult the hit result information
         *
         * @return true to collide, or false to stop the collision from occuring
         *
         * @see ProjectileEntity#onCollision(HitResult)
         */
        boolean onImpact(ProjectileEntity projectile, HitResult hitResult);
    }
}
