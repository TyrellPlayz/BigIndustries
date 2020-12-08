package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.crafting.BICookingRecipeSerializer;
import com.tyrellplayz.big_industries.common.crafting.CokeOvenRecipe;
import com.tyrellplayz.zlib.registry.RecipeSerializerRegistry;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BIRecipesSerializers extends RecipeSerializerRegistry {

    public static final IRecipeSerializer<CokeOvenRecipe> COKING = register(BigIndustries.MOD_ID+":coking", new BICookingRecipeSerializer<>(CokeOvenRecipe::new, 200));

}
