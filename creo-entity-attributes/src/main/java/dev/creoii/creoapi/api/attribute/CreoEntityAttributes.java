package dev.creoii.creoapi.api.attribute;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CreoEntityAttributes implements ModInitializer {
    public static final String NAMESPACE = "creo";

    /**
     * Controls the amount y velocity is decreased over time.
     */
    public static final EntityAttribute GENERIC_GRAVITY = new ClampedEntityAttribute("attribute.name.generic.gravity", .08d, -16d, 16d).setTracked(true);

    @Override
    public void onInitialize() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoEntityAttributes.NAMESPACE, "generic.gravity"), GENERIC_GRAVITY);
    }
}
