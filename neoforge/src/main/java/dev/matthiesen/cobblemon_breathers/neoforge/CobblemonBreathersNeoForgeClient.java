package dev.matthiesen.cobblemon_breathers.neoforge;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathersClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = CobblemonBreathers.MOD_ID, value = Dist.CLIENT)
public final class CobblemonBreathersNeoForgeClient {
    private static volatile CobblemonBreathersClient instance;

    public CobblemonBreathersNeoForgeClient() {
        instance = CobblemonBreathersClient.INSTANCE;
        instance.initialize();
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        instance.registerRenderers();
    }
}
