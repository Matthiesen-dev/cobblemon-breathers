package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public class CriterionTriggerRegistry {
    public static void init() {}

    private static <T extends CriterionTrigger<?>> Supplier<T> register(String id, Supplier<T> criterionTrigger) {
        return CobblemonBreathers.COMMON_PLATFORM.registerCriteriaTriggers(id, criterionTrigger);
    }
}
