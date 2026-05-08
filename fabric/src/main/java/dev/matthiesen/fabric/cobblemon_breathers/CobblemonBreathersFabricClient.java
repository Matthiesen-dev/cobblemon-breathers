package dev.matthiesen.fabric.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathersClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class CobblemonBreathersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CobblemonBreathersClient.init();
        CobblemonBreathersClient.registerRenderers();
        HudRenderCallback.EVENT.register(CobblemonBreathersClient::createAirSupplyDisplayLayer);
    }
}
