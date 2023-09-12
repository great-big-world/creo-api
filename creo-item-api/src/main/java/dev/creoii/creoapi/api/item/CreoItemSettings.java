package dev.creoii.creoapi.api.item;

import dev.creoii.creoapi.mixin.item.ItemSettingsAccessor;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;

public class CreoItemSettings extends FabricItemSettings {
    private int pickupDelay = 10;
    private int despawnTime = 6000;
    private boolean buoyant = true;
    private double gravity = -.04d;
    private Item[] requiredFuels;

    public static CreoItemSettings copyOf(Item.Settings settings) {
        ItemSettingsAccessor accessor = (ItemSettingsAccessor) settings;
        CreoItemSettings copy = new CreoItemSettings();

        ((ItemSettingsAccessor) copy).setMaxCount(accessor.getMaxCount());
        ((ItemSettingsAccessor) copy).setMaxDamage(accessor.getMaxDamage());
        ((ItemSettingsAccessor) copy).setRecipeRemainder(accessor.getRecipeRemainder());
        ((ItemSettingsAccessor) copy).setRarity(accessor.getRarity());
        ((ItemSettingsAccessor) copy).setFoodComponent(accessor.getFoodComponent());
        if (accessor.isFireproof()) {
            ((ItemSettingsAccessor) copy).setFireproof(true);
        }
        return copy;
    }

    public static CreoItemSettings copyOf(CreoItemSettings settings) {
        ItemSettingsAccessor accessor = (ItemSettingsAccessor) settings;
        CreoItemSettings copy = new CreoItemSettings();
        ((ItemSettingsAccessor) copy).setMaxCount(accessor.getMaxCount());
        ((ItemSettingsAccessor) copy).setMaxDamage(accessor.getMaxDamage());
        ((ItemSettingsAccessor) copy).setRecipeRemainder(accessor.getRecipeRemainder());
        ((ItemSettingsAccessor) copy).setRarity(accessor.getRarity());
        ((ItemSettingsAccessor) copy).setFoodComponent(accessor.getFoodComponent());
        if (accessor.isFireproof()) {
            ((ItemSettingsAccessor) copy).setFireproof(true);
        }

        copy.setPickupDelay(settings.getPickupDelay());
        copy.setDespawnTime(settings.getDespawnTime());
        copy.setBuoyant(settings.isBuoyant());
        copy.setGravity(settings.getGravity());
        copy.setRequiredFuels(settings.getRequiredFuels());
        return copy;
    }

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
    public CreoItemSettings food(FoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    @Override
    public CreoItemSettings maxCount(int maxCount) {
        super.maxCount(maxCount);
        return this;
    }

    @Override
    public CreoItemSettings maxDamageIfAbsent(int maxDamage) {
        super.maxDamageIfAbsent(maxDamage);
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
        this.buoyant = false;
        return this;
    }

    public CreoItemSettings gravity(double gravity) {
        this.gravity = gravity;
        return this;
    }

    public CreoItemSettings requiredFuels(Item... requiredFuels) {
        this.requiredFuels = requiredFuels;
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

    public Item[] getRequiredFuels() {
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

    public void setRequiredFuels(Item[] requiredFuels) {
        this.requiredFuels = requiredFuels;
    }
}
