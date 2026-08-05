package dev.matthiesen.cobblemon_breathers.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ServerConfig {

    public ModConfigSpec.IntValue airSupplyRecovery;

    public ModConfigSpec.IntValue maxAir_mk1;
    public ModConfigSpec.IntValue maxAir_mk2;
    public ModConfigSpec.IntValue maxAir_mk3;

    public ModConfigSpec.BooleanValue effects_showAmbient;
    public ModConfigSpec.BooleanValue effects_visible;

    public ModConfigSpec.BooleanValue enchants_disableEffects;
    public ModConfigSpec.IntValue enchants_levelOneAirAddition;
    public ModConfigSpec.IntValue enchants_levelTwoAirAddition;
    public ModConfigSpec.IntValue enchants_levelThreeAirAddition;

    public ServerConfig(ModConfigSpec.Builder builder) {
        builder.comment("General Configuration").push("generalConfig");
        airSupplyRecovery = builder.comment("How much air supply is recovered per tick when the player is not underwater.")
                .defineInRange("airSupplyRecovery", 50, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Max Air Configuration").push("maxAirConfig");
        maxAir_mk1 = builder.comment("The maximum air supply for a Mk1 ReBreather.")
                .defineInRange("mk1", 300, 1, Integer.MAX_VALUE);
        maxAir_mk2 = builder.comment("The maximum air supply for a Mk2 ReBreather.")
                .defineInRange("mk2", 600, 1, Integer.MAX_VALUE);
        maxAir_mk3 = builder.comment("The maximum air supply for a Mk3 ReBreather.")
                .defineInRange("mk3", 1200, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Effect Control Configuration").push("effectControlConfig");
        effects_showAmbient = builder.comment("Whether the ReBreather effect should show ambient particles.")
                .define("showAmbient", false);
        effects_visible = builder.comment("Whether the ReBreather effect should be visible.")
                .define("visible", false);
        builder.pop();

        builder.comment("Breather Upgrade Enchantment Configuration").push("upgradeEnchantConfig");
        enchants_disableEffects = builder.comment("Whether the ReBreather Upgrade enchantment should disable the ReBreather effect.")
                .define("disableEnchantmentEffect", false);
        enchants_levelOneAirAddition = builder.comment("How much air supply is added to the ReBreather when the ReBreather Upgrade enchantment is at level 1.")
                .defineInRange("levelOneAirAddition", 200, 1, Integer.MAX_VALUE);
        enchants_levelTwoAirAddition = builder.comment("How much air supply is added to the ReBreather when the ReBreather Upgrade enchantment is at level 2.")
                .defineInRange("levelTwoAirAddition", 800, 1, Integer.MAX_VALUE);
        enchants_levelThreeAirAddition = builder.comment("How much air supply is added to the ReBreather when the ReBreather Upgrade enchantment is at level 3.")
                .defineInRange("levelThreeAirAddition", 2000, 1, Integer.MAX_VALUE);
        builder.pop();
    }
}
