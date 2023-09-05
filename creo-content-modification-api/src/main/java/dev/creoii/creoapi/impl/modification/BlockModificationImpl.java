package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.BlockModification;
import dev.creoii.creoapi.mixin.modification.block.AbstractBlockAccessor;
import dev.creoii.creoapi.mixin.modification.block.AbstractBlockStateAccessor;
import dev.creoii.creoapi.mixin.modification.block.BlockSettingsAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class BlockModificationImpl implements BlockModification {
    @Override
    public void setHardness(Block block, float hardness) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setHardness(hardness);
    }

    @Override
    public float getHardness(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).getHardness();
    }

    @Override
    public void setResistance(Block block, float resistance) {
        ((AbstractBlockAccessor) block).setResistance(resistance);
    }

    @Override
    public float getResistance(Block block) {
        return block.getBlastResistance();
    }

    @Override
    public void setStrength(Block block, float hardness, float resistance) {
        setHardness(block, hardness);
        setResistance(block, resistance);
    }

    @Override
    public void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        ((AbstractBlockAccessor) block).setSoundGroup(soundGroup);
    }

    @Override
    public BlockSoundGroup getSoundGroup(Block block) {
        return ((AbstractBlockAccessor) block).getSoundGroup();
    }

    @Override
    public void setSlipperiness(Block block, float slipperiness) {
        ((AbstractBlockAccessor) block).setSlipperiness(slipperiness);
    }

    @Override
    public float getSlipperiness(Block block) {
        return block.getSlipperiness();
    }

    @Override
    public void setLuminance(Block block, int luminance) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setLuminance(luminance);
    }

    @Override
    public int getLuminance(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).getLuminance();
    }

    @Override
    public void setMapColor(Block block, MapColor mapColor) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setMapColor(mapColor);
    }

    @Override
    public MapColor getMapColor(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).getMapColor();
    }

    @Override
    public void setVelocityMultiplier(Block block, float velocityMultiplier) {
        ((AbstractBlockAccessor) block).setVelocityMultiplier(velocityMultiplier);
    }

    @Override
    public float getVelocityMultiplier(Block block) {
        return block.getVelocityMultiplier();
    }

    @Override
    public void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier) {
        ((AbstractBlockAccessor) block).setJumpVelocityMultiplier(jumpVelocityMultiplier);
    }

    @Override
    public float getJumpVelocityMultiplier(Block block) {
        return block.getJumpVelocityMultiplier();
    }

    @Override
    public void setRandomTicks(Block block, boolean randomTicks) {
        ((AbstractBlockAccessor) block).setRandomTicks(randomTicks);
    }

    @Override
    public boolean hasRandomTicks(Block block) {
        return ((AbstractBlockAccessor) block).hasRandomTicks();
    }

    @Override
    public void setToolRequired(Block block, boolean toolRequired) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setToolRequired(toolRequired);
    }

    @Override
    public boolean isToolRequired(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).isToolRequired();
    }

    @Override
    public void setLootTableId(Block block, Identifier lootTableId) {
        ((AbstractBlockAccessor) block).setLootTableId(lootTableId);
    }

    @Override
    public Identifier getLootTableId(Block block) {
        return ((AbstractBlockAccessor) block).getLootTableId();
    }

    @Override
    public void setOpaque(Block block, boolean opaque) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setOpaque(opaque);
    }

    @Override
    public boolean isOpaque(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).isOpaque();
    }

    @Override
    public void setBurnable(Block block, boolean burnable) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setBurnable(burnable);
    }

    @Override
    public boolean isBurnable(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).isBurnable();
    }

    @Override
    public void setPistonBehavior(Block block, PistonBehavior pistonBehavior) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setPistonBehavior(pistonBehavior);
    }

    @Override
    public PistonBehavior getPistonBehavior(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).getPistonBehavior();
    }

    @Override
    public void setInstrument(Block block, Instrument instrument) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setInstrument(instrument);
    }

    @Override
    public Instrument getInstrument(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).getInstrument();
    }

    @Override
    public void setBlockBreakParticles(Block block, boolean blockBreakParticles) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setBlockBreakParticles(blockBreakParticles);
    }

    @Override
    public boolean hasBlockBreakParticles(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).hasBlockBreakParticles();
    }

    @Override
    public void setReplaceable(Block block, boolean replaceable) {
        ((AbstractBlockStateAccessor) (block.getDefaultState())).setReplaceable(replaceable);
    }

    @Override
    public boolean isReplaceable(Block block) {
        return ((AbstractBlockStateAccessor) block.getDefaultState()).isReplaceable();
    }

    @Override
    public void setEmissiveLighting(Block block, boolean emissiveLighting) {
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setEmissiveLighting((state, world, pos) -> emissiveLighting);
    }

    @Override
    public boolean hasEmissiveLighting(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getEmissiveLighting().test(state, world, pos);
    }

    public static void applyEmissiveLighting(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BlockModification.INSTANCE.hasEmissiveLighting(state, world, pos));
    }

    @Override
    public void setPostProcessing(Block block, boolean postProcessing) {
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setPostProcessing((state, world, pos) -> postProcessing);
    }

    @Override
    public boolean hasPostProcessing(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getPostProcessing().test(state, world, pos);
    }

    public static void applyPostProcessing(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BlockModification.INSTANCE.hasPostProcessing(state, world, pos));
    }

    @Override
    public void setBlocksVision(Block block, boolean blocksVision) {
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setBlocksVision((state, world, pos) -> blocksVision);
    }

    @Override
    public boolean doesBlockVision(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getBlocksVision().test(state, world, pos);
    }

    public static void applyBlocksVision(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BlockModification.INSTANCE.doesBlockVision(state, world, pos));
    }

    @Override
    public void setSuffocates(Block block, boolean suffocates) {
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setSuffocation((state, world, pos) -> suffocates);
    }

    @Override
    public boolean doesSuffocate(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getSuffocation().test(state, world, pos);
    }

    public static void applySuffocates(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BlockModification.INSTANCE.doesSuffocate(state, world, pos));
    }
}