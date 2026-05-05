package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.AccessoriesCompat;
import dev.matthiesen.common.cobblemon_breathers.compat.accessories.client.AccessoriesCompatClient;

public class CobblemonBreathersClient {
    public static void init() {
        if (CobblemonBreathers.COMMON_PLATFORM.isModLoaded(AccessoriesCompat.ACCESSORIES_MOD_ID)) {
            AccessoriesCompatClient.init();
        }
    }
}
