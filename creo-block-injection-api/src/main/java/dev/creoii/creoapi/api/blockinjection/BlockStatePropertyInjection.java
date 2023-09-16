package dev.creoii.creoapi.api.blockinjection;

import dev.creoii.creoapi.impl.blockinjection.BlockStatePropertyInjectionImpl;
import net.minecraft.block.Block;
import net.minecraft.state.property.Property;

public final class BlockStatePropertyInjection {
    public static void inject(Block block, Property<?> property) {
        BlockStatePropertyInjectionImpl.inject(block, property);
    }

    public static <T extends Comparable<T>> void inject(Block block, Property<T> property, T defaultValue) {
        BlockStatePropertyInjectionImpl.inject(block, property, defaultValue);
    }
}
