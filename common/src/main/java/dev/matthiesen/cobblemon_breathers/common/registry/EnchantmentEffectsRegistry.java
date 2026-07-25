package dev.matthiesen.cobblemon_breathers.common.registry;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.cobblemon_breathers.common.enchant.BreatherUpgradeEffect;
import dev.matthiesen.common.matthiesen_lib.registry.AbstractEntityEffectRegistry;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import java.util.function.Supplier;

public final class EnchantmentEffectsRegistry extends AbstractEntityEffectRegistry {
    public static final EnchantmentEffectsRegistry INSTANCE = new EnchantmentEffectsRegistry();

    private EnchantmentEffectsRegistry() {
        super(CobblemonBreathers.MOD_ID);
    }

    public static void init() {
        CobblemonBreathers.INSTANCE.createInfoLog("Registering enchantment effects...");
    }

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BREATHER_UPGRADE;

    static {
        BREATHER_UPGRADE = INSTANCE.register("breather_upgrade", () -> BreatherUpgradeEffect.CODEC);
    }
}
