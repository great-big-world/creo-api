package dev.creoii.creoapi.api.modification;

import dev.creoii.creoapi.impl.modification.BlockModificationImpl;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import javax.sound.midi.Instrument;

public interface BlockModification {
    BlockModification INSTANCE = new BlockModificationImpl();

    void setHardness(Block block, float hardness);

    float getHardness(Block block);

    void setResistance(Block block, float hardness);

    float getResistance(Block block);

    void setStrength(Block block, float hardness, float resistance);

    void setSoundGroup(Block block, BlockSoundGroup hardness);

    BlockSoundGroup getSoundGroup(Block block);

    void setSlipperiness(Block block, float hardness);

    float getSlipperiness(Block block);

    void setLuminance(Block block, int hardness);

    int getLuminance(Block block);

    void setMapColor(Block block, MapColor hardness);

    MapColor getMapColor(Block block);

    void setVelocityMultiplier(Block block, float hardness);

    float getVelocityMultiplier(Block block);

    void setJumpVelocityMultiplier(Block block, float hardness);

    float getJumpVelocityMultiplier(Block block);

    void setRandomTicks(Block block, boolean hardness);

    boolean hasRandomTicks(Block block);

    void setToolRequired(Block block, boolean hardness);

    boolean isToolRequired(Block block);

    void setLootTableId(Block block, Identifier hardness);

    Identifier getLootTableId(Block block);

    void setOpaque(Block block, boolean hardness);

    boolean isOpaque(Block block);

    void setAir(Block block, boolean hardness);

    boolean isAir(Block block);

    void setBurnable(Block block, boolean hardness);

    boolean isBurnable(Block block);

    void setPistonBehavior(Block block, PistonBehavior hardness);

    PistonBehavior getPistonBehavior(Block block);

    void setInstrument(Block block, Instrument hardness);

    Instrument getInstrument(Block block);

    void setBlockBreakParticles(Block block, boolean hardness);

    boolean hasBlockBreakParticles(Block block);

    void setReplaceable(Block block, boolean hardness);

    boolean isReplaceable(Block block);

    void setEmissiveLighting(Block block, boolean hardness);

    boolean hasEmissiveLighting(Block block);

    void setPostProcessing(Block block, boolean hardness);

    boolean hasPostProcessing(Block block);

    void setAllowsSpawning(Block block, boolean hardness);

    boolean doesAllowSpawning(Block block);
}