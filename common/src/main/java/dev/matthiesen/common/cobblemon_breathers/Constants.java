package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.util.MetricManager;
import dev.matthiesen.libs.faststats.Token;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class Constants {
    public static final String MOD_ID = "cobblemon_breathers";
    public static final String ModName = "Cobblemon Breathers";
    public static @Token final String METRICS_TOKEN = "24049a71a4b10ce2eabba1a736f9f1cc";
    public static final int AIR_SUPPLY_BAR_COLOR = 0x00BFFF;

    public static ResourceLocation modResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static Logger LOGGER = LogManager.getLogger(ModName);

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message) {
        LOGGER.error(message);
    }

    public static void createErrorLog(String message, Throwable throwable) {
        MetricManager.ERROR_TRACKER.trackError(throwable);
        LOGGER.error(message, throwable);
    }
}
