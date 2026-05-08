package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathersClient;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class CobblemonBreathersNeoForgeClient {
    public CobblemonBreathersNeoForgeClient(IEventBus bus) {
        CobblemonBreathersClient.init();
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        CobblemonBreathersClient.registerRenderers();
    }

    @SubscribeEvent
    public static void onRegisterLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(
                VanillaGuiLayers.PLAYER_HEALTH,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "air_supply_display"),
                CobblemonBreathersClient::createAirSupplyDisplayLayer
        );
    }
}
