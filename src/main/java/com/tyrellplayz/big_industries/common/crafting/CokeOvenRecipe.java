package com.tyrellplayz.big_industries.common.crafting;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIRecipesSerializers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class CokeOvenRecipe extends BICookingRecipe {

    public static final IRecipeType<CokeOvenRecipe> TYPE = IRecipeType.register(BigIndustries.MOD_ID+":coking");

    public CokeOvenRecipe(ResourceLocation id, String group, Ingredient ingredient, int ingredientAmount, ItemStack result, float experience, int cookTime) {
        super(TYPE, id, group, ingredient, ingredientAmount, result, experience, cookTime);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return BIRecipesSerializers.COKING;
    }
}
