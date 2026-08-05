package dev.matthiesen.cobblemon_breathers.common.enchant;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.cobblemon_breathers.common.config.BreathersConfig;
import dev.matthiesen.cobblemon_breathers.common.item.ReBreatherItem;
import dev.matthiesen.cobblemon_breathers.common.registry.ComponentTypesRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record BreatherUpgradeEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<BreatherUpgradeEffect> CODEC = MapCodec.unit(BreatherUpgradeEffect::new);

    @Override
    public void apply(ServerLevel world, int enchantLevel, EnchantedItemInUse context, Entity entity, Vec3 pos) {
        var config = BreathersConfig.SERVER_CONFIG;
        if (!(context.itemStack().getItem() instanceof ReBreatherItem item)
                || config.enchants_disableEffects.getAsBoolean()
                || entity.getType() != EntityType.PLAYER) return;

        int maxAir = item.getMaxAir();
        switch (enchantLevel) {
            case 1 -> setMaxAir(context, maxAir, config.enchants_levelOneAirAddition.getAsInt());
            case 2 -> setMaxAir(context, maxAir, config.enchants_levelTwoAirAddition.getAsInt());
            case 3 -> setMaxAir(context, maxAir, config.enchants_levelThreeAirAddition.getAsInt());
            default -> {}
        }
    }

    public static void setMaxAir(EnchantedItemInUse context, int maxAir, int Addition) {
        context.itemStack().set(ComponentTypesRegistry.MAX_AIR.get(), maxAir + Addition);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
