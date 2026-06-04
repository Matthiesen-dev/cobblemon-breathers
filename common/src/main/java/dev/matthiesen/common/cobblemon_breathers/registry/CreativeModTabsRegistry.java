package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.matthiesen_lib.registry.AbstractCreativeModeTabRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class CreativeModTabsRegistry extends AbstractCreativeModeTabRegistry {
    public static final CreativeModTabsRegistry INSTANCE = new CreativeModTabsRegistry();

    protected CreativeModTabsRegistry() {
        super(Constants.MOD_ID);
    }

    public static void init() {
        Constants.createInfoLog("Registering creative mode tabs...");
    }

    public static final Supplier<CreativeModeTab> MAIN_TAB;

    static {
        MAIN_TAB = INSTANCE.register("cobblemon_breathers", () -> INSTANCE.getRegistryBuilder().newCreativeTabBuilder()
                .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".cobblemon_breathers"))
                .icon(() -> new ItemStack(ItemRegistry.REBREATHER_MK1.get()))
                .displayItems((enabledFeatures, entries) ->
                        ItemRegistry.REBREATHERS.forEach(breather ->
                                entries.accept(breather.get())))
                .build());
    }
}
