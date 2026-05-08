package dev.matthiesen.common.cobblemon_breathers.compat.accessories;

import dev.matthiesen.common.cobblemon_breathers.item.ReBreatherItem;
import dev.matthiesen.common.cobblemon_breathers.util.PlayerUtils;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record ReBreatherItemAccessory<T extends ReBreatherItem>(T item) implements Accessory {
    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof Player player)
                || player.level().isClientSide()
                || PlayerUtils.checkPlayerConditions(player)) return;
        item.tickAccessory(stack, player);
    }
}
