package dev.creoii.creoapi.impl.tag;

import com.mojang.datafixers.util.Function5;
import dev.creoii.creoapi.api.tag.CreoBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@ApiStatus.Internal
public final class BlockTagImpl {
    public static void applySignalFireBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.SIGNAL_FIRE_BASE_BLOCKS))
            cir.setReturnValue(true);
    }

    public static void applyCocoaBeansPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.COCOA_BEANS_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyCactusPlantableOn(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!world.getBlockState(pos.up()).getMaterial().isLiquid()) {
            if (state.isIn(CreoBlockTags.CACTUS_PLANTABLE_ON))
                cir.setReturnValue(true);
        }
    }

    public static void applyNetherWartPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.NETHER_WART_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static void applyKeepsFarmlandMoist(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isIn(CreoBlockTags.KEEPS_FARMLAND_MOIST))
            cir.setReturnValue(true);
    }

    public static void applyKeepsCoralAlive(BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.offset(direction)).isIn(CreoBlockTags.KEEPS_CORAL_ALIVE))
            cir.setReturnValue(true);
    }

    public static void applyAnvilSofteners(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.down()).isIn(CreoBlockTags.ANVIL_SOFTENERS))
            cir.setReturnValue(false);
    }

    public static boolean applyBeaconBeamIgnores(BlockState state) {
        return state.isIn(CreoBlockTags.BEACON_BEAM_IGNORES);
    }

    public static void applyConduitFrameBaseBlocks(BlockState state, BlockPos pos, List<BlockPos> activatingBlocks) {
        if (state.isIn(CreoBlockTags.CONDUIT_FRAME_BASE_BLOCKS)) {
            activatingBlocks.add(pos);
        }
    }

    public static void applyCanDripThrough(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.CAN_DRIP_THROUGH))
            cir.setReturnValue(true);
    }

    public static void applyInvalidForShulkerTeleport(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.INVALID_FOR_SHULKER_TELEPORT))
            cir.setReturnValue(true);
    }

    public static void applyRavagerBreakable(World world, BlockState state, BlockPos pos, RavagerEntity ravager, boolean bl) {
        if (state.isIn(CreoBlockTags.RAVAGER_BREAKABLE)) {
            bl = world.breakBlock(pos, true, ravager) || bl;
            ravager.setOnGround(false);
        }
    }

    public static void applyEatenBySheep(Runnable runnable) {
        runnable.run();
    }

    public static void applyShearsMineables(BlockState state, CallbackInfoReturnable<Float> cir) {
        if (state.isIn(CreoBlockTags.SHEARS_VERY_EFFICIENT))
            cir.setReturnValue(15f);
        else if (state.isIn(CreoBlockTags.SHEARS_LESS_EFFICIENT))
            cir.setReturnValue(2f);
    }

    public static void applyProjectilesIgnore(ProjectileEntity projectile, HitResult hitResult, CallbackInfo ci) {
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            if (projectile.getWorld().getBlockState(((BlockHitResult) hitResult).getBlockPos()).isIn(CreoBlockTags.PROJECTILES_IGNORE)) ci.cancel();
        }
    }

    public static Heightmap.Type[] addWeatherHeightmap(Heightmap.Type[] values, Function5<String, Integer, String, Heightmap.Purpose, Predicate<BlockState>, Heightmap.Type> constructor) {
        ArrayList<Heightmap.Type> types = new ArrayList<>(Arrays.asList(values));
        Heightmap.Type last = types.get(types.size() - 1);

        Heightmap.Type weather = constructor.apply("WEATHER", last.ordinal() + 1, "WEATHER", Heightmap.Purpose.CLIENT, BlockTagImpl::shouldWeatherIgnore);
        types.add(weather);

        return types.toArray(new Heightmap.Type[0]);
    }

    private static boolean shouldWeatherIgnore(BlockState state) {
        return !state.isIn(CreoBlockTags.WEATHER_RENDER_IGNORES) && (state.getMaterial().blocksMovement() || !state.getFluidState().isEmpty());
    }

    public static int applyWeatherRenderIgnores(World world, int x, int z) {
        return world.getTopY(Heightmap.Type.valueOf("WEATHER"), x, z);
    }
}
