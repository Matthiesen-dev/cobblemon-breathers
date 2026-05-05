package dev.matthiesen.common.cobblemon_breathers.compat.accessories.client;

import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;

public class AccessoriesCompatClient {
    public static void init() {
        AccessoriesRendererRegistry.registerNoRenderer(ItemRegistry.POKE_BREATHER.get());
    }
}
