package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class CobblemonBreathersNeoForge {
    public CobblemonBreathersNeoForge() {
        Constants.createInfoLog("Loading for NeoForge Mod Loader");
        CobblemonBreathers.loadConfig();
        CobblemonBreathers.initialize();
    }
}
