package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.client.AccessoriesCompatClient;

public class CobblemonBreathersClient {
    public static void init() {
        if (CobblemonBreathers.COMMON_PLATFORM.isModLoaded("accessories")) {
            AccessoriesCompatClient.init();
        }
    }
}
