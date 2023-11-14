package dev.creoii.creoapi.impl.blockinjection;

import dev.creoii.creoapi.api.blockinjection.CreoBlockInjection;
import dev.creoii.creoapi.mixin.blockinjection.BlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public final class BlockStatePropertyInjectionImpl {
    public static <T extends Comparable<T>> void inject(Block block, Property<T> property, @Nullable T defaultValue) {
        BlockAccessor accessor = tryInjectProperty(block, property);
        if (accessor == null)
            return;

        if (defaultValue == null)
            accessor.setDefaultState(block.getStateManager().getDefaultState());
        else
            accessor.setDefaultState(block.getStateManager().getDefaultState().with(property, defaultValue));
        refreshStateIds(block);
    }

    @Nullable
    private static BlockAccessor tryInjectProperty(Block block, Property<?> property) {
        if (block.getStateManager().getProperties().contains(property)) {
            CreoBlockInjection.LOGGER.warn("Property '" + property.getName() + "' already exists in block '" + Registries.BLOCK.getId(block) + "'");
            return null;
        }

        StateManager.Builder<Block, BlockState> builder = new StateManager.Builder<>(block);
        block.getStateManager().getProperties().forEach(builder::add);
        builder.add(property);
        BlockAccessor accessor = (BlockAccessor) block;
        accessor.setStateManager(builder.build(Block::getDefaultState, BlockState::new));
        return accessor;
    }

    private static void refreshStateIds(Block block) {
        for (BlockState blockState : block.getStateManager().getStates()) {
            Block.STATE_IDS.add(blockState);
            blockState.initShapeCache();
        }
        Block.STATE_IDS.forEach(AbstractBlock.AbstractBlockState::initShapeCache);
    }
}
