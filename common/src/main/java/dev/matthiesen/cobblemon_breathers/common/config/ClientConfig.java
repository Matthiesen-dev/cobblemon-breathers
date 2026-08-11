package dev.matthiesen.cobblemon_breathers.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ClientConfig {

    // Hud Configuration
    public ModConfigSpec.BooleanValue hud_disableOverlay;

    public ClientConfig(ModConfigSpec.Builder builder) {
        builder.comment("Hud Configuration").push("hudConfig");
        hud_disableOverlay = builder.comment("Disable the in-game overlay for Breathers.")
                .define("disableInGameOverlay", false);
        builder.pop();
    }
}
