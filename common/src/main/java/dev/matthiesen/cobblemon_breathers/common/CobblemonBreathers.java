package dev.matthiesen.cobblemon_breathers.common;

import dev.matthiesen.cobblemon_breathers.common.compat.accessories.AccessoriesCompat;
import dev.matthiesen.cobblemon_breathers.common.config.BreathersServerConfig;
import dev.matthiesen.cobblemon_breathers.common.registry.ComponentTypesRegistry;
import dev.matthiesen.cobblemon_breathers.common.registry.CreativeModeTabsRegistry;
import dev.matthiesen.cobblemon_breathers.common.registry.EnchantmentEffectsRegistry;
import dev.matthiesen.cobblemon_breathers.common.registry.ItemRegistry;
import dev.matthiesen.libs.faststats.Token;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.utility.config.ConfigManager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class CobblemonBreathers extends AbstractCommonMod {
    public static final String MOD_ID = "cobblemon_breathers";
    public static final String MOD_NAME = "Cobblemon Breathers";
    public static @Token final String METRICS_TOKEN = "24049a71a4b10ce2eabba1a736f9f1cc";
    public static final int AIR_SUPPLY_BAR_COLOR = 0x00BFFF;

    public static final CobblemonBreathers INSTANCE = new CobblemonBreathers();

    private static final ConfigManager<BreathersServerConfig> SERVER_CONFIG_MANAGER =
            new ConfigManager<>(BreathersServerConfig.class, "server", MOD_ID);

    public static ResourceLocation modResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private CobblemonBreathers() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public @NotNull @Token String getMetricsToken() {
        return METRICS_TOKEN;
    }

    @Override
    public void initialize() {
        super.initialize();
        SERVER_CONFIG_MANAGER.loadConfig();

        ComponentTypesRegistry.init();
        ItemRegistry.init();
        CreativeModeTabsRegistry.init();
        EnchantmentEffectsRegistry.init();

        if (getCommonUtils().isModLoaded("accessories")) {
            createInfoLog("Accessories mod detected, initializing compatibility");
            AccessoriesCompat.init();
        }

        createInfoLog("Initialized Cobblemon Breathers");
    }

    public BreathersServerConfig getServerConfig() {
        return SERVER_CONFIG_MANAGER.getConfig();
    }
}
