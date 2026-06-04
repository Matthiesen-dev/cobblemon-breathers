package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.callback.AddCallback;

import java.util.function.Consumer;

public class CobblemonBreathersNeoForgePlatform implements CobblemonBreathersPlatform {
    @Override
    public void addItemRegistryCallback(Consumer<Item> consumer) {
        BuiltInRegistries.ITEM.addCallback((AddCallback<Item>) (registry, i, key, item) -> consumer.accept(item));
    }
}
