package dev.creoii.creoapi.api.blockinjection;

import dev.creoii.creoapi.impl.blockinjection.BlockStatePropertyInjectionImpl;
import net.minecraft.block.Block;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.Nullable;

public final class BlockStatePropertyInjection {
    public static <T extends Comparable<T>> void inject(Property<T> property, Block block) {
        inject(property, null, block);
    }

    public static <T extends Comparable<T>> void inject(Property<T> property, Block... blocks) {
        for (Block block : blocks) {
            inject(property, block);
        }
    }

    public static <T extends Comparable<T>> void inject(Property<T> property, @Nullable T defaultValue, Block block) {
        BlockStatePropertyInjectionImpl.inject(block, property, defaultValue);
    }

    public static <T extends Comparable<T>> void inject(Property<T> property, @Nullable T defaultValue, Block... blocks) {
        for (Block block : blocks) {
            inject(property, defaultValue, block);
        }
    }
}
