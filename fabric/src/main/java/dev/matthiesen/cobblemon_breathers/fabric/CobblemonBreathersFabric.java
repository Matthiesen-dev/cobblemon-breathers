package dev.matthiesen.cobblemon_breathers.fabric;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import net.fabricmc.api.ModInitializer;

public final class CobblemonBreathersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        var instance = CobblemonBreathers.INSTANCE;
        instance.createInfoLog("Loading for Fabric Mod Loader");
        instance.initialize();
    }
}
