package dev.matthiesen.cobblemon_breathers.common.registry;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.matthiesen_core.common.registry.AbstractCreativeModeTabRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public final class CreativeModeTabsRegistry extends AbstractCreativeModeTabRegistry {
    public static final CreativeModeTabsRegistry INSTANCE = new CreativeModeTabsRegistry();

    private CreativeModeTabsRegistry() {
        super(CobblemonBreathers.MOD_ID);
    }

    public static void init() {
        CobblemonBreathers.INSTANCE.createInfoLog("Registering creative mode tabs...");
    }

    public static final Supplier<CreativeModeTab> MAIN_TAB;

    static {
        MAIN_TAB = INSTANCE.register("cobblemon_breathers", () -> INSTANCE.getRegistryBuilder().newCreativeTabBuilder()
                .title(Component.translatable("itemGroup." + CobblemonBreathers.MOD_ID + ".cobblemon_breathers"))
                .icon(() -> new ItemStack(ItemRegistry.REBREATHER_MK1.get()))
                .displayItems((enabledFeatures, entries) ->
                        ItemRegistry.REBREATHERS.forEach(breather ->
                                entries.accept(breather.get())))
                .build());
    }
}
