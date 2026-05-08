package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.item.ReBreatherItem;
import dev.matthiesen.common.cobblemon_breathers.util.Effects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemRegistry {
    public static void init() {
        Constants.createInfoLog("Registering items...");
    }

    public static final Map<String, String> EN_TRANSLATION_MAP = Map.of(
            "rebreather_mk1", "ReBreather Mk1",
            "rebreather_mk2", "ReBreather Mk2",
            "rebreather_mk3", "ReBreather Mk3"
    );

    public static final Supplier<Item> REBREATHER_MK1 = registerReBreather("rebreather_mk1",
            CobblemonBreathers.config.reBreatherItemConfig.maxAirConfig.mk1,
            builder -> builder.addEffect(MobEffects.WATER_BREATHING)
    );

    public static final Supplier<Item> REBREATHER_MK2 = registerReBreather("rebreather_mk2",
            CobblemonBreathers.config.reBreatherItemConfig.maxAirConfig.mk2,
            builder -> builder.addEffect(MobEffects.WATER_BREATHING)
                    .addEffect(MobEffects.NIGHT_VISION)
    );

    public static final Supplier<Item> REBREATHER_MK3 = registerReBreather("rebreather_mk3",
            CobblemonBreathers.config.reBreatherItemConfig.maxAirConfig.mk3,
            builder -> builder.addEffect(MobEffects.CONDUIT_POWER)
    );

    public static final List<Supplier<Item>> REBREATHERS = List.of(
            REBREATHER_MK1,
            REBREATHER_MK2,
            REBREATHER_MK3
    );

    private static <T extends Item> Supplier<T> registerReBreather(String id, Integer maxAir, UnaryOperator<Effects.Builder> effectBuilder) {
        return registerItem(id, ReBreatherItem.create(maxAir, effectBuilder));
    }

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return CobblemonBreathers.COMMON_PLATFORM.registerItem(id, item);
    }

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> CREATIVE_MODE_TAB_SUPPLIER = CobblemonBreathers.COMMON_PLATFORM
            .registerCreativeModeTab("cobblemon_breathers", () -> CobblemonBreathers.COMMON_PLATFORM
            .newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".cobblemon_breathers"))
            .icon(() -> new ItemStack(ItemRegistry.REBREATHER_MK1.get()))
            .displayItems((enabledFeatures, entries) ->
                    REBREATHERS.forEach(breather ->
                            entries.accept(breather.get()))
            )
            .build());
}
