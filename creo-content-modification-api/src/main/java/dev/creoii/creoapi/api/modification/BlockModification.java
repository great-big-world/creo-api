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
import org.jetbrains.annotations.ApiStatus;

/**
 * Methods to modify a block's {@link net.minecraft.block.AbstractBlock.Settings}.
 */
@ApiStatus.NonExtendable
public interface BlockModification {
    BlockModification INSTANCE = new BlockModificationImpl();

    /**
     * Set a new hardness value for the block.
     * @param block A block
     * @param hardness The new hardness value
     */
    void setHardness(Block block, float hardness);

    /**
     * Set a new hardness value for the {@link BlockState}.
     * @param state A blockstate
     * @param hardness The new hardness value
     */
    void setHardness(BlockState state, float hardness);

    /**
     * Get the hardness value of the block.
     * @param block A block
     * @return The block's hardness value
     */
    float getHardness(Block block);

    /**
     * Get the hardness value of the {@link BlockState}.
     * @param state A blockstate
     * @return The block's hardness value
     */
    float getHardness(BlockState state);

    /**
     * Set a new resistance value for the block.
     * @param block A block
     * @param resistance The new resistance value
     */
    void setResistance(Block block, float resistance);

    /**
     * Get the resistance value of the block.
     * @param block A block
     * @return The block's resistance value
     */
    float getResistance(Block block);

    /**
     * Set both the hardness and resistance for the block.
     * @param block A block
     * @param hardness The new hardness value
     * @param resistance The new resistance value
     */
    void setStrength(Block block, float hardness, float resistance);

    /**
     * Set both the hardness and resistance for the block.
     * @param block A block
     * @param strength The new hardness and resistance value
     */
    void setStrength(Block block, float strength);

    void setSoundGroup(Block block, BlockSoundGroup soundGroup);

    /**
     * Get the {@link BlockSoundGroup} value of the block.
     * @param block A block
     * @return The block's sound group
     */
    BlockSoundGroup getSoundGroup(Block block);

    /**
     * Set a new slipperiness value for the block.
     * @param block A block
     * @param slipperiness The new slipperiness value
     */
    void setSlipperiness(Block block, float slipperiness);

    /**
     * Get the slipperiness value of the block.
     * @param block A block
     * @return The block's slipperiness value
     */
    float getSlipperiness(Block block);

    /**
     * Set a new luminance value for the block.
     * @param block A block
     * @param luminance The new luminance value
     */
    void setLuminance(Block block, int luminance);

    /**
     * Set a new resistance value for the {@link BlockState}.
     * @param state A blockstate
     * @param luminance The new luminance value
     */
    void setLuminance(BlockState state, int luminance);

    /**
     * Get the luminance value of the block.
     * @param block A block
     * @return The block's luminance value
     */
    int getLuminance(Block block);

    /**
     * Get the luminance value of the {@link BlockState}.
     * @param state A blockstate
     * @return The blockstate's luminance value
     */
    int getLuminance(BlockState state);

    /**
     * Set a new {@link MapColor} for the block.
     * @param block A block
     * @param mapColor The new map color
     */
    void setMapColor(Block block, MapColor mapColor);

    /**
     * Set a new {@link MapColor} for the {@link BlockState}.
     * @param state A blockstate
     * @param mapColor The new map color
     */
    void setMapColor(BlockState state, MapColor mapColor);

    /**
     * Get the {@link MapColor} of the block.
     * @param block A block
     * @return The block's map color
     */
    MapColor getMapColor(Block block);

    /**
     * Get the {@link MapColor} of the {@link BlockState}.
     * @param state A blockstate
     * @return The blockstate's map color
     */
    MapColor getMapColor(BlockState state);

    /**
     * Set a new velocity multiplier value for the block.
     * @param block A block
     * @param velocityMultiplier The new velocity multiplier value
     */
    void setVelocityMultiplier(Block block, float velocityMultiplier);

    /**
     * Get the velocity multiplier value of the block.
     * @param block A block
     * @return The block's velocity multiplier value
     */
    float getVelocityMultiplier(Block block);

    /**
     * Set a new jump velocity multiplier value for the block.
     * @param block A block
     * @param jumpVelocityMultiplier The new jump velocity multiplier value
     */
    void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier);

    /**
     * Get the jump velocity multiplier value of the block.
     * @param block A block
     * @return The block's jump velocity multiplier value
     */
    float getJumpVelocityMultiplier(Block block);

    void setRandomTicks(Block block, boolean randomTicks);

    boolean hasRandomTicks(Block block);

    void setToolRequired(Block block, boolean toolRequired);

    void setToolRequired(BlockState state, boolean toolRequired);

    boolean isToolRequired(Block block);

    void setLootTableId(Block block, Identifier lootTableId);

    /**
     * Get the loot table id of the block.
     * @param block A block
     * @return The block's loot table id
     */
    Identifier getLootTableId(Block block);

    void setOpaque(Block block, boolean opaque);

    void setOpaque(BlockState state, boolean opaque);

    boolean isOpaque(Block block);

    /**
     * Set whether the block can be lit on fire by lava.
     * @param block A block
     * @param burnable Whether the block can be lit on fire by lava
     */
    void setBurnable(Block block, boolean burnable);

    /**
     * Set whether the {@link BlockState} can be lit on fire by lava.
     * @param state A blockstate
     * @param burnable Whether the blockstate can be lit on fire by lava
     */
    void setBurnable(BlockState state, boolean burnable);

    /**
     * Returns whether the block can be lit on fire by lava.
     * @param block A block
     * @return Whether lava can light the block on fire
     */
    boolean isBurnable(Block block);

    /**
     * Set a new {@link PistonBehavior} for the block.
     * @param block A block
     * @param pistonBehavior The new piston behavior
     */
    void setPistonBehavior(Block block, PistonBehavior pistonBehavior);

    /**
     * Set a new {@link PistonBehavior} for the {@link BlockState}.
     * @param state A blockstate
     * @param pistonBehavior The new piston behavior
     */
    void setPistonBehavior(BlockState state, PistonBehavior pistonBehavior);

    /**
     * Get the {@link PistonBehavior} of the block.
     * @param block A block
     * @return The block's piston behavior
     */
    PistonBehavior getPistonBehavior(Block block);

    void setInstrument(Block block, Instrument instrument);

    void setInstrument(BlockState state, Instrument instrument);

    /**
     * Get the {@link Instrument} of the block.
     * @param block A block
     * @return The block's instrument
     */
    Instrument getInstrument(Block block);

    void setBlockBreakParticles(Block block, boolean blockBreakParticles);

    void setBlockBreakParticles(BlockState state, boolean blockBreakParticles);

    boolean hasBlockBreakParticles(Block block);

    void setReplaceable(Block block, boolean replaceable);

    void setReplaceable(BlockState state, boolean replaceable);

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