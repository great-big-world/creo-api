package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Mixin(AbstractBlock.Settings.class)
public interface BlockSettingsAccessor {
    @Accessor("hardness")
    float getHardness();

    @Accessor("hardness")
    void setHardness(float hardness);

    @Accessor("luminance")
    ToIntFunction<BlockState> getLuminance();

    @Accessor("luminance")
    void setLuminance(ToIntFunction<BlockState> luminance);

    @Accessor("isAir")
    boolean isAir();

    @Accessor("isAir")
    void setAir(boolean air);

    @Accessor("burnable")
    boolean isBurnable();

    @Accessor("burnable")
    void setBurnable(boolean burnable);

    @Accessor("pistonBehavior")
    PistonBehavior getPistonBehavior();

    @Accessor("pistonBehavior")
    void setPistonBehavior(PistonBehavior pistonBehavior);

    @Accessor("mapColorProvider")
    Function<BlockState, MapColor> getMapColor();

    @Accessor("mapColorProvider")
    void setMapColor(Function<BlockState, MapColor> mapColor);

    @Accessor("toolRequired")
    boolean isToolRequired();

    @Accessor("toolRequired")
    void setToolRequired(boolean toolRequired);

    @Accessor("opaque")
    boolean isOpaque();

    @Accessor("opaque")
    void setOpaque(boolean opaque);

    @Accessor("suffocationPredicate")
    AbstractBlock.ContextPredicate getSuffocation();

    @Accessor("suffocationPredicate")
    void setSuffocation(AbstractBlock.ContextPredicate suffocation);

    @Accessor("blockVisionPredicate")
    AbstractBlock.ContextPredicate getBlocksVision();

    @Accessor("blockVisionPredicate")
    void setBlocksVision(AbstractBlock.ContextPredicate blocksVision);

    @Accessor("postProcessPredicate")
    AbstractBlock.ContextPredicate getPostProcessing();

    @Accessor("postProcessPredicate")
    void setPostProcessing(AbstractBlock.ContextPredicate postProcess);

    @Accessor("emissiveLightingPredicate")
    AbstractBlock.ContextPredicate getEmissiveLighting();

    @Accessor("emissiveLightingPredicate")
    void setEmissiveLighting(AbstractBlock.ContextPredicate emissiveLighting);

    @Accessor("blockBreakParticles")
    boolean hasBlockBreakParticles();

    @Accessor("blockBreakParticles")
    void setBlockBreakParticles(boolean blockBreakParticles);

    @Accessor("instrument")
    Instrument getInstrument();

    @Accessor("instrument")
    void setInstrument(Instrument instrument);

    @Accessor("replaceable")
    boolean isReplaceable();

    @Accessor("replaceable")
    void setReplaceable(boolean replaceable);

    @Accessor("dynamicBounds")
    boolean hasDynamicBounds();

    @Accessor("dynamicBounds")
    void setDynamicBounds(boolean dynamicBounds);
}