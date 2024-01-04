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
    public static final Event<Cast> CAST = EventFactory.createArrayBacked(Cast.class,
            listeners -> (world, user, hand, fishingRod, lure, luck) -> {
                for (Cast event : listeners) {
                    return event.onCast(world, user, hand, fishingRod, lure, luck);
                }

                return true;
            }
    );

    public static final Event<Catch> CATCH = EventFactory.createArrayBacked(Catch.class,
            listeners -> (world, user, hand, fishingRod) -> {
                for (Catch event : listeners) {
                    return event.onCatch(world, user, hand, fishingRod);
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface Cast {
        boolean onCast(World world, PlayerEntity user, Hand hand, ItemStack fishingRod, int lure, int luck);
    }

    @FunctionalInterface
    public interface Catch {
        boolean onCatch(World world, PlayerEntity user, Hand hand, ItemStack fishingRod);
    }
}
