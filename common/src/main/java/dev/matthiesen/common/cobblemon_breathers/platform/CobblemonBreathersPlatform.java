package dev.matthiesen.common.cobblemon_breathers.platform;

import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public interface CobblemonBreathersPlatform {
    void addItemRegistryCallback(Consumer<Item> consumer);
}
