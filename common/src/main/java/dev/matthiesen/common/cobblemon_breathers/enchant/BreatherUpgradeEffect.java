package dev.matthiesen.common.cobblemon_breathers.enchant;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
import dev.matthiesen.common.cobblemon_breathers.item.ReBreatherItem;
import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
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
        var config = CobblemonBreathers.getConfig().breatherUpgradeEnchantConfig;
        if (!(context.itemStack().getItem() instanceof ReBreatherItem item)
                || config.disableEnchantmentEffect
                || entity.getType() != EntityType.PLAYER) return;

        int maxAir = item.getMaxAir();
        switch (enchantLevel) {
            case 1 -> setMaxAir(context, maxAir, config.levelOneAirAddition);
            case 2 -> setMaxAir(context, maxAir, config.levelTwoAirAddition);
            case 3 -> setMaxAir(context, maxAir, config.levelThreeAirAddition);
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
