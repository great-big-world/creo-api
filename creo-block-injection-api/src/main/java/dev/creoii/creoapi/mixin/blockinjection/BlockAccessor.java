package dev.creoii.creoapi.mixin.blockinjection;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Block.class)
public interface BlockAccessor {
    @Accessor("stateManager")
    void setStateManager(StateManager<Block, BlockState> stateManager);

    @Accessor("defaultState")
    void setDefaultState(BlockState defaultState);
}
