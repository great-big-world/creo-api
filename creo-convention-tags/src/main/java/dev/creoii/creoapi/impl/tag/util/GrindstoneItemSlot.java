package dev.creoii.creoapi.impl.tag.util;

import dev.creoii.creoapi.api.tag.CreoEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class GrindstoneItemSlot extends Slot {
    private final GrindstoneScreenHandler screenHandler;

    public GrindstoneItemSlot(GrindstoneScreenHandler screenHandler) {
        super(screenHandler.result, 2, 129, 34);
        this.screenHandler = screenHandler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        screenHandler.context.run((world, pos) -> {
            if (world instanceof ServerWorld) {
                ExperienceOrbEntity.spawn((ServerWorld)world, Vec3d.ofCenter(pos), getExperience(world));
            }
            world.syncWorldEvent(WorldEvents.GRINDSTONE_USED, pos, 0);
        });
        screenHandler.input.setStack(0, ItemStack.EMPTY);
        screenHandler.input.setStack(1, ItemStack.EMPTY);
    }

    private int getExperience(World world) {
        int i = 0;
        i += this.getExperience(screenHandler.input.getStack(0));
        i += this.getExperience(screenHandler.input.getStack(1));
        if (i > 0) {
            int j = (int) Math.ceil((double) i / 2d);
            return j + world.random.nextInt(j);
        } else return 0;
    }

    private int getExperience(ItemStack stack) {
        int i = 0;
        ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
            Enchantment enchantment = entry.getKey().value();
            int j = entry.getIntValue();
            if (TagUtil.isIn(CreoEnchantmentTags.GRINDSTONE_IGNORES, enchantment))
                continue;
            i += enchantment.getMinPower(j);
        }
        return i;
    }
}
