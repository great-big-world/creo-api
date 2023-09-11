package dev.creoii.creoapi.api.shader;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;

import java.util.Set;

public final class Shaders {
    private static final Set<Identifier> SHADERS = Sets.newHashSet();
    public static final Identifier ANTIALIAS = register(new Identifier("post/antialias"));
    public static final Identifier ART = register(new Identifier("post/art"));
    public static final Identifier BITS = register(new Identifier("post/bits"));
    public static final Identifier BLOBS = register(new Identifier("post/blobs"));
    public static final Identifier BLOBS2 = register(new Identifier("post/blobs2"));
    public static final Identifier BLUR = register(new Identifier("post/blur"));
    public static final Identifier BUMPY = register(new Identifier("post/bumpy"));
    public static final Identifier COLOR_CONVOLVE = register(new Identifier("post/color_convolve"));
    public static final Identifier CREEPER = register(new Identifier("post/creeper"));
    public static final Identifier DECONVERGE = register(new Identifier("post/deconverge"));
    public static final Identifier DESATURATE = register(new Identifier("post/desaturate"));
    public static final Identifier ENTITY_OUTLINE = register(new Identifier("post/entity_outline"));
    public static final Identifier FLIP = register(new Identifier("post/flip"));
    public static final Identifier FXAA = register(new Identifier("post/fxaa"));
    public static final Identifier GREEN = register(new Identifier("post/green"));
    public static final Identifier INVERT = register(new Identifier("post/invert"));
    public static final Identifier NOTCH = register(new Identifier("post/notch"));
    public static final Identifier NTSC = register(new Identifier("post/ntsc"));
    public static final Identifier OUTLINE = register(new Identifier("post/outline"));
    public static final Identifier PENCIL = register(new Identifier("post/pencil"));
    public static final Identifier PHOSPHOR = register(new Identifier("post/phosphor"));
    public static final Identifier SCAN_PINCUSHION = register(new Identifier("post/scan_pincushion"));
    public static final Identifier SOBEL = register(new Identifier("post/sobel"));
    public static final Identifier SPIDER = register(new Identifier("post/spider"));
    public static final Identifier TRANSPARENCY = register(new Identifier("post/transparency"));
    public static final Identifier WOBBLE = register(new Identifier("post/wobble"));

    private static Identifier register(Identifier id) {
        if (SHADERS.add(id)) {
            return id;
        } else
            throw new IllegalArgumentException("Shader " + id + " is already registered.");
    }

    public static Set<Identifier> getAll() {
        return SHADERS;
    }
}
