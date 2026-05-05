package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.AccessoriesCompat;
import dev.matthiesen.common.cobblemon_breathers.config.ConfigManager;
import dev.matthiesen.common.cobblemon_breathers.config.ModConfig;
import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import dev.matthiesen.common.cobblemon_breathers.registry.CriterionTriggerRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;

import java.util.ServiceLoader;

@SuppressWarnings("CommentedOutCode")
public class CobblemonBreathers {
    public static ModConfig config;
    public static final CobblemonBreathersPlatform COMMON_PLATFORM = ServiceLoader.load(CobblemonBreathersPlatform.class).findFirst().orElseThrow();

    public static void initialize() {
        Constants.createInfoLog("Initialized");
        config = new ConfigManager().loadConfig();

        if (COMMON_PLATFORM.isModLoaded(AccessoriesCompat.ACCESSORIES_MOD_ID)) {
            AccessoriesCompat.init();
        }

        // Order is important... Don't move these (even if they are unused)
        // SoundRegistry.init();
        // StatsRegistry.init();
        // BlockRegistry.init();
        // BlockEntityRegistry.init();
        ItemRegistry.init();
        CriterionTriggerRegistry.init();
    }

    public static void onStartup() {
        Constants.createInfoLog("Server starting, Setting up");
    }

    public static void onShutdown() {
        Constants.createInfoLog("Server stopping, shutting down");
        new ConfigManager().saveConfig();
    }
}
