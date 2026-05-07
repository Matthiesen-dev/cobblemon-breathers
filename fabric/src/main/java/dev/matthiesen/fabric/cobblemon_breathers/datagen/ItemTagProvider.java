package dev.matthiesen.fabric.cobblemon_breathers.datagen;

import dev.matthiesen.common.cobblemon_breathers.datagen.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider<Item> {
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        ModTags.TAG_ITEMS.forEach((tag, item) -> {
            getOrCreateTagBuilder(tag).add(item);
        });
        ModTags.TAG_LISTS.forEach((parent, children) -> {
            getOrCreateTagBuilder(parent).addTag(children);
        });
    }
}
