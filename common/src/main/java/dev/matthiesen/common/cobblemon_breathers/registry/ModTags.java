package dev.matthiesen.common.cobblemon_breathers.registry;

import dev.matthiesen.common.cobblemon_breathers.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

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
        public static final TagKey<Item> HEAD = createTag("head");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("accessories", name));
        }
    }

    public static Map<TagKey<Item>, Item[]> TAG_ITEMS = new HashMap<>();
    public static Map<TagKey<Item>, TagKey<Item>> TAG_LISTS = new HashMap<>();

    static {
        TAG_ITEMS.put(Items.BREATHERS, new Item[]{
                ItemRegistry.REBREATHER_MK1.get(),
                ItemRegistry.REBREATHER_MK2.get(),
                ItemRegistry.REBREATHER_MK3.get(),
        });

        TAG_LISTS.put(ACCESSORIES.FACE, Items.BREATHERS);
        TAG_LISTS.put(ACCESSORIES.HEAD, Items.BREATHERS);
    }
}
