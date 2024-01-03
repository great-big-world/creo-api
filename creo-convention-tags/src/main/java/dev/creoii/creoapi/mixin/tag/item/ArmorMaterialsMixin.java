package dev.creoii.creoapi.mixin.tag.item;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {
    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void creo_applyMaterialIngredientTags(CallbackInfo ci) {
        ItemTagImpl.applyArmorRepairIngredients();
    }
}
