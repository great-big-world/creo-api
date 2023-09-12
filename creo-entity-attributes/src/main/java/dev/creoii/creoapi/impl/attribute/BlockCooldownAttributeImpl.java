package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class BlockCooldownAttributeImpl {
    public static void addAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CreoEntityAttributes.PLAYER_BLOCK_PLACE_SPEED).add(CreoEntityAttributes.PLAYER_BLOCK_BREAK_SPEED);
    }

    public static void applyBlockPlaceSpeed(MinecraftClient client, ItemStack stack) {
        if (stack.getItem() instanceof BlockItem && client.player != null) {
            client.itemUseCooldown = (int) client.player.getAttributeValue(CreoEntityAttributes.PLAYER_BLOCK_PLACE_SPEED);
        }
    }

    public static int applyBlockBreakSpeed(MinecraftClient client) {
        if (client.player != null) {
            return (int) client.player.getAttributeValue(CreoEntityAttributes.PLAYER_BLOCK_BREAK_SPEED);
        }
        return 5;
    }
}
