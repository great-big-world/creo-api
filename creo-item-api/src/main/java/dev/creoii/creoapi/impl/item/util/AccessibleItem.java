package dev.creoii.creoapi.impl.item.util;

import net.minecraft.item.Item;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface AccessibleItem {
    Item.Settings creo_getItemSettings();
}
