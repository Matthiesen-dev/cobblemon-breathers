package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.AccessoriesCompat;
import dev.matthiesen.common.cobblemon_breathers.config.ConfigManager;
import dev.matthiesen.common.cobblemon_breathers.config.ModConfig;
import dev.matthiesen.common.cobblemon_breathers.registry.EnchantmentEffectsRegistry;
import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;

import java.util.ServiceLoader;

public class CobblemonBreathers {
    public static ModConfig config;
    public static final CobblemonBreathersPlatform COMMON_PLATFORM = ServiceLoader.load(CobblemonBreathersPlatform.class).findFirst().orElseThrow();

    public static void loadConfig() {
        config = new ConfigManager().loadConfig();
        Constants.createInfoLog("Initialized Config");
    }

    public static void initialize() {
        Constants.createInfoLog("Initializing Cobblemon Breathers");
        ComponentTypesRegistry.init();
        ItemRegistry.init();
        EnchantmentEffectsRegistry.init();

        if (COMMON_PLATFORM.isModLoaded("accessories")) {
            Constants.createInfoLog("Accessories mod detected, initializing compatibility");
            AccessoriesCompat.init();
        }
    }

    public static void onStartup() {
        Constants.createInfoLog("Server starting, Setting up");
    }

    public static void onShutdown() {
        Constants.createInfoLog("Server stopping, shutting down");
        new ConfigManager().saveConfig();
    }
}
