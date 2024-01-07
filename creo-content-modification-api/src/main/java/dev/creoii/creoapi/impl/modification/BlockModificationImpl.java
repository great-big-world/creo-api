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
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public class BlockModificationImpl implements BlockModification {
    @Override
    public void setHardness(Block block, float hardness) {
        block.getStateManager().getStates().forEach(state -> {
            setHardness(state, hardness);
        });
    }

    @Override
    public void setHardness(BlockState state, float hardness) {
        ((AbstractBlockStateAccessor) state).setHardness(hardness);
        ((BlockSettingsAccessor) state.getBlock().getSettings()).setHardness(hardness);
    }

    @Override
    public void setResistance(Block block, float resistance) {
        ((AbstractBlockAccessor) block).setResistance(resistance);
    }

    @Override
    public void setStrength(Block block, float hardness, float resistance) {
        setHardness(block, hardness);
        setResistance(block, resistance);
    }

    @Override
    public void setStrength(Block block, float strength) {
        setStrength(block, strength, strength);
    }

    @Override
    public void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        ((AbstractBlockAccessor) block).setSoundGroup(soundGroup);
    }

    @Override
    public void setSlipperiness(Block block, float slipperiness) {
        ((AbstractBlockAccessor) block).setSlipperiness(slipperiness);
    }

    @Override
    public void setLuminance(Block block, int luminance) {
        block.getStateManager().getStates().forEach(state -> {
            setLuminance(state, luminance);
        });
    }

    @Override
    public void setLuminance(BlockState state, int luminance) {
        ((AbstractBlockStateAccessor) state).setLuminance(luminance);
    }

    @Override
    public void setMapColor(Block block, MapColor mapColor) {
        block.getStateManager().getStates().forEach(state -> {
            setMapColor(state, mapColor);
        });
    }

    @Override
    public void setMapColor(BlockState state, MapColor mapColor) {
        ((AbstractBlockStateAccessor) state).setMapColor(mapColor);
    }

    @Override
    public MapColor getMapColor(Block block) {
        return getMapColor(block.getDefaultState());
    }

    @Override
    public MapColor getMapColor(BlockState state) {
        return ((AbstractBlockStateAccessor) state).getMapColor();
    }

    @Override
    public void setVelocityMultiplier(Block block, float velocityMultiplier) {
        ((AbstractBlockAccessor) block).setVelocityMultiplier(velocityMultiplier);
    }

    @Override
    public void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier) {
        ((AbstractBlockAccessor) block).setJumpVelocityMultiplier(jumpVelocityMultiplier);
    }

    @Override
    public void setRandomTicks(Block block, boolean randomTicks) {
        ((AbstractBlockAccessor) block).setRandomTicks(randomTicks);
        block.getStateManager().getStates().forEach(state -> {
            ((AbstractBlockStateAccessor) state).setRandomTicks(randomTicks);
        });
    }

    @Override
    public void setToolRequired(Block block, boolean toolRequired) {
        block.getStateManager().getStates().forEach(state -> {
            setToolRequired(state, toolRequired);
        });
    }

    @Override
    public void setToolRequired(BlockState state, boolean toolRequired) {
        ((AbstractBlockStateAccessor) state).setToolRequired(toolRequired);
    }

    @Override
    public void setLootTableId(Block block, Identifier lootTableId) {
        ((AbstractBlockAccessor) block).setLootTableId(lootTableId);
    }

    @Override
    public void setOpaque(Block block, boolean opaque) {
        block.getStateManager().getStates().forEach(state -> {
            setOpaque(state, opaque);
        });
    }

    @Override
    public void setOpaque(BlockState state, boolean opaque) {
        ((AbstractBlockStateAccessor) state).setOpaque(opaque);
    }

    @Override
    public void setBurnable(Block block, boolean burnable) {
        block.getStateManager().getStates().forEach(state -> {
            setBurnable(state, burnable);
        });
    }

    @Override
    public void setBurnable(BlockState state, boolean burnable) {
        ((AbstractBlockStateAccessor) state).setBurnable(burnable);
    }

    @Override
    public void setPistonBehavior(Block block, PistonBehavior pistonBehavior) {
        block.getStateManager().getStates().forEach(state -> {
            setPistonBehavior(state, pistonBehavior);
        });
    }

    @Override
    public void setPistonBehavior(BlockState state, PistonBehavior pistonBehavior) {
        ((AbstractBlockStateAccessor) state).setPistonBehavior(pistonBehavior);
    }

    @Override
    public void setInstrument(Block block, Instrument instrument) {
        block.getStateManager().getStates().forEach(state -> {
            setInstrument(state, instrument);
        });
    }

    @Override
    public void setInstrument(BlockState state, Instrument instrument) {
        ((AbstractBlockStateAccessor) state).setInstrument(instrument);
    }

    @Override
    public void setBlockBreakParticles(Block block, boolean blockBreakParticles) {
        block.getStateManager().getStates().forEach(state -> {
            setBlockBreakParticles(state, blockBreakParticles);
        });
    }

    @Override
    public void setBlockBreakParticles(BlockState state, boolean blockBreakParticles) {
        ((AbstractBlockStateAccessor) state).setBlockBreakParticles(blockBreakParticles);
    }

    @Override
    public void setReplaceable(Block block, boolean replaceable) {
        block.getStateManager().getStates().forEach(state -> {
            setReplaceable(state, replaceable);
        });
    }

    @Override
    public void setReplaceable(BlockState state, boolean replaceable) {
        ((AbstractBlockStateAccessor) state).setReplaceable(replaceable);
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