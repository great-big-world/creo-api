package dev.creoii.creoapi.mixin.event.item;

import dev.creoii.creoapi.impl.event.BlockEventImpl;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow public abstract Block getBlock();

    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;placeFromNbt(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;"))
    private void creo_applyBlockPlaceEvent(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        BlockEventImpl.applyBlockPlaceEvent(getBlock(), context, cir);
    }
}
