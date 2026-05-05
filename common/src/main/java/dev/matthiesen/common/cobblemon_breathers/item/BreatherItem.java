package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BreatherItem extends Item implements Equipable {
    public BreatherItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);

    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        return swapWithEquipmentSlot(this, worldIn, playerIn, handIn);
    }
}
