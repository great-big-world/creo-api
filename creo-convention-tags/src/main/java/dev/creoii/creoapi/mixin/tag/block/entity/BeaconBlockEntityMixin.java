package dev.creoii.creoapi.mixin.tag.block.entity;

import dev.creoii.creoapi.impl.tag.BlockTagImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean creo_applyBeaconBeamIgnores(BlockState instance, Block block) {
        return BlockTagImpl.applyBeaconBeamIgnores(instance);
    }
}
