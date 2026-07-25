package dev.matthiesen.cobblemon_breathers.fabric.datagen;

import dev.matthiesen.cobblemon_breathers.common.datagen.RecipeRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.concurrent.CompletableFuture;

public final class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        RecipeRegistry.RECIPES.forEach(recipe ->
                recipe.save(exporter));

        RecipeRegistry.SMITHING_RECIPES.forEach(
                (name, recipe) ->
                        recipe.save(exporter, name + "_smithing"));
    }
}
