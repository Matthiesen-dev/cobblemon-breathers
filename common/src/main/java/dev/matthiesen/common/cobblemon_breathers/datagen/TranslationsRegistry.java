package dev.matthiesen.common.cobblemon_breathers.datagen;

import java.util.HashMap;
import java.util.Map;

public class TranslationsRegistry {
    public static final Map<String, String> EN_TRANSLATIONS = new HashMap<>();

    static {
        // Creative Tab Groups
        EN_TRANSLATIONS.put(newItemGroup("cobblemon_breathers"), "Cobblemon Breathers");

        // Air Supply Labels
        EN_TRANSLATIONS.put(newAirSupply("current_air"), "Current Air Supply: %s/%s seconds");
        EN_TRANSLATIONS.put(newAirSupply("supply_low"), "Air Supply low! %s Seconds left!");
        EN_TRANSLATIONS.put(newAirSupply("supply_depleted"), "Air Supply depleted! You are suffocating!");
        EN_TRANSLATIONS.put(newAirSupply("hud_display"), "%s seconds");

        // Enchantment Labels
        EN_TRANSLATIONS.put(newEnchantment("breather_upgrade"), "Breather Upgrade");
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
}
