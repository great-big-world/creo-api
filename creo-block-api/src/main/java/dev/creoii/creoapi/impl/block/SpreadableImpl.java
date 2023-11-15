package dev.creoii.creoapi.impl.block;

import dev.creoii.creoapi.api.block.Spreadable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@ApiStatus.Internal
public final class SpreadableImpl {
    public static void applySpreadables(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.getBlock() instanceof Spreadable spreadable) {
            if (!spreadable.canSurvive(state, world, pos, random)) {
                world.setBlockState(pos, spreadable.getDead().getDefaultState());
                return;
            } else if (spreadable.canSpread(state, world, pos, random)) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos testPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    Block test = getConversion(spreadable.getSpreads(), world.getBlockState(testPos).getBlock(), state.getBlock());
                    if (test != null && spreadable.canSpread(state, world, pos, random) && spreadable.canSurvive(state, world, testPos, random)) {
                        if (!world.setBlockState(testPos, test.getDefaultState()))
                            continue;
                        spreadable.onSpread(state, world, pos, random);
                    }
                }
            }
            ci.cancel();
        }
    }

    @Nullable
    private static Block getConversion(List<Spreadable.Spread> spreads, Block block, Block defaultBlock) {
        for (Spreadable.Spread spread : spreads) {
            if (spread.target() == block) {
                if (spread.conversion() == null) {
                    return defaultBlock;
                } else return spread.conversion();
            }
        }
        return null;
    }
}
