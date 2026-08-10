package dev.matthiesen.cobblemon_breathers.common.datagen;

import java.util.HashMap;
import java.util.Map;

public final class TranslationsRegistry {
    public static final Map<String, String> EN_TRANSLATIONS = new HashMap<>();

    static {
        // Creative Tab Groups
        EN_TRANSLATIONS.put(newItemGroup("cobblemon_breathers"), "Cobblemon Breathers");

        // Air Supply Labels
        EN_TRANSLATIONS.put(newAirSupply("current_air"), "Current Air Supply: %s/%s seconds");
        EN_TRANSLATIONS.put(newAirSupply("supply_low"), "Air Supply low! You should find air soon!");
        EN_TRANSLATIONS.put(newAirSupply("supply_depleted"), "Air Supply depleted! You are suffocating!");
        EN_TRANSLATIONS.put(newAirSupply("hud_display"), "%s seconds");

        // Enchantment Labels
        EN_TRANSLATIONS.put(newEnchantment("breather_upgrade"), "Breather Upgrade");

        // In-game configuration screen
        EN_TRANSLATIONS.put(newConfiguration("title"), "Cobblemon Breathers Configuration");
        EN_TRANSLATIONS.put(newConfiguration("section.cobblemon.breathers.client.toml"), "Client Configuration");
        EN_TRANSLATIONS.put(newConfiguration("section.cobblemon.breathers.client.toml.title"), "Client Configuration");
        EN_TRANSLATIONS.put(newConfiguration("section.cobblemon.breathers.server.toml"), "Server Configuration");
        EN_TRANSLATIONS.put(newConfiguration("section.cobblemon.breathers.server.toml.title"), "Server Configuration");
        EN_TRANSLATIONS.put(newConfiguration("hudConfig"), "Hud Configuration");
        EN_TRANSLATIONS.put(newConfiguration("hudConfig.tooltip"), "Configuration options for the in-game HUD for Breathers.");
        EN_TRANSLATIONS.put(newConfiguration("hudConfig.button"), "Edit");
        EN_TRANSLATIONS.put(newConfiguration("disableInGameOverlay"), "Disable the in-game overlay for Breathers.");
        EN_TRANSLATIONS.put(newConfiguration("disableInGameOverlay.tooltip"), "If enabled, the in-game overlay for Breathers will be disabled. This means that you will not see the air supply indicator on your screen while underwater. You will still receive notifications when your air supply is low or depleted.");
        EN_TRANSLATIONS.put(newConfiguration("generalConfig"), "General Configuration");
        EN_TRANSLATIONS.put(newConfiguration("generalConfig.tooltip"), "General configuration options for Cobblemon Breathers.");
        EN_TRANSLATIONS.put(newConfiguration("generalConfig.button"), "Edit");
        EN_TRANSLATIONS.put(newConfiguration("airSupplyRecovery"), "Air Supply Recovery");
        EN_TRANSLATIONS.put(newConfiguration("airSupplyRecovery.tooltip"), "How much air supply is recovered per tick when the player is not underwater.");
        EN_TRANSLATIONS.put(newConfiguration("maxAirConfig"), "Max Air Supply Configuration");
        EN_TRANSLATIONS.put(newConfiguration("maxAirConfig.tooltip"), "Configuration options for the maximum air supply of the ReBreather items.");
        EN_TRANSLATIONS.put(newConfiguration("maxAirConfig.button"), "Edit");
        EN_TRANSLATIONS.put(newConfiguration("mk1"), "ReBreather Mk1 Max Air Supply");
        EN_TRANSLATIONS.put(newConfiguration("mk1.tooltip"), "The maximum air supply for the ReBreather Mk1.");
        EN_TRANSLATIONS.put(newConfiguration("mk2"), "ReBreather Mk2 Max Air Supply");
        EN_TRANSLATIONS.put(newConfiguration("mk2.tooltip"), "The maximum air supply for the ReBreather Mk2.");
        EN_TRANSLATIONS.put(newConfiguration("mk3"), "ReBreather Mk3 Max Air Supply");
        EN_TRANSLATIONS.put(newConfiguration("mk3.tooltip"), "The maximum air supply for the ReBreather Mk3.");
        EN_TRANSLATIONS.put(newConfiguration("effectControlConfig"), "Effect Control Configuration");
        EN_TRANSLATIONS.put(newConfiguration("effectControlConfig.tooltip"), "Configuration options for the ReBreather effect.");
        EN_TRANSLATIONS.put(newConfiguration("effectControlConfig.button"), "Edit");
        EN_TRANSLATIONS.put(newConfiguration("showAmbient"), "Show Ambient Particles");
        EN_TRANSLATIONS.put(newConfiguration("showAmbient.tooltip"), "Whether the ReBreather effect should show ambient particles.");
        EN_TRANSLATIONS.put(newConfiguration("visible"), "Visible Effects");
        EN_TRANSLATIONS.put(newConfiguration("visible.tooltip"), "Whether the ReBreather effect should be visible on the player.");
        EN_TRANSLATIONS.put(newConfiguration("upgradeEnchantConfig"), "Breather Upgrade Enchantment Configuration");
        EN_TRANSLATIONS.put(newConfiguration("upgradeEnchantConfig.tooltip"), "Configuration options for the ReBreather Upgrade enchantment.");
        EN_TRANSLATIONS.put(newConfiguration("upgradeEnchantConfig.button"), "Edit");
        EN_TRANSLATIONS.put(newConfiguration("disableEnchantmentEffect"), "Disable Enchantment Effect");
        EN_TRANSLATIONS.put(newConfiguration("disableEnchantmentEffect.tooltip"), "Whether the ReBreather Upgrade enchantment should have a visual effect on the player.");
        EN_TRANSLATIONS.put(newConfiguration("levelOneAirAddition"), "Level One Air Addition");
        EN_TRANSLATIONS.put(newConfiguration("levelOneAirAddition.tooltip"), "The amount of air supply added by the ReBreather Upgrade enchantment at level 1.");
        EN_TRANSLATIONS.put(newConfiguration("levelTwoAirAddition"), "Level Two Air Addition");
        EN_TRANSLATIONS.put(newConfiguration("levelTwoAirAddition.tooltip"), "The amount of air supply added by the ReBreather Upgrade enchantment at level 2.");
        EN_TRANSLATIONS.put(newConfiguration("levelThreeAirAddition"), "Level Three Air Addition");
        EN_TRANSLATIONS.put(newConfiguration("levelThreeAirAddition.tooltip"), "The amount of air supply added by the ReBreather Upgrade enchantment at level 3.");
    }

    @SuppressWarnings("SameParameterValue")
    private static String newItemGroup(String name) {
        return "itemGroup.cobblemon_breathers." + name;
    }

    private static String newAirSupply(String name) {
        return "airSupply.cobblemon_breathers." + name;
    }

    @SuppressWarnings("SameParameterValue")
    private static String newEnchantment(String name) {
        return "enchantment.cobblemon_breathers." + name;
    }

    private static String newConfiguration(String name) {
        return "cobblemon_breathers.configuration." + name;
    }
}
