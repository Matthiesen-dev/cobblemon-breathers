package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public class CriterionTriggerRegistry {
    public static void init() {
        Constants.createInfoLog("Registering advancement triggers...");
    }

    private static <T extends CriterionTrigger<?>> Supplier<T> register(String id, Supplier<T> criterionTrigger) {
        return CobblemonBreathers.COMMON_PLATFORM.registerCriteriaTriggers(id, criterionTrigger);
    }
}
