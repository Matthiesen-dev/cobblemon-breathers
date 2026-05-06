package dev.matthiesen.common.cobblemon_breathers.compat.accessories.client;

import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;

public class AccessoriesCompatClient {
    public static void init() {
        ItemRegistry.REBREATHERS.forEach(item ->
                AccessoriesRendererRegistry.registerRenderer(item.get(), ReBreatherRenderer::new));
    }
}
