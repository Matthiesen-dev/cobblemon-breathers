package dev.matthiesen.fabric.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class CobblemonBreathersFabric implements ModInitializer {
    public static MinecraftServer MC_SERVER;

    @Override
    public void onInitialize() {
        Constants.createInfoLog("Loading for Fabric Mod Loader");
        CobblemonBreathers.initialize();

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            CobblemonBreathers.onStartup();
            MC_SERVER = server;
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            CobblemonBreathers.onShutdown();
            MC_SERVER = null;
        });
    }

}
