package dev.matthiesen.fabric.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class CobblemonBreathersFabricPlatform implements CobblemonBreathersPlatform {
    @Override
    public void addItemRegistryCallback(Consumer<Item> consumer) {
        RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM)
                .register((i, resourceLocation, item) -> consumer.accept(item));
    }
}
