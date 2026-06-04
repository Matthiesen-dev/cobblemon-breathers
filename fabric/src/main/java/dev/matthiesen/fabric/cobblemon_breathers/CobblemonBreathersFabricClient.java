package dev.matthiesen.fabric.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathersClient;
import net.fabricmc.api.ClientModInitializer;

public class CobblemonBreathersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CobblemonBreathersClient.init();
        CobblemonBreathersClient.registerRenderers();
    }
}
