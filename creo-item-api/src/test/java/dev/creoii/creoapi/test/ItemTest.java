package dev.creoii.creoapi.test;

import dev.creoii.creoapi.api.item.CreoFoodComponent;
import dev.creoii.creoapi.api.item.CreoItem;
import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.creoapi.api.item.ItemEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ItemTest implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, Identifier.of("test", "test_item"), new Item(
                new CreoItemSettings()
                        .pickupDelay(25)
                        .despawnTime(2000)
                        .notBuoyant()
                        .gravity(0d)
                        .hopperTransferRate(1)
                        .rotationModifier(0f)
                        .noHoverAnimation()
                        .clickPickup()
                        .requiredFuels(ItemTags.COALS)
        ));
        Registry.register(Registries.ITEM, Identifier.of("test", "xray"), new XrayItem(new CreoItemSettings()));

        ItemEvents.CLICK_PICKUP.register((itemEntity, player) -> {
            System.out.println("clickpickup event: " + player.getWorld().isClient);
            System.out.println(itemEntity.getStack().getTranslationKey());
        });
        ItemEvents.ATTACK_THROUGH_BLOCK.register((player, stack, target) -> {
            System.out.println("attackthru event: " + player.getWorld().isClient);
            System.out.println(stack.getTranslationKey() + " on " + target.getType().getTranslationKey());
        });

        Item slowFood = new Item(new CreoItemSettings().food(new CreoFoodComponent.Builder().nutrition(2).eatSeconds(3).canAlwaysEat().build()));
        Item fastFood = new Item(new CreoItemSettings().food(new CreoFoodComponent.Builder().eatSeconds(.4f).canAlwaysEat().build()));
        Item sprintEdibleFood = new Item(new CreoItemSettings().food(new CreoFoodComponent.Builder().canAlwaysEat().canSprintEat().build()));
        Item healsHealthFood = new Item(new CreoItemSettings().food(new CreoFoodComponent.Builder().nutrition(2).canAlwaysEat().healsHealth().build()));

        Registry.register(Registries.ITEM, Identifier.of("test", "slow_food"), slowFood);
        Registry.register(Registries.ITEM, Identifier.of("test", "fast_food"), fastFood);
        Registry.register(Registries.ITEM, Identifier.of("test", "sprint_food"), sprintEdibleFood);
        Registry.register(Registries.ITEM, Identifier.of("test", "heals_health"), healsHealthFood);
    }

    private static class XrayItem extends Item implements CreoItem {
        public XrayItem(Settings settings) {
            super(settings);
        }

        @Override
        public void onAttackThroughBlock(PlayerEntity player, ItemStack stack, Entity target) {
            Vec3d vec3d = player.getPos().add(0d, 1.600000023841858d, 0d);
            Vec3d vec3d2 = target.getEyePos().subtract(vec3d);
            for (int i = 0; i < MathHelper.floor(vec3d2.length()); ++i) {
                Vec3d vec3d4 = vec3d.add(vec3d2.normalize().multiply(i));
                player.getWorld().addParticle(ParticleTypes.SONIC_BOOM, vec3d4.x, vec3d4.y, vec3d4.z, 0d, 0d, 0d);
            }

            player.getWorld().playSoundFromEntity(target, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1f, 1f);
            System.out.println("on attack thru: " + player.getWorld().isClient);
        }

        @Override
        public boolean canAttackThroughBlock(PlayerEntity player, ItemStack stack, Entity target) {
            System.out.println("can attack thru: " + player.getWorld().isClient);
            return true;
        }

        @Override
        public void onAttack(PlayerEntity player, ItemStack stack, HitResult.Type type, Vec3d pos) {
            System.out.println("on attack: " + player.getWorld().isClient + " | " + type + " | " + pos.toString());
        }
    }
}
