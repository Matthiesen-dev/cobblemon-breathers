package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathersClient;
import net.neoforged.bus.api.IEventBus;

public class CobblemonBreathersNeoForgeClient {
    public CobblemonBreathersNeoForgeClient(IEventBus bus) {
        CobblemonBreathersClient.init();
    }
}
