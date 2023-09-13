package dev.creoii.creoapi.api.worldgen.fastnoise;

import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class FastNoiseParameters {
    public static final RegistryKey<FastNoiseLite> DOTS = of("dots");
    public static final RegistryKey<FastNoiseLite> FRACTAL = of("fractal");
    public static final RegistryKey<FastNoiseLite> GLASS = of("glass");
    public static final RegistryKey<FastNoiseLite> GRID = of("grid");
    public static final RegistryKey<FastNoiseLite> HONEYCOMB = of("honeycomb");
    public static final RegistryKey<FastNoiseLite> LAYERS = of("layers");
    public static final RegistryKey<FastNoiseLite> MOSAIC = of("mosaic");
    public static final RegistryKey<FastNoiseLite> NOODLE = of("noodle");
    public static final RegistryKey<FastNoiseLite> PUZZLE = of("puzzle");
    public static final RegistryKey<FastNoiseLite> RIDGED_GRIDLIKE = of("ridged_gridlike");
    public static final RegistryKey<FastNoiseLite> SPLOTCHES = of("splotches");
    public static final RegistryKey<FastNoiseLite> SWIRLS = of("swirls");
    public static final RegistryKey<FastNoiseLite> WATERCOLOR = of("watercolor");
    public static final RegistryKey<FastNoiseLite> WEB = of("web");
    public static final RegistryKey<FastNoiseLite> WIERD = of("wierd");
    public static final RegistryKey<FastNoiseLite> ZONES = of("zones");

    private static RegistryKey<FastNoiseLite> of(String id) {
        return RegistryKey.of(FastNoiseLite.FAST_NOISE_SETTINGS_KEY, new Identifier(CreoWorldgen.NAMESPACE, id));
    }
}
