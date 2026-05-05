package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BreatherItem extends Item implements Equipable {
    public static final List<MobEffectInstance> EFFECTS = List.of(
            new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0, false, false)
    );

    public BreatherItem() {
        super(new Item.Properties());
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);
        if (!(entity instanceof Player player)) return;
        if (!player.isInWater()) return;
        if (!checkItemEquipped(player)) return;
        evaluateEffects(player);
    }

    private boolean checkItemEquipped(Player player) {
        var inventory = player.getInventory();
        ItemStack helmetSlot = inventory.getArmor(3);
        var listOfItems = inventory.items.stream().toList();
        if (helmetSlot.isEmpty() && listOfItems.isEmpty()) return false;
        if (!helmetSlot.isEmpty() && helmetSlot.getItem().equals(this)) return true;

        boolean itemFoundInInventory = false;

        for (ItemStack stack : listOfItems) {
            if (stack.getItem().equals(this)) {
                itemFoundInInventory = true;
                break;
            }
        }
        return !itemFoundInInventory;
    }

    public void evaluateEffects(Player player) {
        for (MobEffectInstance effect : EFFECTS) {
            if (!player.hasEffect(effect.getEffect())) {
                player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
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
