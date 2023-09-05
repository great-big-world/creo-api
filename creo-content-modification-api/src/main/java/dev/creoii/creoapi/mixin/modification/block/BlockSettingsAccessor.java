package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.Settings.class)
public interface BlockSettingsAccessor {
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
}