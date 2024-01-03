package dev.creoii.creoapi.mixin.tag.item;

import dev.creoii.creoapi.impl.tag.ItemTagImpl;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {
    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void creo_applyMaterialIngredientTags(CallbackInfo ci) {
        ItemTagImpl.applyToolRepairIngredients();
    }
}
