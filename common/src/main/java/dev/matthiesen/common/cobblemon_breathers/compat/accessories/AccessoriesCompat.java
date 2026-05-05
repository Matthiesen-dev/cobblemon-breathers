package dev.matthiesen.common.cobblemon_breathers.compat.accessories;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.item.AbstractReBreatherItem;
import io.wispforest.accessories.api.AccessoriesAPI;

public class AccessoriesCompat {
    public static final String ACCESSORIES_MOD_ID = "accessories";

    public static void init() {
        CobblemonBreathers.COMMON_PLATFORM.addItemRegistryCallback(item -> {
            if (item instanceof AbstractReBreatherItem breatherItem) {
                AccessoriesAPI.registerAccessory(item, new ReBreatherItemAccessory<>(breatherItem));
            }
        });
    }
}
