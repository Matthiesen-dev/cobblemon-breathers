package dev.matthiesen.common.cobblemon_breathers.compat.accessories;

import dev.matthiesen.common.cobblemon_breathers.item.BreatherItem;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record BreatherItemAccessory(BreatherItem item) implements Accessory {
    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        Accessory.super.tick(stack, reference);
        var entity = reference.entity();
        if (entity == null) return;
        if (!(entity instanceof Player player)) return;
        if (!player.isInWater()) return;
        if (player.level().isClientSide()) return;
        item.evaluateEffects(player);
    }
}
