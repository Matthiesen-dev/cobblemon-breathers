package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemRegistry {
    public static void init() {}

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return CobblemonBreathers.COMMON_PLATFORM.registerItem(id, item);
    }
}
