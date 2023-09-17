package dev.creoii.creoapi.mixin.item;

import dev.creoii.creoapi.impl.item.AccessibleItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin implements AccessibleItem {
    @Unique private Item.Settings creo_settings;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo_accessItemSettings(Item.Settings settings, CallbackInfo ci) {
        this.creo_settings = settings;
    }

    @Override
    public Item.Settings creo_getItemSettings() {
        return creo_settings;
    }
}