package dev.matthiesen.common.cobblemon_breathers.config;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.matthiesen_lib_api.config.ConfigManager;

public class BreathersConfigManager<T> extends ConfigManager<T> {
    public BreathersConfigManager(Class<T> configClass, String configName) {
        super(configClass, configName, Constants.MOD_ID);
    }
}
