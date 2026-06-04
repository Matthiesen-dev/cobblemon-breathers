package dev.matthiesen.common.cobblemon_breathers.registry;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.enchant.BreatherUpgradeEffect;
import dev.matthiesen.common.matthiesen_lib.registry.AbstractEntityEffectRegistry;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import java.util.function.Supplier;

public class EnchantmentEffectsRegistry extends AbstractEntityEffectRegistry {
    public static final EnchantmentEffectsRegistry INSTANCE = new EnchantmentEffectsRegistry();

    private EnchantmentEffectsRegistry() {
        super(Constants.MOD_ID);
    }

    public static void init() {
        Constants.createInfoLog("Registering enchantment effects...");
    }

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BREATHER_UPGRADE;

    static {
        BREATHER_UPGRADE = INSTANCE.register("breather_upgrade", () -> BreatherUpgradeEffect.CODEC);
    }
}
