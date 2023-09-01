package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.AbstractBlockState.class)
public interface AbstractBlockStateAccessor {
    @Accessor("luminance")
    int getLuminance();

    @Accessor("isAir")
    boolean isAir();

    @Accessor("burnable")
    boolean isBurnable();

    @Accessor("pistonBehavior")
    PistonBehavior getPistonBehavior();

    @Accessor("mapColor")
    MapColor getMapColor();

    @Accessor("hardness")
    float getHardness();

    @Accessor("toolRequired")
    boolean isToolRequired();

    @Accessor("opaque")
    boolean isOpaque();

    @Accessor("suffocationPredicate")
    AbstractBlock.ContextPredicate getSuffocation();

    @Accessor("blockVisionPredicate")
    AbstractBlock.ContextPredicate getBlocksVision();

    @Accessor("postProcessPredicate")
    AbstractBlock.ContextPredicate getPostProcess();

    @Accessor("emissiveLightingPredicate")
    AbstractBlock.ContextPredicate getEmissiveLighting();

    @Accessor("blockBreakParticles")
    boolean hasBlockBreakParticles();

    @Accessor("instrument")
    Instrument getInstrument();

    @Accessor("replaceable")
    boolean isReplaceable();
}