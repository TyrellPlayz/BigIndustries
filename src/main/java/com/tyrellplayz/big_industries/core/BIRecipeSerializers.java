package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.crafting.BlastFurnaceRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BIRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BigIndustries.MOD_ID);

   // public static final RegistryObject<SimpleCookingSerializer<BlastFurnaceRecipe>> BLAST_FURNACE = register("blast_furnace", new SimpleCookingSerializer<>(BlastFurnaceRecipe::new,100));

    //public static <T extends RecipeSerializer<?>> RegistryObject<T> register(String registryName, T serializer) {
    //    return REGISTER.register(registryName,serializer);
    //}

}
