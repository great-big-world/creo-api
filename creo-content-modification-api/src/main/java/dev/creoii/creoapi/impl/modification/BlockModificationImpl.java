package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.BlockModification;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import javax.sound.midi.Instrument;

public class BlockModificationImpl implements BlockModification {
    @Override
    public void setHardness(Block block, float hardness) {

    }

    @Override
    public float getHardness(Block block) {
        return 0;
    }

    @Override
    public void setResistance(Block block, float hardness) {

    }

    @Override
    public float getResistance(Block block) {
        return 0;
    }

    @Override
    public void setStrength(Block block, float hardness, float resistance) {

    }

    @Override
    public void setSoundGroup(Block block, BlockSoundGroup hardness) {

    }

    @Override
    public BlockSoundGroup getSoundGroup(Block block) {
        return null;
    }

    @Override
    public void setSlipperiness(Block block, float hardness) {

    }

    @Override
    public float getSlipperiness(Block block) {
        return 0;
    }

    @Override
    public void setLuminance(Block block, int hardness) {

    }

    @Override
    public int getLuminance(Block block) {
        return 0;
    }

    @Override
    public void setMapColor(Block block, MapColor hardness) {

    }

    @Override
    public MapColor getMapColor(Block block) {
        return null;
    }

    @Override
    public void setVelocityMultiplier(Block block, float hardness) {

    }

    @Override
    public float getVelocityMultiplier(Block block) {
        return 0;
    }

    @Override
    public void setJumpVelocityMultiplier(Block block, float hardness) {

    }

    @Override
    public float getJumpVelocityMultiplier(Block block) {
        return 0;
    }

    @Override
    public void setRandomTicks(Block block, boolean hardness) {

    }

    @Override
    public boolean hasRandomTicks(Block block) {
        return false;
    }

    @Override
    public void setToolRequired(Block block, boolean hardness) {

    }

    @Override
    public boolean isToolRequired(Block block) {
        return false;
    }

    @Override
    public void setLootTableId(Block block, Identifier hardness) {

    }

    @Override
    public Identifier getLootTableId(Block block) {
        return null;
    }

    @Override
    public void setOpaque(Block block, boolean hardness) {

    }

    @Override
    public boolean isOpaque(Block block) {
        return false;
    }

    @Override
    public void setAir(Block block, boolean hardness) {

    }

    @Override
    public boolean isAir(Block block) {
        return false;
    }

    @Override
    public void setBurnable(Block block, boolean hardness) {

    }

    @Override
    public boolean isBurnable(Block block) {
        return false;
    }

    @Override
    public void setPistonBehavior(Block block, PistonBehavior hardness) {

    }

    @Override
    public PistonBehavior getPistonBehavior(Block block) {
        return null;
    }

    @Override
    public void setInstrument(Block block, Instrument hardness) {

    }

    @Override
    public Instrument getInstrument(Block block) {
        return null;
    }

    @Override
    public void setBlockBreakParticles(Block block, boolean hardness) {

    }

    @Override
    public boolean hasBlockBreakParticles(Block block) {
        return false;
    }

    @Override
    public void setReplaceable(Block block, boolean hardness) {

    }

    @Override
    public boolean isReplaceable(Block block) {
        return false;
    }

    @Override
    public void setEmissiveLighting(Block block, boolean hardness) {

    }

    @Override
    public boolean hasEmissiveLighting(Block block) {
        return false;
    }

    @Override
    public void setPostProcessing(Block block, boolean hardness) {

    }

    @Override
    public boolean hasPostProcessing(Block block) {
        return false;
    }

    @Override
    public void setAllowsSpawning(Block block, boolean hardness) {

    }

    @Override
    public boolean doesAllowSpawning(Block block) {
        return false;
    }
}