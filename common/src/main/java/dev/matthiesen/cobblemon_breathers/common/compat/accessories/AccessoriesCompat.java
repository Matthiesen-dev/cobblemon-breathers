package dev.matthiesen.cobblemon_breathers.common.compat.accessories;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.cobblemon_breathers.common.item.ReBreatherItem;
import io.wispforest.accessories.api.AccessoriesAPI;

public class AccessoriesCompat {
    public static void init() {
        CobblemonBreathers.INSTANCE.getCommonRegistry().registerItemRegistryCallback(item -> {
            if (item instanceof ReBreatherItem breatherItem)
                AccessoriesAPI.registerAccessory(item, new ReBreatherItemAccessory<>(breatherItem));
        });
    }
}
