package dev.matthiesen.common.cobblemon_breathers.item;

import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractReBreatherItem extends Item implements Equipable  {
    private final int maxAir;

    public AbstractReBreatherItem(Item.Properties properties, Integer maxAir) {
        super(properties.component(ComponentTypesRegistry.AIR_RESERVE, maxAir));
        this.maxAir = maxAir;
    }

    /**
     * Intended for overriding to provide the conditions that must be met for the item
     * to apply its effects.
     */
    public abstract boolean checkPlayerConditions(Player player);

    /**
     * Intended for overriding to provide the list of effects that should be applied
     * to the player when the item is equipped and the player is in water.
     * By default, returns an empty list.
     */
    public abstract List<MobEffectInstance> getPlayerEffects();

    /**
     * Intended for overriding in case of items needing to run custom code on the player
     * each tick, alongside applying effects.
     * By default, does nothing.
     */
    public abstract void runPlayerActions(Player player);

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);
        if (!(entity instanceof Player player)) return;
        tickAirSupply(itemStack, player);
        if (!checkPlayerConditions(player)) return;
        if (!checkItemEquipped(player)) return;
        runPlayerActions(player);
        evaluateEffects(player);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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
        for (MobEffectInstance effect : getPlayerEffects()) {
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

    public void tickAirSupply(ItemStack item, Player player) {
        if (player.tickCount % 20 != 0) return;
        int currentAir = item.getOrDefault(ComponentTypesRegistry.AIR_RESERVE, 0);
        if (!player.isInWater()) {
            if (currentAir < maxAir) {
                var toAddToCurrent = currentAir + 50;
                if (toAddToCurrent > maxAir) toAddToCurrent = maxAir;
                item.set(ComponentTypesRegistry.AIR_RESERVE, toAddToCurrent);
            }
            return;
        }
        if (!checkItemEquipped(player)) return;
        if (currentAir > 0) {
            item.set(ComponentTypesRegistry.AIR_RESERVE, currentAir - 1);
        }
        boolean under10PercentAir = currentAir <= (maxAir * 0.1);
        if (under10PercentAir && currentAir > 0) {
            var remainingSeconds = currentAir - maxAir;
            player.displayClientMessage(Component.translatable("airSupply.cobblemon_breathers.supply_low", remainingSeconds).withStyle(ChatFormatting.RED), true);
        }
        if (currentAir == 0) {
            player.displayClientMessage(Component.translatable("airSupply.cobblemon_breathers.supply_depleted").withStyle(ChatFormatting.RED), true);
            player.hurt(player.damageSources().drown(), 2.0F);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return 0x00BFFF;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE, 0);
        return Math.round((float)currentAir * 13.0F / (float)this.maxAir);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE, 0);
        list.add(Component.translatable("airSupply.cobblemon_breathers.current_air", currentAir, maxAir).withStyle(ChatFormatting.BLUE));
    }
}
