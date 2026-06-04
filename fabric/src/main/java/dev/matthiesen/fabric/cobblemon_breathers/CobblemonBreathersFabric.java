package dev.matthiesen.fabric.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import net.fabricmc.api.ModInitializer;

public class CobblemonBreathersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.createInfoLog("Loading for Fabric Mod Loader");
        CobblemonBreathers.loadConfig();
        CobblemonBreathers.initialize();
    }

}
