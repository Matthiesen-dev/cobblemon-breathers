package dev.matthiesen.fabric.cobblemon_breathers.datagen;

import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import dev.matthiesen.common.cobblemon_breathers.registry.ModTags;
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
        getOrCreateTagBuilder(ModTags.Items.BREATHERS).add(ItemRegistry.REBREATHER_MK1.get()).add(ItemRegistry.REBREATHER_MK2.get()).add(ItemRegistry.REBREATHER_MK3.get());
        getOrCreateTagBuilder(ModTags.ACCESSORIES.FACE).addTag(ModTags.Items.BREATHERS);
        getOrCreateTagBuilder(ModTags.ACCESSORIES.HEAD).addTag(ModTags.Items.BREATHERS);
    }
}
