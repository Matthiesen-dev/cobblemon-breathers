package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.client.AccessoriesCompatClient;

public class CobblemonBreathersClient {
    public static void init() {
        Constants.createInfoLog("Initializing client...");

        if (CobblemonBreathers.COMMON_PLATFORM.isModLoaded("accessories")) {
            Constants.createInfoLog("Accessories mod detected, initializing client compatibility...");
            AccessoriesCompatClient.init();
        }
    }
}
