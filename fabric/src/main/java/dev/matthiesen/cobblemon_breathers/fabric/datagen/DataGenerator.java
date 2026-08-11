package dev.matthiesen.cobblemon_breathers.fabric.datagen;

import dev.matthiesen.cobblemon_breathers.common.datagen.EnchantmentsRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public final class DataGenerator implements DataGeneratorEntrypoint  {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishTranslationProvider::new);
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(EnchantmentTagProvider::new);
        pack.addProvider(RegistryDataGenerator::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.ENCHANTMENT, EnchantmentsRegistry::bootstrap);
    }
}
