package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ItemRegistry {
    public static void init() {}

    public static final Supplier<Item> POKE_BREATHER = registerItem("poke_breather", () -> new Item(new Item.Properties()));

    @SuppressWarnings("SameParameterValue")
    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return CobblemonBreathers.COMMON_PLATFORM.registerItem(id, item);
    }

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CREATIVE_MODE_TAB_SUPPLIER = CobblemonBreathers.COMMON_PLATFORM
            .registerCreativeModeTab("cobblemon_breathers", () -> CobblemonBreathers.COMMON_PLATFORM
            .newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".cobblemon_breathers"))
            .icon(() -> new ItemStack(ItemRegistry.POKE_BREATHER.get()))
            .displayItems((enabledFeatures, entries) -> entries.accept(ItemRegistry.POKE_BREATHER.get())
            )
            .build());
}
