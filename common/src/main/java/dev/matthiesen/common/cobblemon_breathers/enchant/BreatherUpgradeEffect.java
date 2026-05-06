package dev.matthiesen.common.cobblemon_breathers.enchant;

import com.mojang.serialization.MapCodec;
import dev.matthiesen.common.cobblemon_breathers.item.AbstractReBreatherItem;
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
        if (!(context.itemStack().getItem() instanceof AbstractReBreatherItem item)) return;
        if (entity.getType() != EntityType.PLAYER) return;
        int maxAir = item.getMaxAir();
        switch (enchantLevel) {
            case 1 -> setMaxAir(context, maxAir, 200);
            case 2 -> setMaxAir(context, maxAir, 800);
            case 3 -> setMaxAir(context, maxAir, 2000);
            default -> {}
        }
    }

    public static void setMaxAir(EnchantedItemInUse context, int maxAir, int Addition) {
        context.itemStack().set(ComponentTypesRegistry.MAX_AIR, maxAir + Addition);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
