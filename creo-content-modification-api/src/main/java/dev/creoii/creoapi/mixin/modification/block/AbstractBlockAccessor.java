package dev.creoii.creoapi.mixin.modification.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor {
    @Accessor("settings")
    AbstractBlock.Settings getSettings();

    @Accessor("resistance")
    void setResistance(float resistance);

    @Accessor("randomTicks")
    boolean hasRandomTicks();

    @Accessor("randomTicks")
    void setRandomTicks(boolean randomTicks);

    @Accessor("soundGroup")
    BlockSoundGroup getSoundGroup();

    @Accessor("soundGroup")
    void setSoundGroup(BlockSoundGroup soundGroup);

    @Accessor("slipperiness")
    void setSlipperiness(float slipperiness);

    @Accessor("velocityMultiplier")
    void setVelocityMultiplier(float velocityMultiplier);

    @Accessor("jumpVelocityMultiplier")
    void setJumpVelocityMultiplier(float jumpVelocityMultiplier);

    @Accessor("lootTableId")
    Identifier getLootTableId();

    @Accessor("lootTableId")
    void setLootTableId(Identifier lootTableId);
}