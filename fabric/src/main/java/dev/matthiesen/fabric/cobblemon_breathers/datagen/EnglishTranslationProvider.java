package dev.matthiesen.fabric.cobblemon_breathers.datagen;

import dev.matthiesen.common.cobblemon_breathers.registry.ItemRegistry;
import dev.matthiesen.common.cobblemon_breathers.datagen.TranslationsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class EnglishTranslationProvider extends FabricLanguageProvider {
    protected EnglishTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        for (var entry : ItemRegistry.EN_TRANSLATION_MAP.entrySet()) {
            translationBuilder.add("item.cobblemon_breathers." + entry.getKey(), entry.getValue());
        }
        for (var entry : TranslationsRegistry.EN_TRANSLATIONS.entrySet()) {
            translationBuilder.add(entry.getKey(), entry.getValue());
        }
    }
}
