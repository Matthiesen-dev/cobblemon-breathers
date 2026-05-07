package dev.matthiesen.common.cobblemon_breathers.datagen;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> BREATHERS = createTag("breathers");

        @SuppressWarnings("SameParameterValue")
        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Constants.modResource(name));
        }
    }

    public static class ACCESSORIES {
        public static final TagKey<Item> FACE = createTag("face");
        public static final TagKey<Item> HAT = createTag("hat");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("accessories", name));
        }
    }

    public static class MINECRAFT {
        public static final TagKey<Enchantment> IN_ENCHANTING_TABLE = createEnchantmentTag("in_enchanting_table");

        @SuppressWarnings("SameParameterValue")
        private static TagKey<Enchantment> createEnchantmentTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace(name));
        }
    }

    public static Map<TagKey<Item>, Item[]> TAG_ITEMS = new HashMap<>();
    public static Map<TagKey<Item>, TagKey<Item>> TAG_LISTS = new HashMap<>();
    public static Map<TagKey<Enchantment>, ResourceKey<Enchantment>> ENCHANTMENTS = new HashMap<>();

    static {
        TAG_ITEMS.put(Items.BREATHERS, ItemRegistry.REBREATHERS.stream().map(Supplier::get).toArray(Item[]::new));

        TAG_LISTS.put(ACCESSORIES.FACE, Items.BREATHERS);
        TAG_LISTS.put(ACCESSORIES.HAT, Items.BREATHERS);

        ENCHANTMENTS.put(MINECRAFT.IN_ENCHANTING_TABLE, EnchantmentsRegistry.BREATHER_UPGRADE);
    }
}
