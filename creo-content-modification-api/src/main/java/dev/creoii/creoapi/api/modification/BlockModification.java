package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public interface BlockModification {
    BlockModification INSTANCE = new BlockModificationImpl();

    void setHardness(Block block, float hardness);

    float getHardness(Block block);

    void setResistance(Block block, float resistance);

    float getResistance(Block block);

    void setStrength(Block block, float hardness, float resistance);

    void setSoundGroup(Block block, BlockSoundGroup soundGroup);

    BlockSoundGroup getSoundGroup(Block block);

    void setSlipperiness(Block block, float slipperiness);

    float getSlipperiness(Block block);

    void setLuminance(Block block, int luminance);

    int getLuminance(Block block);

    void setMapColor(Block block, MapColor mapColor);

    MapColor getMapColor(Block block);

    void setVelocityMultiplier(Block block, float velocityMultiplier);

    float getVelocityMultiplier(Block block);

    void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier);

    float getJumpVelocityMultiplier(Block block);

    void setRandomTicks(Block block, boolean randomTicks);

    boolean hasRandomTicks(Block block);

    void setToolRequired(Block block, boolean toolRequired);

    boolean isToolRequired(Block block);

    void setLootTableId(Block block, Identifier lootTableId);

    Identifier getLootTableId(Block block);

    void setOpaque(Block block, boolean opaque);

    boolean isOpaque(Block block);

    void setBurnable(Block block, boolean burnable);

    /**
     * @return Whether Lava can light the block on fire.
     */
    boolean isBurnable(Block block);

    void setPistonBehavior(Block block, PistonBehavior pistonBehavior);

    PistonBehavior getPistonBehavior(Block block);

    void setInstrument(Block block, Instrument instrument);

    Instrument getInstrument(Block block);

    void setBlockBreakParticles(Block block, boolean blockBreakParticles);

    boolean hasBlockBreakParticles(Block block);

    void setReplaceable(Block block, boolean replaceable);

    boolean isReplaceable(Block block);

    void setEmissiveLighting(Block block, boolean emissiveLighting);

    boolean hasEmissiveLighting(BlockState state, BlockView world, BlockPos pos);

    void setPostProcessing(Block block, boolean postProcessing);

    boolean hasPostProcessing(BlockState state, BlockView world, BlockPos pos);

    void setBlocksVision(Block block, boolean blocksVision);

    boolean doesBlockVision(BlockState state, BlockView world, BlockPos pos);

    void setSuffocates(Block block, boolean suffocates);

    boolean doesSuffocate(BlockState state, BlockView world, BlockPos pos);
}