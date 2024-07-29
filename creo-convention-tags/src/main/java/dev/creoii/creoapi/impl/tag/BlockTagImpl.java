package dev.creoii.creoapi.impl.tag;

import com.mojang.datafixers.util.Function5;
import dev.creoii.creoapi.api.tag.CreoBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
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

    public static boolean applyCactusPlantableOn(BlockState state) {
        return state.isIn(CreoBlockTags.CACTUS_PLANTABLE_ON);
    }

    public static void applyWitherRosePlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.isIn(CreoBlockTags.WITHER_ROSE_PLANTABLE_ON));
    }

    public static void applyNetherWartPlantableOn(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CreoBlockTags.NETHER_WART_PLANTABLE_ON))
            cir.setReturnValue(true);
    }

    public static boolean applyChorusPlantPlantableOn(BlockState state) {
        return state.isIn(CreoBlockTags.CHORUS_PLANT_PLANTABLE_ON);
    }

    public static void applyKeepsFarmlandMoist(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isIn(CreoBlockTags.KEEPS_FARMLAND_MOIST))
            cir.setReturnValue(true);
    }

    public static boolean applyKeepsFarmlandMoistOverwrite(WorldView world, BlockPos pos) {
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();

        for (int dz = -4; dz <= 4; ++dz) {
            int z = dz + posZ;
            for (int dx = -4; dx <= 4; ++dx) {
                int x = posX + dx;
                for (int dy = 0; dy <= 1; ++dy) {
                    Chunk chunk = world.getChunk(x >> 4, z >> 4);
                    BlockPos blockPos = new BlockPos(x, dy + posY, z);
                    FluidState fluid = chunk.getBlockState(blockPos).getFluidState();
                    if (fluid.isIn(FluidTags.WATER) || chunk.getBlockState(blockPos).isIn(CreoBlockTags.KEEPS_FARMLAND_MOIST)) {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public static void applyShearsMineables(CallbackInfoReturnable<ToolComponent> cir) {
        cir.setReturnValue(new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15f), ToolComponent.Rule.of(CreoBlockTags.SHEARS_VERY_EFFICIENT, 15f), ToolComponent.Rule.of(BlockTags.WOOL, 5f), ToolComponent.Rule.of(CreoBlockTags.SHEARS_LESS_EFFICIENT, 2f)), 1f, 1));
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

    @SuppressWarnings("deprecation")
    private static boolean shouldWeatherIgnore(BlockState state) {
        return !state.isIn(CreoBlockTags.WEATHER_RENDER_IGNORES) && (state.blocksMovement() || !state.getFluidState().isEmpty());
    }

    public static int applyWeatherRenderIgnores(World world, int x, int z) {
        return world.getTopY(Heightmap.Type.valueOf("WEATHER"), x, z);
    }

    public static void applyDoesNotClipAtLedge(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (player.getSteppingBlockState().isIn(CreoBlockTags.DOES_NOT_CLIP_AT_LEDGE)) {
            cir.setReturnValue(false);
        }
    }
}
