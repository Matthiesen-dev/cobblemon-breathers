package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.matthiesen_lib.registry.AbstractDataComponentTypeRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class ComponentTypesRegistry extends AbstractDataComponentTypeRegistry {
    public static final ComponentTypesRegistry INSTANCE = new ComponentTypesRegistry();

    private ComponentTypesRegistry() {
        super(Constants.MOD_ID);
    }

    public static void init() {
        Constants.createInfoLog("Registering component types...");
    }

    public static final Supplier<DataComponentType<Integer>> AIR_RESERVE;
    public static final Supplier<DataComponentType<Integer>> MAX_AIR;

    static {
        AIR_RESERVE = register("air_reserve", builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT));
        MAX_AIR = register("max_air", builder -> builder.persistent(ExtraCodecs.POSITIVE_INT));
    }

    @SuppressWarnings("unchecked")
    private static <K, T extends DataComponentType<K>> Supplier<T> register(String id, UnaryOperator<DataComponentType.Builder<K>> unaryOperator) {
        Supplier<T> supplier = () -> (T) unaryOperator.apply(DataComponentType.builder()).build();
        return INSTANCE.register(id, supplier);
    }
}
