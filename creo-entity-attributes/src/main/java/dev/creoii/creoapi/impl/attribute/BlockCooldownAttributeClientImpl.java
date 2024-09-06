package dev.creoii.creoapi.impl.attribute;

import dev.creoii.creoapi.api.attribute.CreoEntityAttributes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public final class BlockCooldownAttributeClientImpl {
    public static void applyBlockPlaceCooldown(MinecraftClient client, ItemStack stack) {
        if (stack.getItem() instanceof BlockItem && client.player != null) {
            client.itemUseCooldown = (int) client.player.getAttributeValue(CreoEntityAttributes.PLACE_COOLDOWN);
        }
    }

    public static int applyBlockBreakCooldown(MinecraftClient client) {
        if (client.player != null) {
            return (int) client.player.getAttributeValue(CreoEntityAttributes.BREAK_COOLDOWN);
        }
        return 5;
    }
}
