package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ComponentTypesRegistry {
    public static void init() {
        Constants.createInfoLog("Registering component types...");
    }

    public static final Supplier<DataComponentType<Integer>> AIR_RESERVE = register("air_reserve",
            builder -> builder.persistent(ExtraCodecs.POSITIVE_INT)
    );

    public static final Supplier<DataComponentType<Integer>> MAX_AIR = register("max_air",
            builder -> builder.persistent(ExtraCodecs.POSITIVE_INT)
    );

    @SuppressWarnings("unchecked")
    private static <K, T extends DataComponentType<K>> Supplier<T> register(String id, UnaryOperator<DataComponentType.Builder<K>> unaryOperator) {
        Supplier<T> supplier = () -> (T) unaryOperator.apply(DataComponentType.builder()).build();
        return CobblemonBreathers.COMMON_PLATFORM.registerComponentType(id, supplier);
    }
}
