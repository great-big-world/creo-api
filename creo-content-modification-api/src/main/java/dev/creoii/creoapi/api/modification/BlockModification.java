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
     * Set a new resistance value for the block.
     * @param block A block
     * @param resistance The new resistance value
     */
    void setResistance(Block block, float resistance);

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

    /**
     * Set a new {@link BlockSoundGroup} for the block.
     * @param block a block
     * @param soundGroup the new sound group.
     */
    void setSoundGroup(Block block, BlockSoundGroup soundGroup);

    /**
     * Set a new slipperiness value for the block.
     * @param block a block
     * @param slipperiness the new slipperiness value
     */
    void setSlipperiness(Block block, float slipperiness);

    /**
     * Set a new luminance value for the block.
     * @param block a block
     * @param luminance the new luminance value
     */
    void setLuminance(Block block, int luminance);

    /**
     * Set a new resistance value for the {@link BlockState}.
     * @param state a blockstate
     * @param luminance the new luminance value
     */
    void setLuminance(BlockState state, int luminance);

    /**
     * Set a new {@link MapColor} for the block.
     * @param block a block
     * @param mapColor the new map color
     */
    void setMapColor(Block block, MapColor mapColor);

    /**
     * Set a new {@link MapColor} for the {@link BlockState}.
     * @param state a blockstate
     * @param mapColor the new map color
     */
    void setMapColor(BlockState state, MapColor mapColor);

    /**
     * Get the {@link MapColor} of the block.
     * @param block a block
     * @return the block's map color
     */
    MapColor getMapColor(Block block);

    /**
     * Get the {@link MapColor} of the {@link BlockState}.
     * @param state a blockstate
     * @return the blockstate's map color
     */
    MapColor getMapColor(BlockState state);

    /**
     * Set a new velocity multiplier value for the block.
     * @param block a block
     * @param velocityMultiplier the new velocity multiplier value
     */
    void setVelocityMultiplier(Block block, float velocityMultiplier);

    /**
     * Set a new jump velocity multiplier value for the block.
     * @param block a block
     * @param jumpVelocityMultiplier the new jump velocity multiplier value
     */
    void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier);

    /**
     * Set whether the block has dynamic bounds.
     * @param block a block
     * @param dynamicBounds whether the block has dynamic bounds
     */
    void setDynamicBounds(Block block, boolean dynamicBounds);

    /**
     * Set whether the block ticks randomly.
     * @param block a block
     * @param randomTicks whether the block ticks randomly
     */
    void setRandomTicks(Block block, boolean randomTicks);

    /**
     * Set whether a tool is required to mine the block.
     * @param block a block
     * @param toolRequired whether a tool is required to mine the block
     */
    void setToolRequired(Block block, boolean toolRequired);

    /**
     * Set whether a tool is required to mine the blockstate.
     * @param state a blockstate
     * @param toolRequired whether a tool is required to mine the blockstate
     */
    void setToolRequired(BlockState state, boolean toolRequired);

    /**
     * Set the loot table identifier of the block.
     * @param block a block
     * @param lootTableId the new loot table id of the block
     */
    void setLootTableId(Block block, Identifier lootTableId);

    /**
     * Set whether the block is opaque.
     * @param block a block
     * @param opaque whether the block is opaque
     */
    void setOpaque(Block block, boolean opaque);

    /**
     * Set whether the blockstate is opaque.
     * @param state a blockstate
     * @param opaque whether the blockstate is opaque
     */
    void setOpaque(BlockState state, boolean opaque);

    /**
     * Set whether the block can be lit on fire by lava.
     * @param block a block
     * @param burnable whether the block can be lit on fire by lava
     */
    void setBurnable(Block block, boolean burnable);

    /**
     * Set whether the {@link BlockState} can be lit on fire by lava.
     * @param state a blockstate
     * @param burnable whether the blockstate can be lit on fire by lava
     */
    void setBurnable(BlockState state, boolean burnable);

    /**
     * Set a new {@link PistonBehavior} for the block.
     * @param block a block
     * @param pistonBehavior the new piston behavior
     */
    void setPistonBehavior(Block block, PistonBehavior pistonBehavior);

    /**
     * Set a new {@link PistonBehavior} for the {@link BlockState}.
     * @param state a blockstate
     * @param pistonBehavior the new piston behavior
     */
    void setPistonBehavior(BlockState state, PistonBehavior pistonBehavior);

    /**
     * Set a new {@link Instrument} for the block.
     * @param block a block
     * @param instrument the new Instrument
     */
    void setInstrument(Block block, Instrument instrument);

    /**
     * Set a new {@link Instrument} for the blockstate.
     * @param state a blockstate
     * @param instrument the new Instrument
     */
    void setInstrument(BlockState state, Instrument instrument);

    /**
     * Set whether the block has block break particles.
     * @param block a block
     * @param blockBreakParticles whether the block has block break particles
     */
    void setBlockBreakParticles(Block block, boolean blockBreakParticles);

    /**
     * Set whether the blockstate has block break particles.
     * @param state a blockstate
     * @param blockBreakParticles whether the blockstate has block break particles
     */
    void setBlockBreakParticles(BlockState state, boolean blockBreakParticles);

    /**
     * Set whether the block is replaceable
     * @param block a block
     * @param replaceable whether the block is replaceable
     */
    void setReplaceable(Block block, boolean replaceable);

    /**
     * Set whether the blockstate is replaceable
     * @param state a blockstate
     * @param replaceable whether the blockstate is replaceable
     */
    void setReplaceable(BlockState state, boolean replaceable);

    void setEmissiveLighting(Block block, boolean emissiveLighting);

    boolean hasEmissiveLighting(BlockState state, BlockView world, BlockPos pos);

    void setPostProcessing(Block block, boolean postProcessing);

    boolean hasPostProcessing(BlockState state, BlockView world, BlockPos pos);

    void setBlocksVision(Block block, boolean blocksVision);

    boolean doesBlockVision(BlockState state, BlockView world, BlockPos pos);

    void setSuffocates(Block block, boolean suffocates);

    boolean doesSuffocate(BlockState state, BlockView world, BlockPos pos);
}