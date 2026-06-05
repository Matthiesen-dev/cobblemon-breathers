package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.AccessoriesCompat;
import dev.matthiesen.common.cobblemon_breathers.config.BreathersConfigManager;
import dev.matthiesen.common.cobblemon_breathers.config.ModConfig;
import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import dev.matthiesen.common.cobblemon_breathers.registry.CreativeModTabsRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.EnchantmentEffectsRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import dev.matthiesen.common.cobblemon_breathers.util.MetricManager;
import dev.matthiesen.common.matthiesen_lib.MatthiesenLib;

import java.util.ServiceLoader;

public class CobblemonBreathers {
    private static final BreathersConfigManager<ModConfig> CONFIG_MANAGER =
            new BreathersConfigManager<>(ModConfig.class, "config");

    public static final CobblemonBreathersPlatform COMMON_PLATFORM =
            ServiceLoader.load(CobblemonBreathersPlatform.class).findFirst().orElseThrow();

    public static void loadConfig() {
        CONFIG_MANAGER.loadConfig();
        Constants.createInfoLog("Initialized Config");
    }

    public static void initialize() {
        Constants.createInfoLog("Initializing Cobblemon Breathers");
        MetricManager.init();

        ComponentTypesRegistry.init();
        ItemRegistry.init();
        CreativeModTabsRegistry.init();
        EnchantmentEffectsRegistry.init();

        if (MatthiesenLib.isModLoaded("accessories")) {
            Constants.createInfoLog("Accessories mod detected, initializing compatibility");
            AccessoriesCompat.init();
        }
    }

    public static ModConfig getConfig() {
        return CONFIG_MANAGER.getConfig();
    }
}
