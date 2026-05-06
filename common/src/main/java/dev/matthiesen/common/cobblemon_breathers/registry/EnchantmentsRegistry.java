package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.enchant.BreatherUpgradeEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

public class EnchantmentsRegistry {
    public static final ResourceKey<Enchantment> BREATHER_UPGRADE = ResourceKey.create(Registries.ENCHANTMENT, Constants.modResource("breather_upgrade"));

    public static void bootstrap(BootstrapContext<Enchantment> bootstrapContext) {
        var enchantments = bootstrapContext.lookup(Registries.ENCHANTMENT);
        var items = bootstrapContext.lookup(Registries.ITEM);

        register(bootstrapContext, BREATHER_UPGRADE,
                Enchantment.enchantment(Enchantment.definition(
                                items.getOrThrow(ModTags.Items.BREATHERS), // SupportedItems
                                items.getOrThrow(ModTags.Items.BREATHERS), // PrimaryItems
                                5, // Weight
                                3, // MaxLevel
                                Enchantment.dynamicCost(5, 7), // MinCost
                                Enchantment.dynamicCost(25, 9), // MaxCost
                                2, // AnvilCost
                                EquipmentSlotGroup.ANY // Slots
                        ))
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.IN_ENCHANTING_TABLE))
                        .withEffect(EnchantmentEffectComponents.TICK, new BreatherUpgradeEffect())
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.location()));
    }
}
