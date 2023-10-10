package dev.creoii.creoapi.api.worldgen.fastnoise;

import dev.creoii.creoapi.api.worldgen.CreoWorldgen;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class FastNoiseParametersKeys {
    public static final RegistryKey<FastNoiseParameters> DOTS = of("dots");
    public static final RegistryKey<FastNoiseParameters> FRACTAL = of("fractal");
    public static final RegistryKey<FastNoiseParameters> GLASS = of("glass");
    public static final RegistryKey<FastNoiseParameters> GRID = of("grid");
    public static final RegistryKey<FastNoiseParameters> HONEYCOMB = of("honeycomb");
    public static final RegistryKey<FastNoiseParameters> LAYERS = of("layers");
    public static final RegistryKey<FastNoiseParameters> MOSAIC = of("mosaic");
    public static final RegistryKey<FastNoiseParameters> NOODLE = of("noodle");
    public static final RegistryKey<FastNoiseParameters> PUZZLE = of("puzzle");
    public static final RegistryKey<FastNoiseParameters> RIDGED_GRIDLIKE = of("ridged_gridlike");
    public static final RegistryKey<FastNoiseParameters> SPLOTCHES = of("splotches");
    public static final RegistryKey<FastNoiseParameters> SWIRLS = of("swirls");
    public static final RegistryKey<FastNoiseParameters> WATERCOLOR = of("watercolor");
    public static final RegistryKey<FastNoiseParameters> WEB = of("web");
    public static final RegistryKey<FastNoiseParameters> WIERD = of("wierd");
    public static final RegistryKey<FastNoiseParameters> ZONES = of("zones");

    private static RegistryKey<FastNoiseParameters> of(String id) {
        return RegistryKey.of(FastNoiseParameters.REGISTRY_KEY, new Identifier(CreoWorldgen.NAMESPACE, id));
    }
}
