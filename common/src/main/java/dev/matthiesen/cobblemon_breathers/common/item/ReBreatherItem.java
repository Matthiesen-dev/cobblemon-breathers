package dev.matthiesen.cobblemon_breathers.common.item;

import dev.matthiesen.cobblemon_breathers.common.CobblemonBreathers;
import dev.matthiesen.cobblemon_breathers.common.config.BreathersConfig;
import dev.matthiesen.cobblemon_breathers.common.datagen.ModTags;
import dev.matthiesen.cobblemon_breathers.common.registry.ComponentTypesRegistry;
import dev.matthiesen.cobblemon_breathers.common.util.Effects;
import dev.matthiesen.cobblemon_breathers.common.util.PlayerUtils;
import io.wispforest.accessories.api.AccessoriesCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ReBreatherItem extends Item implements Equipable {
    private final boolean effectIcons;
    private final List<MobEffectInstance> effects;
    private final int mk;

    private static int getBaseMaxAir(int mk) {
        return switch (mk) {
            case 1 -> BreathersConfig.SERVER_CONFIG.maxAir_mk1.getAsInt();
            case 2 -> BreathersConfig.SERVER_CONFIG.maxAir_mk2.getAsInt();
            case 3 -> BreathersConfig.SERVER_CONFIG.maxAir_mk3.getAsInt();
            default -> 0;
        };
    }

    public ReBreatherItem(Integer mk, UnaryOperator<Effects.Builder> effectBuilder) {
        super(getItemProps());
        this.mk = mk;
        // Investigate why this seems to do nothing...
        this.effectIcons = false;
        this.effects = effectBuilder.apply(Effects.builder()).build();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> Supplier<T> create(Integer mk, UnaryOperator<Effects.Builder> effectBuilder) {
        return () -> (T) new ReBreatherItem(mk, effectBuilder);
    }

    private static Properties getItemProps() {
        return new Item.Properties()
                .stacksTo(1);
    }

    public boolean isEnchantable(ItemStack arg) {
        return arg.getCount() == 1;
    }

    public int getEnchantmentValue() {
        return 1;
    }

    public void tickAccessory(ItemStack itemStack, Player player) {
        evaluateEffects(itemStack, player);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (!(entity instanceof Player player)) return;
        tickAirSupply(itemStack, player);
        if (PlayerUtils.checkPlayerConditions(player) || !isItemEquipped(player)) {
            clearEffects(player);
            return;
        }
        evaluateEffects(itemStack, player);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isItemEquipped(Player player) {
        var inventory = player.getInventory();
        ItemStack helmetSlot = inventory.getArmor(3);
        boolean isInHelmetSlot = !helmetSlot.isEmpty() && helmetSlot.getItem().equals(this);
        boolean isInAccessorySlot = false;

        if (CobblemonBreathers.INSTANCE.getCommonUtils().isModLoaded("accessories")) {
            var capability = AccessoriesCapability.get(player);
            if (capability != null) {
                var bl = capability.isEquipped(stack -> !stack.isEmpty() && stack.is(ModTags.Items.BREATHERS));
                if (bl) {
                    var test = capability.getEquipped(stack -> !stack.isEmpty() && stack.is(ModTags.Items.BREATHERS)).getFirst();
                    isInAccessorySlot = test.stack().getItem().equals(this);
                }
            }
        }

        if (isInHelmetSlot) return true;
        return isInAccessorySlot;
    }

    public void clearEffects(Player player) {
        for (MobEffectInstance effect : effects) {
            if (
                    player.hasEffect(effect.getEffect()) &&
                    (Objects.requireNonNull(
                            player.getEffect(effect.getEffect()))
                            .getDuration() == MobEffectInstance.INFINITE_DURATION
                    )
            ) {
                player.removeEffect(effect.getEffect());
            }
        }
    }

    public void evaluateEffects(ItemStack itemStack, Player player) {
        ensureAirComponentsInitialized(itemStack);
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0);
        if (currentAir == 0) {
            clearEffects(player);
            return;
        }
        var config = BreathersConfig.SERVER_CONFIG;
        for (MobEffectInstance effect : effects) {
            if (!player.hasEffect(effect.getEffect())) {
                player.addEffect(new MobEffectInstance(
                        effect.getEffect(),
                        MobEffectInstance.INFINITE_DURATION,
                        0,
                        config.effects_showAmbient.getAsBoolean(),
                        config.effects_visible.getAsBoolean(), effectIcons
                ));
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

    public int getMaxAirSupply(ItemStack item) {
        int baseMaxAir = item.getOrDefault(ComponentTypesRegistry.MAX_AIR.get(), 0);
        int additionalAir = item.getOrDefault(ComponentTypesRegistry.ADDITIONAL_AIR.get(), 0);
        return baseMaxAir + additionalAir;
    }

    public void tickAirSupply(ItemStack item, Player player) {
        if (player.tickCount % 20 != 0) return;
        ensureAirComponentsInitialized(item);
        int currentAir = item.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0);
        int maxAir = getMaxAirSupply(item);
        var config = BreathersConfig.SERVER_CONFIG;
        if (PlayerUtils.checkPlayerConditions(player) || PlayerUtils.checkAntiConditions(player)) {
            if (currentAir < maxAir) {
                var toAddToCurrent = currentAir + config.airSupplyRecovery.getAsInt();
                if (toAddToCurrent > maxAir) toAddToCurrent = maxAir;
                item.set(ComponentTypesRegistry.AIR_RESERVE.get(), ensureMinimumValue(toAddToCurrent));
            }
            return;
        }
        if (!isItemEquipped(player)) return;
        if (currentAir > 0) {
            item.set(ComponentTypesRegistry.AIR_RESERVE.get(), ensureMinimumValue(currentAir - 1));
        }
        boolean under100Air = currentAir <= 100;
        if (under100Air && currentAir > 0) {
            player.displayClientMessage(Component.translatable("airSupply.cobblemon_breathers.supply_low").withStyle(ChatFormatting.RED), true);
        }
        if (currentAir == 0) {
            player.displayClientMessage(Component.translatable("airSupply.cobblemon_breathers.supply_depleted").withStyle(ChatFormatting.RED), true);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return CobblemonBreathers.AIR_SUPPLY_BAR_COLOR;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        ensureAirComponentsInitialized(itemStack);
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0);
        int maxAir = getMaxAirSupply(itemStack);
        if (maxAir <= 0) return 0;
        return Math.round((float)currentAir * 13.0F / (float)maxAir);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        ensureAirComponentsInitialized(itemStack);
        int currentAir = itemStack.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0);
        int maxAir = getMaxAirSupply(itemStack);
        list.add(Component.translatable("airSupply.cobblemon_breathers.current_air", currentAir, maxAir).withStyle(ChatFormatting.BLUE));
    }

    private void ensureAirComponentsInitialized(ItemStack itemStack) {
        int maxAir = itemStack.getOrDefault(ComponentTypesRegistry.MAX_AIR.get(), 0);
        int baseMaxAir = getBaseMaxAir(mk);
        if (!itemStack.has(ComponentTypesRegistry.MAX_AIR.get()) || maxAir <= 0 || maxAir != baseMaxAir) {
            maxAir = baseMaxAir;
            if (maxAir > 0) {
                itemStack.set(ComponentTypesRegistry.MAX_AIR.get(), maxAir);
            }
        }

        if (!itemStack.has(ComponentTypesRegistry.AIR_RESERVE.get()) && maxAir > 0) {
            itemStack.set(ComponentTypesRegistry.AIR_RESERVE.get(), maxAir);
        }

        if (!itemStack.has(ComponentTypesRegistry.ADDITIONAL_AIR.get())) {
            itemStack.set(ComponentTypesRegistry.ADDITIONAL_AIR.get(), 0);
        }
    }
}
