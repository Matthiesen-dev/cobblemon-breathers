package dev.matthiesen.cobblemon_breathers.fabric;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathersClient;
import net.fabricmc.api.ClientModInitializer;

public final class CobblemonBreathersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        var instance = CobblemonBreathersClient.INSTANCE;
        instance.initialize();
        instance.registerRenderers();
    }
}
