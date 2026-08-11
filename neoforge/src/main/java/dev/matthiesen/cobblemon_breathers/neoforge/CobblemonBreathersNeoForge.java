package dev.matthiesen.cobblemon_breathers.neoforge;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import net.neoforged.fml.common.Mod;

@Mod(CobblemonBreathers.MOD_ID)
public final class CobblemonBreathersNeoForge {
    public CobblemonBreathersNeoForge() {
        var instance = CobblemonBreathers.INSTANCE;
        instance.createInfoLog("Loading for NeoForge Mod Loader");
        instance.initialize();
    }
}
