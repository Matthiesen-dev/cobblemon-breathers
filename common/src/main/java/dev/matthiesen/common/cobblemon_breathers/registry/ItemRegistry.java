package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.item.ReBreatherItem;
import dev.matthiesen.common.cobblemon_breathers.util.Effects;
import dev.matthiesen.common.matthiesen_lib.registry.AbstractItemRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemRegistry extends AbstractItemRegistry {
    public static final ItemRegistry INSTANCE = new ItemRegistry();

    private ItemRegistry() {
        super(Constants.MOD_ID);
    }

    public static void init() {
        Constants.createInfoLog("Registering items...");
    }

    public static final Map<String, String> EN_TRANSLATION_MAP = Map.of(
            "rebreather_mk1", "ReBreather Mk1",
            "rebreather_mk2", "ReBreather Mk2",
            "rebreather_mk3", "ReBreather Mk3"
    );

    public static final Supplier<Item> REBREATHER_MK1;
    public static final Supplier<Item> REBREATHER_MK2;
    public static final Supplier<Item> REBREATHER_MK3;

    static {
        REBREATHER_MK1 = registerReBreather("rebreather_mk1",
                CobblemonBreathers.getConfig().reBreatherItemConfig.maxAirConfig.mk1,
                builder -> builder.addEffect(MobEffects.WATER_BREATHING)
        );
        REBREATHER_MK2 = registerReBreather("rebreather_mk2",
                CobblemonBreathers.getConfig().reBreatherItemConfig.maxAirConfig.mk2,
                builder -> builder.addEffect(MobEffects.WATER_BREATHING)
                        .addEffect(MobEffects.NIGHT_VISION)
        );
        REBREATHER_MK3 = registerReBreather("rebreather_mk3",
                CobblemonBreathers.getConfig().reBreatherItemConfig.maxAirConfig.mk3,
                builder -> builder.addEffect(MobEffects.CONDUIT_POWER)
        );
    }

    public static final List<Supplier<Item>> REBREATHERS = List.of(
            REBREATHER_MK1,
            REBREATHER_MK2,
            REBREATHER_MK3
    );

    private static <T extends Item> Supplier<T> registerReBreather(String id, Integer maxAir, UnaryOperator<Effects.Builder> effectBuilder) {
        return registerItem(id, ReBreatherItem.create(maxAir, effectBuilder));
    }

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return INSTANCE.register(id, item);
    }
}
