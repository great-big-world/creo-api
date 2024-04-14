package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.item.CreoItem;
import dev.creoii.creoapi.api.item.CreoItemSettings;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ItemTest implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("test", "test_item"), new Item(
                new CreoItemSettings()
                        .pickupDelay(25)
                        .despawnTime(3000)
                        .notBuoyant()
                        .gravity(0d)
                        .hopperTransferRate(1)
                        .requiredFuels(ItemTags.COALS)
        ));

        Registry.register(Registries.ITEM, new Identifier("test", "xray"), new XrayItem(new CreoItemSettings()));
    }

    private static class XrayItem extends Item implements CreoItem {
        public XrayItem(Settings settings) {
            super(settings);
        }

        @Override
        public void onAttackThroughBlock(ServerPlayerEntity player, ItemStack stack, Entity target) {
            Vec3d vec3d = player.getPos().add(0d, 1.600000023841858d, 0d);
            Vec3d vec3d2 = target.getEyePos().subtract(vec3d);
            for (int i = 0; i < MathHelper.floor(vec3d2.length()); ++i) {
                Vec3d vec3d4 = vec3d.add(vec3d2.normalize().multiply(i));
                player.getWorld().addParticle(ParticleTypes.SONIC_BOOM, vec3d4.x, vec3d4.y, vec3d4.z, 0d, 0d, 0d);
            }

            player.getWorld().playSoundFromEntity(target, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1f, 1f);
        }

        @Override
        public boolean canAttackThroughBlock(ServerPlayerEntity player, ItemStack stack, Entity target) {
            return true;
        }

        @Override
        public void onAttack(ServerPlayerEntity player, ItemStack stack, HitResult.Type type) {
            System.out.println(type);
        }
    }
}
