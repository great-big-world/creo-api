package dev.creoii.creoapi.impl.event;

import dev.creoii.creoapi.api.event.block.BlockEvents;
import dev.creoii.creoapi.api.event.block.CropEvents;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
public final class BlockEventImpl {
    public static void applyBlockPlaceEvent(BlockState state, ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        boolean result = BlockEvents.PLACE.invoker().onPlace(state, context);

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

    public static void applyCropGrowEvent(World world, BlockPos pos, BlockState state, BlockState growState, int age, float moisture, CallbackInfoReturnable<Boolean> cir) {
        boolean result = CropEvents.GROW.invoker().onGrow(world, pos, state, growState, age, moisture);

        if (!result)
            cir.setReturnValue(false);
    }

    public static void applyCropGrowRandomEvent(World world, BlockPos pos, BlockState state, BlockState growState, int age, float moisture, CallbackInfo ci) {
        boolean result = CropEvents.GROW.invoker().onGrow(world, pos, state, growState, age, moisture);

        if (!result)
            ci.cancel();
    }
}
