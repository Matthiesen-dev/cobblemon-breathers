package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ComponentTypesRegistry {
    public static void init() {}

    public static final DataComponentType<Integer> AIR_RESERVE = register("air_reserve",
            builder -> builder.persistent(ExtraCodecs.POSITIVE_INT)
    );

    @SuppressWarnings("SameParameterValue")
    private static <T> DataComponentType<T> register(String string, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return register(string, () -> unaryOperator.apply(DataComponentType.builder()).build()).get();
    }

    private static <T extends DataComponentType<?>> Supplier<T> register(String id, Supplier<T> component) {
        return CobblemonBreathers.COMMON_PLATFORM.registerComponentType(id, component);
    }
}
