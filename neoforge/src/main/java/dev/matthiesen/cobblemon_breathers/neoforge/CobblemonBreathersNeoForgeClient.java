package dev.matthiesen.cobblemon_breathers.neoforge;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathersClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = CobblemonBreathers.MOD_ID, dist = Dist.CLIENT)
public final class CobblemonBreathersNeoForgeClient {
    private static final CobblemonBreathersClient instance = CobblemonBreathersClient.INSTANCE;

    public CobblemonBreathersNeoForgeClient(IEventBus modBus) {
        instance.initialize();
        modBus.addListener(this::registerRenderers);
    }

    public void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        instance.registerRenderers();
    }
}
