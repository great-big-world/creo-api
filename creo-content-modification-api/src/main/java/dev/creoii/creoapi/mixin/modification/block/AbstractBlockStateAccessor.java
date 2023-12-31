package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
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

    @Accessor("burnable")
    boolean isBurnable();

    @Accessor("burnable")
    void setBurnable(boolean burnable);

    @Accessor("pistonBehavior")
    PistonBehavior getPistonBehavior();

    @Accessor("pistonBehavior")
    void setPistonBehavior(PistonBehavior pistonBehavior);

    @Accessor("instrument")
    Instrument getInstrument();

    @Accessor("instrument")
    void setInstrument(Instrument instrument);

    @Accessor("replaceable")
    boolean isReplaceable();

    @Accessor("replaceable")
    void setReplaceable(boolean replaceable);

    @Accessor("blockBreakParticles")
    boolean hasBlockBreakParticles();

    @Accessor("blockBreakParticles")
    void setBlockBreakParticles(boolean blockBreakParticles);
}
