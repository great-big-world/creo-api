package dev.creoii.creoapi.api.shader;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;

import java.util.Set;

public final class Shaders {
    private static final String NAMESPACE = "minecraft";
    private static final Set<Identifier> SHADERS = Sets.newHashSet();
    public static final Identifier ANTIALIAS = register(Identifier.of(NAMESPACE, "post/antialias"));//
    public static final Identifier ART = register(Identifier.of(NAMESPACE, "post/art"));//
    public static final Identifier BITS = register(Identifier.of(NAMESPACE, "post/bits"));//
    public static final Identifier BLOBS = register(Identifier.of(NAMESPACE, "post/blobs"));//
    public static final Identifier BLOBS2 = register(Identifier.of(NAMESPACE, "post/blobs2"));//
    public static final Identifier BUMPY = register(Identifier.of(NAMESPACE, "post/bumpy"));//
    public static final Identifier COLOR_CONVOLVE = register(Identifier.of(NAMESPACE, "post/color_convolve"));//
    public static final Identifier CREEPER = register(Identifier.of("post/creeper"));//
    public static final Identifier DECONVERGE = register(Identifier.of(NAMESPACE, "post/deconverge"));//
    public static final Identifier DESATURATE = register(Identifier.of(NAMESPACE, "post/desaturate"));//
    public static final Identifier FLIP = register(Identifier.of(NAMESPACE, "post/flip"));//
    public static final Identifier GREEN = register(Identifier.of(NAMESPACE, "post/green"));//
    public static final Identifier INVERT = register(Identifier.of("post/invert"));//
    public static final Identifier NOTCH = register(Identifier.of(NAMESPACE, "post/notch"));//
    public static final Identifier PENCIL = register(Identifier.of(NAMESPACE, "post/pencil"));//
    public static final Identifier PHOSPHOR = register(Identifier.of(NAMESPACE, "post/phosphor"));//
    public static final Identifier SCAN_PINCUSHION = register(Identifier.of(NAMESPACE, "post/scan_pincushion"));//
    public static final Identifier SOBEL = register(Identifier.of(NAMESPACE, "post/sobel"));//
    public static final Identifier SPIDER = register(Identifier.of("post/spider"));//
    public static final Identifier WOBBLE = register(Identifier.of(NAMESPACE, "post/wobble"));//

    private static Identifier register(Identifier id) {
        if (SHADERS.add(id)) {
            return id;
        } else throw new IllegalArgumentException("Shader " + id + " is already registered.");
    }

    public static Set<Identifier> getAll() {
        return SHADERS;
    }
}
