package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathersClient;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class CobblemonBreathersNeoForgeClient {
    public CobblemonBreathersNeoForgeClient() {
        CobblemonBreathersClient.init();
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        CobblemonBreathersClient.registerRenderers();
    }
}
