package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.block.BlockEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class BlockEventImpl {
    public static void applyBlockPlaceEvent(Block block, ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        boolean result = BlockEvents.PLACE.invoker().onPlace(block, context);

        if (!result)
            cir.setReturnValue(ActionResult.PASS);
    }

    public static void applyBlockBreakEvent(ServerWorld world, ServerPlayerEntity player, BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        boolean result = BlockEvents.BREAK.invoker().onBreak(world, player, state, pos);

        if (!result)
            cir.setReturnValue(false);
    }

    public static void applyBlockChangeEvent(World world, BlockPos pos, BlockState newState, BlockState oldState, boolean moved, CallbackInfoReturnable<BlockState> cir) {
        boolean result = BlockEvents.CHANGE.invoker().onChange(world, pos, newState, oldState, moved);

        if (!result)
            cir.setReturnValue(oldState);
    }
}
