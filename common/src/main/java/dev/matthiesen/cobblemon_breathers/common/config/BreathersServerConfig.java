package dev.matthiesen.cobblemon_breathers.common.config;

import com.google.gson.annotations.SerializedName;

public final class BreathersServerConfig {
    @SerializedName("airSupplyRecovery")
    public int airSupplyRecovery = 50;

    @SerializedName("maxAirConfig")
    public ReBreatherMaxAirConfig maxAirConfig = new ReBreatherMaxAirConfig();

    @SerializedName("upgradeEnchantConfig")
    public BreatherUpgradeEnchantConfig upgradeEnchantConfig = new BreatherUpgradeEnchantConfig();

    @SerializedName("effectControlConfig")
    public EffectControlConfig effectControlConfig = new EffectControlConfig();

    public static class ReBreatherMaxAirConfig {
        @SerializedName("mk1")
        public int mk1 = 300;

        @SerializedName("mk2")
        public int mk2 = 600;

        @SerializedName("mk3")
        public int mk3 = 1200;
    }

    public static class BreatherUpgradeEnchantConfig {
        @SerializedName("disableEnchantmentEffect")
        public boolean disableEnchantmentEffect = false;

        @SerializedName("levelOneAirAddition")
        public int levelOneAirAddition = 200;

        @SerializedName("levelTwoAirAddition")
        public int levelTwoAirAddition = 800;

        @SerializedName("levelThreeAirAddition")
        public int levelThreeAirAddition = 2000;
    }

    public static class EffectControlConfig {
        @SerializedName("showAmbient")
        public boolean showAmbient = false;

        @SerializedName("visible")
        public boolean visible = false;
    }
}
