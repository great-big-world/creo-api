package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.CompositeFeatureConfig;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;
import java.util.function.BiPredicate;

public class CompositeFeature extends Feature<CompositeFeatureConfig> {
    public CompositeFeature(Codec<CompositeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext context) {
        CompositeFeatureConfig config = (CompositeFeatureConfig) context.getConfig();
        return config.failType().test(context, config.features());
    }

    public enum FailType implements StringIdentifiable {
        SOFT("soft", (context, entries) -> {
            for (RegistryEntry<PlacedFeature> entry : entries) {
                if (entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin())) {
                    return true;
                }
            }
            return false;
        }),
        HARD("hard", (context, entries) -> {
            for (RegistryEntry<PlacedFeature> entry : entries) {
                if (!entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin())) {
                    return false;
                }
            }
            return true;
        }),
        FREE("free", (context, entries) -> true);

        public static final Codec<FailType> CODEC = StringIdentifiable.createCodec(FailType::values);
        private final String id;
        private final BiPredicate<FeatureContext<?>, List<RegistryEntry<PlacedFeature>>> failPredicate;

        FailType(String id, BiPredicate<FeatureContext<?>, List<RegistryEntry<PlacedFeature>>> failPredicate) {
            this.id = id;
            this.failPredicate = failPredicate;
        }

        public boolean test(FeatureContext<?> context, List<RegistryEntry<PlacedFeature>> entries) {
            return failPredicate.test(context, entries);
        }

        @Override
        public String asString() {
            return id;
        }
    }
}
