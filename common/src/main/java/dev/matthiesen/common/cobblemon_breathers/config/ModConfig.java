package dev.matthiesen.common.cobblemon_breathers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class ModConfig {
    @SerializedName("reBreatherItemConfig")
    public ReBreatherItemConfig reBreatherItemConfig = new ReBreatherItemConfig();

    @SerializedName("breatherUpgradeEnchantConfig")
    public BreatherUpgradeEnchantConfig breatherUpgradeEnchantConfig = new BreatherUpgradeEnchantConfig();

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

    public static class ReBreatherItemConfig {
        @SerializedName("airSupplyRecovery")
        public int airSupplyRecovery = 50;

        @SerializedName("effectsConfig")
        public EffectControlConfig effectsConfig = new EffectControlConfig();

        @SerializedName("maxAirConfig")
        public ReBreatherMaxAirConfig maxAirConfig = new ReBreatherMaxAirConfig();
    }

    public static class ReBreatherMaxAirConfig {
        @SerializedName("mk1")
        public int mk1 = 300;

        @SerializedName("mk2")
        public int mk2 = 600;

        @SerializedName("mk3")
        public int mk3 = 1200;
    }

    public static class EffectControlConfig {
        @SerializedName("showAmbient")
        public boolean showAmbient = false;

        @SerializedName("visible")
        public boolean visible = false;
    }

    public static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
}
