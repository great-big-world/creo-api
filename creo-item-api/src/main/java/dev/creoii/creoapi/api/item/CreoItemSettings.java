package dev.creoii.creoapi.api.item;

import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;

import java.util.LinkedList;
import java.util.List;

public class CreoItemSettings extends Item.Settings {
    private int pickupDelay = 10;
    private int despawnTime = 6000;
    private boolean buoyant = true;
    private double gravity = .04d;
    private int hopperTransferRate = 8;
    private float rotationModifier = 1f;
    private boolean hoverAnimation = true;
    private boolean clickPickup = false;
    private RegistryEntryList<Item> requiredFuels;

    @Override
    public CreoItemSettings equipmentSlot(EquipmentSlotProvider equipmentSlotProvider) {
        super.equipmentSlot(equipmentSlotProvider);
        return this;
    }

    @Override
    public CreoItemSettings customDamage(CustomDamageHandler handler) {
        super.customDamage(handler);
        return this;
    }

    @Override
    public Item.Settings food(FoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    public Item.Settings food(CreoFoodComponent foodComponent) {
        component(CreoDataComponentTypes.FOOD, foodComponent);
        return this;
    }

    @Override
    public CreoItemSettings maxCount(int maxCount) {
        super.maxCount(maxCount);
        return this;
    }

    @Override
    public CreoItemSettings maxDamage(int maxDamage) {
        super.maxDamage(maxDamage);
        return this;
    }

    @Override
    public CreoItemSettings recipeRemainder(Item recipeRemainder) {
        super.recipeRemainder(recipeRemainder);
        return this;
    }

    @Override
    public CreoItemSettings rarity(Rarity rarity) {
        super.rarity(rarity);
        return this;
    }

    @Override
    public CreoItemSettings fireproof() {
        super.fireproof();
        return this;
    }

    @Override
    public CreoItemSettings requires(FeatureFlag... features) {
        super.requires(features);
        return this;
    }

    @Override
    public Item.Settings attributeModifiers(AttributeModifiersComponent attributeModifiersComponent) {
        super.attributeModifiers(attributeModifiersComponent);
        return this;
    }

    public CreoItemSettings pickupDelay(int delay) {
        pickupDelay = delay;
        return this;
    }

    public CreoItemSettings cannotPickup() {
        pickupDelay = 32767;
        return this;
    }

    public CreoItemSettings despawnTime(int despawnTime) {
        this.despawnTime = despawnTime;
        return this;
    }

    public CreoItemSettings cannotDespawn() {
        despawnTime = -32768;
        return this;
    }

    public CreoItemSettings notBuoyant() {
        buoyant = false;
        return this;
    }

    public CreoItemSettings gravity(double gravity) {
        this.gravity = gravity;
        return this;
    }

    public CreoItemSettings hopperTransferRate(int hopperTransferRate) {
        this.hopperTransferRate = hopperTransferRate;
        return this;
    }

    /**
     * @since 0.2.3
     */
    public CreoItemSettings rotationModifier(float rotationModifier) {
        this.rotationModifier = rotationModifier;
        return this;
    }

    public CreoItemSettings noHoverAnimation() {
        hoverAnimation = false;
        return this;
    }

    /**
     * @since 0.2.3
     */
    public CreoItemSettings clickPickup() {
        clickPickup = true;
        return this;
    }

    public CreoItemSettings requiredFuels(RegistryEntryList<Item> requiredFuels) {
        this.requiredFuels = requiredFuels;
        return this;
    }

    @SuppressWarnings("deprecation")
    public CreoItemSettings requiredFuels(TagKey<Item> requiredFuels) {
        this.requiredFuels = RegistryEntryList.of(Registries.ITEM.getEntryOwner(), requiredFuels);
        return this;
    }

    public CreoItemSettings requiredFuels(Item[] requiredFuels) {
        List<RegistryEntry<Item>> entries = new LinkedList<>();
        for (Item item : requiredFuels) {
            entries.add(item.getDefaultStack().getRegistryEntry());
        }
        this.requiredFuels = RegistryEntryList.of(entries);
        return this;
    }

    public int getPickupDelay() {
        return pickupDelay;
    }

    public int getDespawnTime() {
        return despawnTime;
    }

    public boolean isBuoyant() {
        return buoyant;
    }

    public double getGravity() {
        return gravity;
    }

    public int getHopperTransferRate() {
        return hopperTransferRate;
    }

    public float getRotationModifier() {
        return rotationModifier;
    }

    public boolean hasHoverAnimation() {
        return hoverAnimation;
    }

    public boolean doesClickPickup() {
        return clickPickup;
    }

    public RegistryEntryList<Item> getRequiredFuels() {
        return requiredFuels;
    }

    public void setPickupDelay(int pickupDelay) {
        this.pickupDelay = pickupDelay;
    }

    public void setDespawnTime(int despawnTime) {
        this.despawnTime = despawnTime;
    }

    public void setBuoyant(boolean buoyant) {
        this.buoyant = buoyant;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setHopperTransferRate(int hopperTransferRate) {
        this.hopperTransferRate = hopperTransferRate;
    }

    public void setRotationModifier(float rotationModifier) {
        this.rotationModifier = rotationModifier;
    }

    public void setHoverAnimation(boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
    }

    public void setClickPickup(boolean clickPickup) {
        this.clickPickup = clickPickup;
    }

    public void setRequiredFuels(RegistryEntryList<Item> requiredFuels) {
        this.requiredFuels = requiredFuels;
    }
}
