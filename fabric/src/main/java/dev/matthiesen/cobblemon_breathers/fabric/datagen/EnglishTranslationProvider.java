package dev.matthiesen.cobblemon_breathers.fabric.datagen;

import dev.matthiesen.cobblemon_breathers.common.registry.ItemRegistry;
import dev.matthiesen.cobblemon_breathers.common.datagen.TranslationsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public final class EnglishTranslationProvider extends FabricLanguageProvider {
    public EnglishTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
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
