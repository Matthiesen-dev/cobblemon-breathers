package dev.matthiesen.common.cobblemon_breathers.registry;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.enchant.BreatherUpgradeEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class EnchantmentEffectsRegistry {
    public static void init() {
        Constants.createInfoLog("Registering enchantment effects...");
    }

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BREATHER_UPGRADE =
            register("breather_upgrade", BreatherUpgradeEffect.CODEC);

    @SuppressWarnings("SameParameterValue")
    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> register(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return CobblemonBreathers.COMMON_PLATFORM.registerEntityEffects(name, () -> codec);
    }
}
