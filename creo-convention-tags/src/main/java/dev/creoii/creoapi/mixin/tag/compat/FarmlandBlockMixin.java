package dev.creoii.creoapi.mixin.tag.compat;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
    /**
     * @author creoii
     * @reason To keep functionality when Potatoptimize is installed. Mixin into {@link BlockTagImpl} to modify functionality.
     */
    @Overwrite
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        return BlockTagImpl.applyKeepsFarmlandMoistOverwrite(world, pos);
    }
}
