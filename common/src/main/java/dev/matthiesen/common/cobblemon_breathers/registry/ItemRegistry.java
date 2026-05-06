package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Supplier;

public class ItemRegistry {
    public static void init() {}

    public static final Map<String, String> EN_TRANSLATION_MAP = Map.of(
            "rebreather_mk1", "ReBreather Mk1",
            "rebreather_mk2", "ReBreather Mk2",
            "rebreather_mk3", "ReBreather Mk3"
    );

    public static final Supplier<Item> REBREATHER_MK1 = registerItem("rebreather_mk1", ReBreatherMk1Item::new);
    public static final Supplier<Item> REBREATHER_MK2 = registerItem("rebreather_mk2", ReBreatherMk2Item::new);
    public static final Supplier<Item> REBREATHER_MK3 = registerItem("rebreather_mk3", ReBreatherMk3Item::new);

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return CobblemonBreathers.COMMON_PLATFORM.registerItem(id, item);
    }

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CREATIVE_MODE_TAB_SUPPLIER = CobblemonBreathers.COMMON_PLATFORM
            .registerCreativeModeTab("cobblemon_breathers", () -> CobblemonBreathers.COMMON_PLATFORM
            .newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".cobblemon_breathers"))
            .icon(() -> new ItemStack(ItemRegistry.REBREATHER_MK1.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegistry.REBREATHER_MK1.get());
                entries.accept(ItemRegistry.REBREATHER_MK2.get());
                entries.accept(ItemRegistry.REBREATHER_MK3.get());
            })
            .build());
}
