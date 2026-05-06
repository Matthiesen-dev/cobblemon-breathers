package dev.matthiesen.common.cobblemon_breathers.registry;

import com.cobblemon.mod.common.CobblemonItems;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.data.recipes.RecipeProvider.has;

@SuppressWarnings("unused")
public class RecipeRegistry {
    public static List<RecipeBuilder> RECIPES = new ArrayList<>();
    public static Map<String, SmithingTransformRecipeBuilder> SMITHING_RECIPES = new HashMap<>();

    public static RecipeBuilder REBREATHER_MK1 = registerCraftingRecipe(
            newShapedRecipe(ItemRegistry.REBREATHER_MK1.get())
                    .define('s', Items.STRING)
                    .define('c', Items.COPPER_INGOT)
                    .define('w', CobblemonItems.WATER_STONE)
                    .define('b', Items.GLASS_BOTTLE)
                    .pattern(" s ")
                    .pattern("cwc")
                    .pattern(" b ")
                    .showNotification(true)
                    .unlockedBy("has_water_stone", has(CobblemonItems.WATER_STONE))
    );

    public static RecipeBuilder REBREATHER_MK2 = registerCraftingRecipe(
            newShapedRecipe(ItemRegistry.REBREATHER_MK2.get())
                    .define('s', CobblemonItems.MYSTIC_WATER)
                    .define('d', Items.DIAMOND)
                    .define('w', ItemRegistry.REBREATHER_MK1.get())
                    .define('b', Items.BUCKET)
                    .pattern(" s ")
                    .pattern("dwd")
                    .pattern(" b ")
                    .showNotification(true)
                    .unlockedBy("has_rebreather_mk1", has(ItemRegistry.REBREATHER_MK1.get()))
    );

    public static SmithingTransformRecipeBuilder REBREATHER_MK3 = registerSmithingRecipe(
            "rebreather_mk3",
            newSmithingRecipe(
                    ItemRegistry.REBREATHER_MK3.get(),
                    Items.CONDUIT,
                    ItemRegistry.REBREATHER_MK2.get(),
                    Items.NETHERITE_INGOT)
                    .unlocks("has_rebreather_mk2", has(ItemRegistry.REBREATHER_MK2.get()))
    );

    private static RecipeBuilder registerCraftingRecipe(RecipeBuilder recipe) {
        RECIPES.add(recipe);
        return recipe;
    }

    @SuppressWarnings("SameParameterValue")
    private static SmithingTransformRecipeBuilder registerSmithingRecipe(String name, SmithingTransformRecipeBuilder recipe) {
        SMITHING_RECIPES.put(name, recipe);
        return recipe;
    }

    private static ShapedRecipeBuilder newShapedRecipe(ItemLike output) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .group("cobblemon_breathers");
    }

    @SuppressWarnings("SameParameterValue")
    private static SmithingTransformRecipeBuilder newSmithingRecipe(
            Item output,
            ItemLike template,
            ItemLike base,
            ItemLike upgradeMaterial
    ) {
        return SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(base),
                Ingredient.of(upgradeMaterial),
                RecipeCategory.TOOLS,
                output
        );
    }
}
