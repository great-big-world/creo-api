package dev.creoii.creoapi.mixin.event.item;

import dev.creoii.creoapi.impl.event.MiscEventImpl;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;use(Lnet/minecraft/item/ItemStack;)I"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyFishingRodCastEvent(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, ItemStack itemStack) {
        MiscEventImpl.applyFishingRodCastEvent(world, user, hand, itemStack, cir);
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_applyFishingRodCatchEvent(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, ItemStack itemStack, int i, int j) {
        MiscEventImpl.applyFishingRodCatchEvent(world, user, hand, itemStack, i, j, cir);
    }
}
