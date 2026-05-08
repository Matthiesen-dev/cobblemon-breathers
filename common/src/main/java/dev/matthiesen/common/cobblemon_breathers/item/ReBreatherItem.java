package dev.matthiesen.common.cobblemon_breathers.item;

import dev.matthiesen.common.cobblemon_breathers.CobblemonBreathers;
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
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ReBreatherItem extends Item implements Equipable {
    private final boolean ambientEffects;
    private final boolean visibleEffects;
    private final boolean effectIcons;
    private final int baseMaxAir;
    private final List<MobEffectInstance> effects;

    public ReBreatherItem(Integer maxAir, UnaryOperator<EffectBuilder> effectBuilder) {
        super(getItemProps(maxAir));
        this.baseMaxAir = maxAir;
        this.effects = effectBuilder.apply(new EffectBuilder()).build();
        this.ambientEffects = CobblemonBreathers.config.reBreatherItemConfig.effectsConfig.showAmbient;
        this.visibleEffects = CobblemonBreathers.config.reBreatherItemConfig.effectsConfig.visible;
        // Investigate why this seems to do nothing...
        this.effectIcons = false;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> Supplier<T> create(Integer maxAir, UnaryOperator<EffectBuilder> effectBuilder) {
        return () -> (T) new ReBreatherItem(maxAir, effectBuilder);
    }

    public static class EffectBuilder {
        private final List<MobEffectInstance> effects = new ArrayList<>();

        public EffectBuilder addEffect(Holder<MobEffect> effect) {
            effects.add(new MobEffectInstance(effect));
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

    public boolean checkAntiConditions(Player player) {
        return player.isCreative() || player.isSpectator();
    }

    public boolean checkPlayerConditions(Player player) {
        return !player.isInWater() || checkAntiConditions(player);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (!(entity instanceof Player player)) return;
        tickAirSupply(itemStack, player);
        if (checkPlayerConditions(player)) return;
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
                player.addEffect(new MobEffectInstance(effect.getEffect(), 100, 0, ambientEffects, visibleEffects, effectIcons));
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

    /**
     * This is to ensure that the air supply value never goes below 0, as that would cause a game crash
     *
     * @param value the value to check
     * @return the value if it's above 0, or 0 if it's below 0
     */
    private int ensureMinimumValue(Integer value) {
        int MinValue = 0;
        if (value > MinValue) {
            MinValue = value;
        }
        return MinValue;
    }

    public void tickAirSupply(ItemStack item, Player player) {
        if (player.tickCount % 20 != 0) return;
        int currentAir = item.getOrDefault(ComponentTypesRegistry.AIR_RESERVE, 0);
        int maxAir = item.getOrDefault(ComponentTypesRegistry.MAX_AIR, 0);
        if (checkPlayerConditions(player) || checkAntiConditions(player)) {
            if (currentAir < maxAir) {
                var toAddToCurrent = currentAir + CobblemonBreathers.config.reBreatherItemConfig.airSupplyRecovery;
                if (toAddToCurrent > maxAir) toAddToCurrent = maxAir;
                item.set(ComponentTypesRegistry.AIR_RESERVE, ensureMinimumValue(toAddToCurrent));
            }
            return;
        }
        if (!checkItemEquipped(player)) return;
        if (currentAir > 0) {
            item.set(ComponentTypesRegistry.AIR_RESERVE, ensureMinimumValue(currentAir - 1));
        }
        boolean under10PercentAir = currentAir <= (maxAir * 0.1);
        if (under10PercentAir && currentAir > 0) {
            player.displayClientMessage(Component.translatable("airSupply.cobblemon_breathers.supply_low", currentAir).withStyle(ChatFormatting.RED), true);
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
