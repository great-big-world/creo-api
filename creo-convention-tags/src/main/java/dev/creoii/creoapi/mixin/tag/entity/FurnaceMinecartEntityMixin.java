package dev.creoii.creoapi.mixin.tag.entity;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FurnaceMinecartEntity.class)
public class FurnaceMinecartEntityMixin {
    @Mutable @Shadow @Final private static Ingredient ACCEPTABLE_FUEL;

    static {
        ACCEPTABLE_FUEL = ItemTagImpl.applyFuelsFurnaceMinecarts();
    }
}
