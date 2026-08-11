package dev.matthiesen.cobblemon_breathers.common.compat.accessories.client;

import dev.matthiesen.cobblemon_breathers.common.registry.ItemRegistry;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;

public final class AccessoriesCompatClient {
    public static void init() {
        ItemRegistry.REBREATHERS.forEach(item ->
                AccessoriesRendererRegistry.registerRenderer(item.get(), ReBreatherRenderer::new));
    }
}
