package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.blockinjection.BlockEntityTypeInjection;
import dev.creoii.creoapi.api.blockinjection.PointOfInterestTypeInjection;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.world.poi.PointOfInterestTypes;

public class BlockInjectionTest implements ModInitializer {
    @Override
    public void onInitialize() {
        PointOfInterestTypeInjection.inject(PointOfInterestTypes.LIGHTNING_ROD, Blocks.BEACON);
        BlockEntityTypeInjection.inject(BlockEntityType.SIGN, Blocks.OAK_FENCE);
    }
}