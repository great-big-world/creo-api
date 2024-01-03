package dev.creoii.creoapi.mixin.event.block;

import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {
    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;removeBlock(Lnet/minecraft/util/math/BlockPos;Z)Z", ordinal = 0), cancellable = true)
    private void creo_applySleepExplodeEvent(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        MiscEventImpl.applySleepExplodeEvent(state, world, pos, player, hand, hit, cir);
    }

    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BedBlock;wakeVillager(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"), cancellable = true)
    private void creo_applySleepWakeVillagerEvent(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        MiscEventImpl.applySleepWakeVillagerEvent(state, world, pos, player, hand, hit, cir);
    }
}
