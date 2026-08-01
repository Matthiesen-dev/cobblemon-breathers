package dev.matthiesen.cobblemon_breathers.common.registry;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.matthiesen_core.common.core.registry.BuiltInCreativeModeSection;
import dev.matthiesen.matthiesen_core.common.registry.AbstractCreativeModeTabRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;

public final class CreativeModeTabsRegistry extends AbstractCreativeModeTabRegistry {
    public static final CreativeModeTabsRegistry INSTANCE = new CreativeModeTabsRegistry();

    private static final BuiltInCreativeModeSection.RegistrationKey REBREATHERS_KEY = new BuiltInCreativeModeSection.RegistrationKey(
            CobblemonBreathers.modResource("breathers"),
            Component.literal("Cobblemon Breathers"),
            100
    );

    private CreativeModeTabsRegistry() {
        super(CobblemonBreathers.MOD_ID);
    }

    public static void init() {
        CobblemonBreathers.INSTANCE.createInfoLog("Registering creative mode tabs...");

        INSTANCE.registerTabItemAugmentations(CreativeModeTabs.TOOLS_AND_UTILITIES, ItemRegistry.REBREATHERS);
        INSTANCE.registerItemsToMiscTab(REBREATHERS_KEY, ItemRegistry.REBREATHERS);
    }
}
