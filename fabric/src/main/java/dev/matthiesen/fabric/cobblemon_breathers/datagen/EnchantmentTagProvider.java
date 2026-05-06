package dev.matthiesen.fabric.cobblemon_breathers.datagen;

import dev.matthiesen.common.cobblemon_breathers.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTagProvider extends FabricTagProvider<Enchantment> {
    public EnchantmentTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        ModTags.ENCHANTMENTS.forEach((tag, enchantment) -> {
            getOrCreateTagBuilder(tag).add(enchantment);
        });
    }
}
