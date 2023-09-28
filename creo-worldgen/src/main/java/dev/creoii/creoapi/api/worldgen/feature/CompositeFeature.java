package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.CompositeFeatureConfig;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.List;
import java.util.function.BiPredicate;

public class CompositeFeature extends Feature<CompositeFeatureConfig> {
    public CompositeFeature(Codec<CompositeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext context) {
        CompositeFeatureConfig config = (CompositeFeatureConfig) context.getConfig();
        return config.type().test(context, config.features());
    }

    public enum Type implements StringIdentifiable {
        SOFT("soft", (context, entries) -> {
            MutableBoolean fail = new MutableBoolean(false);
            for (RegistryEntry<PlacedFeature> entry : entries) {
                if (entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin()))
                    fail.setTrue();
            }
            return fail.booleanValue();
        }),
        HARD("hard", (context, entries) -> {
            for (RegistryEntry<PlacedFeature> entry : entries) {
                if (!entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin()))
                    return false;
            }
            return true;
        }),
        FREE("free", (context, entries) -> {
            for (RegistryEntry<PlacedFeature> entry : entries) {
                entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin());
            }
            return true;
        });

        public static final Codec<Type> CODEC = StringIdentifiable.createCodec(Type::values);
        private final String id;
        private final BiPredicate<FeatureContext<?>, List<RegistryEntry<PlacedFeature>>> typePredicate;

        Type(String id, BiPredicate<FeatureContext<?>, List<RegistryEntry<PlacedFeature>>> typePredicate) {
            this.id = id;
            this.typePredicate = typePredicate;
        }

        public boolean test(FeatureContext<?> context, List<RegistryEntry<PlacedFeature>> entries) {
            return typePredicate.test(context, entries);
        }

        @Override
        public String asString() {
            return id;
        }
    }
}
