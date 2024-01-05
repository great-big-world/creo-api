package dev.creoii.creoapi.api.event.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Events related to fishing.
 *
 * @see net.minecraft.item.FishingRodItem
 */
public class FishingEvents {
    /**
     * An event called when a Fishing Rod is cast.
     */
    public static final Event<Cast> CAST = EventFactory.createArrayBacked(Cast.class,
            listeners -> (world, user, hand, fishingRod, lure, luck) -> {
                for (Cast event : listeners) {
                    return event.onCast(world, user, hand, fishingRod, lure, luck);
                }

                return true;
            }
    );

    /**
     * An event called when a Fishing Rod has its {@link net.minecraft.entity.projectile.FishingBobberEntity} reeled in.
     */
    public static final Event<ReeledIn> REELED_IN = EventFactory.createArrayBacked(ReeledIn.class,
            listeners -> (world, user, hand, fishingRod) -> {
                for (ReeledIn event : listeners) {
                    return event.onReeledIn(world, user, hand, fishingRod);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface Cast {
        /**
         * Called when a Fishing Rod is cast.
         * @param world the world
         * @param user the user
         * @param hand the hand being used
         * @param fishingRod the fishing rod
         * @param lure the amount of lure
         * @param luck the amount of luck
         * @return true to cast the line or false to ignore it
         */
        boolean onCast(World world, PlayerEntity user, Hand hand, ItemStack fishingRod, int lure, int luck);
    }

    @FunctionalInterface
    public interface ReeledIn {
        /**
         * Called when a Fishing Rod has its {@link net.minecraft.entity.projectile.FishingBobberEntity} reeled in.
         * @param world the world
         * @param user the user
         * @param hand the hand being used
         * @param fishingRod the fishing rod
         * @return
         */
        boolean onReeledIn(World world, PlayerEntity user, Hand hand, ItemStack fishingRod);
    }
}
