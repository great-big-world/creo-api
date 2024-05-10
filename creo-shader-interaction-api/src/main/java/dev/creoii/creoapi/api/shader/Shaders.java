package dev.creoii.creoapi.api.shader;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;

import java.util.Set;

public final class Shaders {
    private static final String NAMESPACE = "minecraft";
    private static final Set<Identifier> SHADERS = Sets.newHashSet();
    public static final Identifier ANTIALIAS = register(new Identifier(NAMESPACE, "post/antialias"));//
    public static final Identifier ART = register(new Identifier(NAMESPACE, "post/art"));//
    public static final Identifier BITS = register(new Identifier(NAMESPACE, "post/bits"));//
    public static final Identifier BLOBS = register(new Identifier(NAMESPACE, "post/blobs"));//
    public static final Identifier BLOBS2 = register(new Identifier(NAMESPACE, "post/blobs2"));//
    public static final Identifier BUMPY = register(new Identifier(NAMESPACE, "post/bumpy"));//
    public static final Identifier COLOR_CONVOLVE = register(new Identifier(NAMESPACE, "post/color_convolve"));//
    public static final Identifier CREEPER = register(new Identifier("post/creeper"));//
    public static final Identifier DECONVERGE = register(new Identifier(NAMESPACE, "post/deconverge"));//
    public static final Identifier DESATURATE = register(new Identifier(NAMESPACE, "post/desaturate"));//
    public static final Identifier FLIP = register(new Identifier(NAMESPACE, "post/flip"));//
    public static final Identifier GREEN = register(new Identifier(NAMESPACE, "post/green"));//
    public static final Identifier INVERT = register(new Identifier("post/invert"));//
    public static final Identifier NOTCH = register(new Identifier(NAMESPACE, "post/notch"));//
    public static final Identifier PENCIL = register(new Identifier(NAMESPACE, "post/pencil"));//
    public static final Identifier PHOSPHOR = register(new Identifier(NAMESPACE, "post/phosphor"));//
    public static final Identifier SCAN_PINCUSHION = register(new Identifier(NAMESPACE, "post/scan_pincushion"));//
    public static final Identifier SOBEL = register(new Identifier(NAMESPACE, "post/sobel"));//
    public static final Identifier SPIDER = register(new Identifier("post/spider"));//
    public static final Identifier WOBBLE = register(new Identifier(NAMESPACE, "post/wobble"));//

    private static Identifier register(Identifier id) {
        if (SHADERS.add(id)) {
            return id;
        } else throw new IllegalArgumentException("Shader " + id + " is already registered.");
    }

    public static Set<Identifier> getAll() {
        return SHADERS;
    }
}
