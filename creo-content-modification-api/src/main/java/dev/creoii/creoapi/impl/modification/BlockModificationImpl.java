package dev.creoii.creoapi.impl.modification;

import dev.creoii.creoapi.api.modification.BlockModification;
import dev.creoii.creoapi.mixin.modification.block.AbstractBlockAccessor;
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

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class BlockModificationImpl implements BlockModification {
    @Override
    public void setHardness(Block block, float hardness) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setHardness(hardness);
    }

    @Override
    public float getHardness(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).getHardness();
    }

    public static void applyHardness(Block block, CallbackInfoReturnable<Float> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getHardness(block));
        }
    }

    @Override
    public void setResistance(Block block, float resistance) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setResistance(resistance);
    }

    @Override
    public float getResistance(Block block) {
        return ((AbstractBlockAccessor) block).getResistance();
    }

    public static void applyResistance(Block block, CallbackInfoReturnable<Float> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getResistance(block));
        }
    }

    @Override
    public void setStrength(Block block, float hardness, float resistance) {
        setHardness(block, hardness);
        setResistance(block, resistance);
    }

    @Override
    public void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setSoundGroup(soundGroup);
    }

    @Override
    public BlockSoundGroup getSoundGroup(Block block) {
        return ((AbstractBlockAccessor) block).getSoundGroup();
    }

    public static void applySoundGroup(Block block, CallbackInfoReturnable<BlockSoundGroup> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getSoundGroup(block));
        }
    }

    @Override
    public void setSlipperiness(Block block, float slipperiness) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setSlipperiness(slipperiness);
    }

    @Override
    public float getSlipperiness(Block block) {
        return ((AbstractBlockAccessor) block).getSlipperiness();
    }

    public static void applySlipperiness(Block block, CallbackInfoReturnable<Float> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getSlipperiness(block));
        }
    }

    @Override
    public void setLuminance(Block block, int luminance) {
        setLuminance(block, state -> luminance);
    }

    @Override
    public void setLuminance(Block block, ToIntFunction<BlockState> luminance) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setLuminance(luminance);
    }

    @Override
    public ToIntFunction<BlockState> getLuminance(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).getLuminance();
    }

    public static void applyLuminance(Block block, CallbackInfoReturnable<ToIntFunction<BlockState>> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getLuminance(block));
        }
    }

    @Override
    public void setMapColor(Block block, MapColor mapColor) {
        setMapColor(block, state -> mapColor);
    }

    @Override
    public void setMapColor(Block block, Function<BlockState, MapColor> mapColor) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setMapColor(mapColor);
    }

    @Override
    public Function<BlockState, MapColor> getMapColor(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).getMapColor();
    }

    public static void applyMapColor(Block block, CallbackInfoReturnable<Function<BlockState, MapColor>> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getMapColor(block));
        }
    }

    @Override
    public void setVelocityMultiplier(Block block, float velocityMultiplier) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setVelocityMultiplier(velocityMultiplier);
    }

    @Override
    public float getVelocityMultiplier(Block block) {
        return ((AbstractBlockAccessor) block).getVelocityMultiplier();
    }

    public static void applyVelocityMultiplier(Block block, CallbackInfoReturnable<Float> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getVelocityMultiplier(block));
        }
    }

    @Override
    public void setJumpVelocityMultiplier(Block block, float jumpVelocityMultiplier) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setJumpVelocityMultiplier(jumpVelocityMultiplier);
    }

    @Override
    public float getJumpVelocityMultiplier(Block block) {
        return ((AbstractBlockAccessor) block).getJumpVelocityMultiplier();
    }

    public static void applyJumpVelocityMultiplier(Block block, CallbackInfoReturnable<Float> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getJumpVelocityMultiplier(block));
        }
    }

    @Override
    public void setRandomTicks(Block block, boolean randomTicks) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setRandomTicks(randomTicks);
    }

    @Override
    public boolean hasRandomTicks(Block block) {
        return ((AbstractBlockAccessor) block).hasRandomTicks();
    }

    public static void applyRandomTicks(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.hasRandomTicks(block));
        }
    }

    @Override
    public void setToolRequired(Block block, boolean toolRequired) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setToolRequired(toolRequired);
    }

    @Override
    public boolean isToolRequired(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).isToolRequired();
    }

    public static void applyToolRequired(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.isToolRequired(block));
        }
    }

    @Override
    public void setLootTableId(Block block, Identifier lootTableId) {
        Modifiable.setModified(block);
        ((AbstractBlockAccessor) block).setLootTableId(lootTableId);
    }

    @Override
    public Identifier getLootTableId(Block block) {
        return ((AbstractBlockAccessor) block).getLootTableId();
    }

    public static void applyLootTableId(Block block, CallbackInfoReturnable<Identifier> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getLootTableId(block));
        }
    }

    @Override
    public void setOpaque(Block block, boolean opaque) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setOpaque(opaque);
    }

    @Override
    public boolean isOpaque(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).isOpaque();
    }

    public static void applyOpaque(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.isOpaque(block));
        }
    }

    @Override
    public void setAir(Block block, boolean air) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setAir(air);
    }

    @Override
    public boolean isAir(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).isAir();
    }

    public static void applyAir(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.isAir(block));
        }
    }

    @Override
    public void setBurnable(Block block, boolean burnable) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setBurnable(burnable);
    }

    @Override
    public boolean isBurnable(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).isBurnable();
    }

    public static void applyBurnable(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.isBurnable(block));
        }
    }

    @Override
    public void setPistonBehavior(Block block, PistonBehavior pistonBehavior) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setPistonBehavior(pistonBehavior);
    }

    @Override
    public PistonBehavior getPistonBehavior(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).getPistonBehavior();
    }

    public static void applyPistonBehavior(Block block, CallbackInfoReturnable<PistonBehavior> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getPistonBehavior(block));
        }
    }

    @Override
    public void setInstrument(Block block, Instrument instrument) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setInstrument(instrument);
    }

    @Override
    public Instrument getInstrument(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).getInstrument();
    }

    public static void applyInstrument(Block block, CallbackInfoReturnable<Instrument> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.getInstrument(block));
        }
    }

    @Override
    public void setBlockBreakParticles(Block block, boolean blockBreakParticles) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setBlockBreakParticles(blockBreakParticles);
    }

    @Override
    public boolean hasBlockBreakParticles(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).hasBlockBreakParticles();
    }

    public static void applyBlockBreakParticles(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.hasBlockBreakParticles(block));
        }
    }

    @Override
    public void setReplaceable(Block block, boolean replaceable) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setReplaceable(replaceable);
    }

    @Override
    public boolean isReplaceable(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).isReplaceable();
    }

    public static void applyReplaceable(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.hasDynamicBounds(block));
        }
    }

    @Override
    public void setDynamicBounds(Block block, boolean dynamicBounds) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setDynamicBounds(dynamicBounds);
    }

    @Override
    public boolean hasDynamicBounds(Block block) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).hasDynamicBounds();
    }

    public static void applyDynamicBounds(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(block)) {
            cir.setReturnValue(BlockModification.INSTANCE.hasDynamicBounds(block));
        }
    }

    @Override
    public void setEmissiveLighting(Block block, boolean emissiveLighting) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setEmissiveLighting((state, world, pos) -> emissiveLighting);
    }

    @Override
    public boolean hasEmissiveLighting(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getEmissiveLighting().test(state, world, pos);
    }

    public static void applyEmissiveLighting(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(state.getBlock())) {
            cir.setReturnValue(BlockModification.INSTANCE.hasEmissiveLighting(state, world, pos));
        }
    }

    @Override
    public void setPostProcessing(Block block, boolean postProcessing) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setPostProcessing((state, world, pos) -> postProcessing);
    }

    @Override
    public boolean hasPostProcessing(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getPostProcessing().test(state, world, pos);
    }

    public static void applyPostProcessing(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(state.getBlock())) {
            cir.setReturnValue(BlockModification.INSTANCE.hasPostProcessing(state, world, pos));
        }
    }

    @Override
    public void setBlocksVision(Block block, boolean blocksVision) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setBlocksVision((state, world, pos) -> blocksVision);
    }

    @Override
    public boolean doesBlockVision(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getBlocksVision().test(state, world, pos);
    }

    public static void applyBlocksVision(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(state.getBlock())) {
            cir.setReturnValue(BlockModification.INSTANCE.doesBlockVision(state, world, pos));
        }
    }

    @Override
    public void setSuffocates(Block block, boolean suffocates) {
        Modifiable.setModified(block);
        ((BlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings()).setSuffocation((state, world, pos) -> suffocates);
    }

    @Override
    public boolean doesSuffocate(BlockState state, BlockView world, BlockPos pos) {
        return ((BlockSettingsAccessor) ((AbstractBlockAccessor) state.getBlock()).getSettings()).getSuffocation().test(state, world, pos);
    }

    public static void applySuffocates(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Modifiable.canApply(state.getBlock())) {
            cir.setReturnValue(BlockModification.INSTANCE.doesSuffocate(state, world, pos));
        }
    }

    public interface Modifiable {
        boolean creo_isModified();

        void creo_setModified(boolean modified);

        static void setModified(Block block) {
            if (block instanceof Modifiable modifiable)
                modifiable.creo_setModified(true);
        }

        static boolean canApply(Block block) {
            if (block instanceof Modifiable modifiable)
                return modifiable.creo_isModified();
            return false;
        }
    }
}