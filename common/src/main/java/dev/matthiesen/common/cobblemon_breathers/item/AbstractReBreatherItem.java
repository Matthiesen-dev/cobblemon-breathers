package dev.matthiesen.common.cobblemon_breathers.item;

import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public abstract class AbstractReBreatherItem extends Item implements Equipable {
    private final int baseMaxAir;
    private final List<MobEffectInstance> effects;

    public AbstractReBreatherItem(Integer maxAir, UnaryOperator<EffectBuilder> effectBuilder) {
        super(getItemProps(maxAir));
        this.baseMaxAir = maxAir;
        this.effects = effectBuilder.apply(new EffectBuilder()).build();
    }

    public static class EffectBuilder {
        private final List<MobEffectInstance> effects = new ArrayList<>();

        public EffectBuilder addEffect(Holder<MobEffect> effect) {
            effects.add(new MobEffectInstance(effect, 100, 0, false, false));
            return this;
        }

        public List<MobEffectInstance> build() {
            return effects;
        }
    }

    private static Properties getItemProps(int maxAir) {
        return new Item.Properties()
                .stacksTo(1)
                .component(ComponentTypesRegistry.AIR_RESERVE, maxAir)
                .component(ComponentTypesRegistry.MAX_AIR, maxAir);
    }

    public int getMaxAir() {
        return baseMaxAir;
    }

    public boolean isEnchantable(ItemStack arg) {
        return arg.getCount() == 1;
    }

    public int getEnchantmentValue() {
        return 1;
    }

    /**
     * Intended for overriding in case of items needing to run custom code on the player
     * each tick, alongside applying effects.
     * By default, does nothing.
     */
    @SuppressWarnings("unused")
    public void runPlayerActions(Player player) {}

    public boolean checkPlayerConditions(Player player) {
        return player.isInWater() || player.isCreative() || player.isSpectator();
    }

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
        for (MobEffectInstance effect : effects) {
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
        int maxAir = item.getOrDefault(ComponentTypesRegistry.MAX_AIR, 0);
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
        int maxAir = itemStack.getOrDefault(ComponentTypesRegistry.MAX_AIR, 0);
        return Math.round((float)currentAir * 13.0F / (float)maxAir);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE, 0);
        int maxAir = itemStack.getOrDefault(ComponentTypesRegistry.MAX_AIR, 0);
        list.add(Component.translatable("airSupply.cobblemon_breathers.current_air", currentAir, maxAir).withStyle(ChatFormatting.BLUE));
    }
}
