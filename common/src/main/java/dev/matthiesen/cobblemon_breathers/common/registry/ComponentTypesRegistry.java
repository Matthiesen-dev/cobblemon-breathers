package dev.matthiesen.cobblemon_breathers.common.registry;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.matthiesen_core.common.registry.AbstractDataComponentTypeRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class ComponentTypesRegistry extends AbstractDataComponentTypeRegistry {
    public static final ComponentTypesRegistry INSTANCE = new ComponentTypesRegistry();

    private ComponentTypesRegistry() {
        super(CobblemonBreathers.MOD_ID);
    }

    public static void init() {
        CobblemonBreathers.INSTANCE.createInfoLog("Registering component types...");
    }

    public static final Supplier<DataComponentType<Integer>> AIR_RESERVE;
    public static final Supplier<DataComponentType<Integer>> MAX_AIR;
    public static final Supplier<DataComponentType<Integer>> ADDITIONAL_AIR;

    static {
        AIR_RESERVE = register("air_reserve", builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT));
        MAX_AIR = register("max_air", builder -> builder.persistent(ExtraCodecs.POSITIVE_INT));
        ADDITIONAL_AIR = register("additional_air", builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT));
    }

    @SuppressWarnings("unchecked")
    private static <K, T extends DataComponentType<K>> Supplier<T> register(String id, UnaryOperator<DataComponentType.Builder<K>> unaryOperator) {
        Supplier<T> supplier = () -> (T) unaryOperator.apply(DataComponentType.builder()).build();
        return INSTANCE.register(id, supplier);
    }
}
