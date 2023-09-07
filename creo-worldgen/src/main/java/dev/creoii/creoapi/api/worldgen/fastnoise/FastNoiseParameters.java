package dev.creoii.creoapi.api.worldgen.fastnoise;

import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class FastNoiseParameters {
    public static final RegistryKey<FastNoiseLite> NOODLE = of("noodle");
    public static final RegistryKey<FastNoiseLite> HONEYCOMB = of("honeycomb");
    public static final RegistryKey<FastNoiseLite> RIDGED_GRIDLIKE = of("ridged_gridlike");
    public static final RegistryKey<FastNoiseLite> ZONES = of("zones");
    public static final RegistryKey<FastNoiseLite> GRID = of("grid");
    public static final RegistryKey<FastNoiseLite> WEB = of("web");

    private static RegistryKey<FastNoiseLite> of(String id) {
        return RegistryKey.of(FastNoiseLite.FAST_NOISE_SETTINGS_KEY, new Identifier(id));
    }
}
