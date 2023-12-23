package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.AbstractBlockState.class)
public interface AbstractBlockStateAccessor {
    @Accessor("hardness")
    float getHardness();

    @Accessor("hardness")
    void setHardness(float hardness);

    @Accessor("luminance")
    int getLuminance();

    @Accessor("luminance")
    void setLuminance(int luminance);

    @Accessor("mapColor")
    MapColor getMapColor();

    @Accessor("mapColor")
    void setMapColor(MapColor mapColor);

    @Accessor("toolRequired")
    boolean isToolRequired();

    @Accessor("toolRequired")
    void setToolRequired(boolean toolRequired);

    @Accessor("opaque")
    boolean isOpaque();

    @Accessor("opaque")
    void setOpaque(boolean opaque);

    @Accessor("blockBreakParticles")
    boolean hasBlockBreakParticles();

    @Accessor("blockBreakParticles")
    void setBlockBreakParticles(boolean blockBreakParticles);
}
