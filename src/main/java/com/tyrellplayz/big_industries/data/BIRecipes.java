package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BITags;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import com.tyrellplayz.zlib.data.RecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.function.Consumer;

public class BIRecipes extends RecipeProvider {

    public BIRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        createMetalRecipes(BIBlocks.COPPER_BLOCK, BIItems.COPPER_INGOT, BIItems.COPPER_NUGGET, BITags.Items.STORAGE_BLOCKS_COPPER,BITags.Items.INGOTS_COPPER,BITags.Items.NUGGETS_COPPER,BITags.Items.ORES_COPPER,consumer);
        createMetalRecipes(BIBlocks.LEAD_BLOCK, BIItems.LEAD_INGOT, BIItems.LEAD_NUGGET,BITags.Items.STORAGE_BLOCKS_LEAD,BITags.Items.INGOTS_LEAD,BITags.Items.NUGGETS_LEAD,BITags.Items.ORES_LEAD,consumer);
        createMetalRecipes(BIBlocks.TIN_BLOCK, BIItems.TIN_INGOT, BIItems.TIN_NUGGET,BITags.Items.STORAGE_BLOCKS_TIN,BITags.Items.INGOTS_TIN,BITags.Items.NUGGETS_TIN,BITags.Items.ORES_TIN,consumer);
        createMetalRecipes(BIBlocks.SILVER_BLOCK, BIItems.SILVER_INGOT, BIItems.SILVER_NUGGET,BITags.Items.STORAGE_BLOCKS_SILVER,BITags.Items.INGOTS_SILVER,BITags.Items.NUGGETS_SILVER,BITags.Items.ORES_SILVER,consumer);
        createMetalRecipes(BIBlocks.NICKEL_BLOCK, BIItems.NICKEL_INGOT, BIItems.NICKEL_NUGGET,BITags.Items.STORAGE_BLOCKS_NICKEL,BITags.Items.INGOTS_NICKEL,BITags.Items.NUGGETS_NICKEL,BITags.Items.ORES_NICKEL,consumer);
        createMetalRecipes(BIBlocks.STEEL_BLOCK, BIItems.STEEL_INGOT, BIItems.STEEL_NUGGET,BITags.Items.STORAGE_BLOCKS_STEEL,BITags.Items.INGOTS_STEEL,BITags.Items.NUGGETS_STEEL,BITags.Items.DUSTS_STEEL,consumer);
        createMetalRecipes(BIBlocks.BRONZE_BLOCK, BIItems.BRONZE_INGOT, BIItems.BRONZE_NUGGET,BITags.Items.STORAGE_BLOCKS_BRONZE,BITags.Items.INGOTS_BRONZE,BITags.Items.NUGGETS_BRONZE,BITags.Items.DUSTS_BRONZE,consumer);
        createMetalRecipes(BIBlocks.INVAR_BLOCK, BIItems.INVAR_INGOT, BIItems.INVAR_NUGGET,BITags.Items.STORAGE_BLOCKS_INVAR,BITags.Items.INGOTS_INVAR,BITags.Items.NUGGETS_INVAR,BITags.Items.DUSTS_INVAR,consumer);

        createMetalSmeltingRecipes(BITags.Items.ORES_COPPER, BIItems.COPPER_INGOT,0.7F, 200,consumer);
        createMetalSmeltingRecipes(BITags.Items.ORES_LEAD, BIItems.LEAD_INGOT,0.7F, 200,consumer);
        createMetalSmeltingRecipes(BITags.Items.ORES_TIN, BIItems.TIN_INGOT,0.7F, 200,consumer);
        createMetalSmeltingRecipes(BITags.Items.ORES_SILVER, BIItems.SILVER_INGOT,0.7F, 200,consumer);
        createMetalSmeltingRecipes(BITags.Items.ORES_NICKEL, BIItems.NICKEL_INGOT,1.0F, 200,consumer);

        // Gravel Clay Balls
        ShapedRecipeBuilder.shapedRecipe(BIItems.GRAVEL_CLAY_BALL,4).patternLine("CGC").patternLine("GCG").patternLine("CGC").key('C', Items.CLAY_BALL).key('G', Blocks.GRAVEL).addCriterion("hasItem",hasItem(Items.CLAY_BALL)).build(consumer);
        // Gravel Clay Ball to Coke Brick
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BIItems.GRAVEL_CLAY_BALL),BIItems.COKE_BRICK,0.3F, 300).addCriterion("has_item",hasItem(BIItems.GRAVEL_CLAY_BALL)).build(consumer);
        // Coke Bricks
        ShapedRecipeBuilder.shapedRecipe(BIBlocks.COKE_BRICKS).patternLine("## ").patternLine("## ").key('#',BIItems.COKE_BRICK).addCriterion("has_item",hasItem(BIItems.COKE_BRICK)).build(consumer);
        // Coke Controller
        ShapedRecipeBuilder.shapedRecipe(BIBlocks.COKE_OVEN_CONTROLLER).patternLine(" C ").patternLine("CFC").patternLine(" C ").key('C',BIItems.COKE_BRICK).key('F',Blocks.FURNACE).addCriterion("has_item",hasItem(BIItems.COKE_BRICK)).build(consumer);
    }

    protected void createMetalRecipes(Block block, Item ingotItem, Item nuggetItem, ITag.INamedTag<Item> blockTag, ITag.INamedTag<Item> ingotTag, ITag.INamedTag<Item> nuggetTag, ITag.INamedTag<Item> itemToUnlockTag, Consumer<IFinishedRecipe> consumer) {
        // Block to Ingots
        ShapelessRecipeBuilder.shapelessRecipe(ingotItem,9).addIngredient(blockTag).addCriterion("has_item", hasItem(itemToUnlockTag)).build(consumer,new ResourceLocation(ingotItem.getRegistryName().getNamespace(),ingotItem.getRegistryName().getPath()+"_from_block"));
        // Ingot to Nuggets
        ShapelessRecipeBuilder.shapelessRecipe(nuggetItem,9).addIngredient(ingotTag).addCriterion("has_item", hasItem(itemToUnlockTag)).build(consumer);
        // Nuggets to Ingot
        ShapedRecipeBuilder.shapedRecipe(ingotItem).patternLine("###").patternLine("###").patternLine("###").key('#',nuggetTag).addCriterion("has_item", hasItem(itemToUnlockTag)).build(consumer,new ResourceLocation(ingotItem.getRegistryName().getNamespace(),ingotItem.getRegistryName().getPath()+"_from_nuggets"));
        // Ingots to Block
        ShapedRecipeBuilder.shapedRecipe(block).patternLine("###").patternLine("###").patternLine("###").key('#',ingotTag).addCriterion("has_item", hasItem(itemToUnlockTag)).build(consumer);
    }

    protected void createMetalSmeltingRecipes(ITag.INamedTag<Item> oreItemTag, Item ingotItem, float experienceIn, int cookingTimeIn, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(oreItemTag),ingotItem,experienceIn, cookingTimeIn).addCriterion("has_ore", hasItem(oreItemTag)).build(consumer);
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(oreItemTag),ingotItem,experienceIn, cookingTimeIn/2).addCriterion("has_ore", hasItem(oreItemTag)).build(consumer,new ResourceLocation(ingotItem.getRegistryName().getNamespace(),ingotItem.getRegistryName().getPath()+"_from_blasting"));
    }

}
