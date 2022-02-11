package com.tyrellplayz.big_industries.crafting;

import com.tyrellplayz.big_industries.core.BIRecipeSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BlastFurnaceRecipe extends AbstractCookingRecipe {

    public BlastFurnaceRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(RecipeType.BLAST_FURNACE, id, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
       // return BIRecipeSerializers.BLAST_FURNACE.get();
    }
}
