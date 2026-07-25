package dev.matthiesen.cobblemon_breathers.common.config;

import com.google.gson.annotations.SerializedName;

public final class BreathersClientConfig {
    @SerializedName("hudConfig")
    public HudConfig hudConfig = new HudConfig();

    public static class HudConfig {
        @SerializedName("disableInGameOverlay")
        public boolean disableInGameOverlay = false;
    }
}
