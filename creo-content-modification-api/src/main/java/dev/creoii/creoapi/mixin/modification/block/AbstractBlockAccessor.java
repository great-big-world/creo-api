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
    float getResistance();

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
    float getSlipperiness();

    @Accessor("slipperiness")
    void setSlipperiness(float slipperiness);

    @Accessor("velocityMultiplier")
    float getVelocityMultiplier();

    @Accessor("slipperiness")
    void setVelocityMultiplier(float velocityMultiplier);

    @Accessor("jumpVelocityMultiplier")
    float getJumpVelocityMultiplier();

    @Accessor("slipperiness")
    void setJumpVelocityMultiplier(float jumpVelocityMultiplier);

    @Accessor("lootTableId")
    Identifier getLootTableId();

    @Accessor("lootTableId")
    void setLootTableId(Identifier lootTableId);
}