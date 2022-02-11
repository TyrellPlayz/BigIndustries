package com.tyrellplayz.big_industries.crafting;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

public class RecipeType {

    public static final net.minecraft.world.item.crafting.RecipeType<BlastFurnaceRecipe> BLAST_FURNACE = register("blast_furnace");

    private static <T extends Recipe<?>> net.minecraft.world.item.crafting.RecipeType<T> register(final String registryName) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(BigIndustries.MOD_ID, registryName), new net.minecraft.world.item.crafting.RecipeType<T>() {
            @Override
            public String toString() {
                return registryName;
            }
        });
    }

}
