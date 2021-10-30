package com.tyrellplayz.big_industries.data;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.ModTags;
import com.tyrellplayz.big_industries.core.ModBlocks;
import com.tyrellplayz.big_industries.core.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    private static final ImmutableList<ItemLike> TIN_SMELTABLES = ImmutableList.of(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get(), ModItems.CRUSHED_TIN.get());
    private static final ImmutableList<ItemLike> LEAD_SMELTABLES = ImmutableList.of(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get(), ModItems.CRUSHED_LEAD.get());
    private static final ImmutableList<ItemLike> STEEL_SMELTABLES = ImmutableList.of(ModItems.CRUSHED_STEEL.get());

    public RecipeGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // TIn
        nineBlockStorageRecipe(consumer, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN, ModBlocks.TIN_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_block");
        nineBlockStorageRecipe(consumer, ModItems.TIN_NUGGET.get(),ModTags.Items.NUGGETS_TIN, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_nugget");
        nineBlockStorageRecipe(consumer, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCK.get(),simpleItemName(ModItems.RAW_TIN.get())+"_from_block");
        ShapedRecipeBuilder.shaped(ModItems.TIN_GEAR.get()).define('T',ModTags.Items.INGOTS_TIN).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_TIN)).save(consumer);
        oreSmelting(consumer,TIN_SMELTABLES, ModItems.TIN_INGOT.get(),0F,200);
        oreBlasting(consumer,TIN_SMELTABLES, ModItems.TIN_INGOT.get(),0F,100);
        // Lead
        nineBlockStorageRecipe(consumer, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD, ModBlocks.LEAD_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_block");
        nineBlockStorageRecipe(consumer, ModItems.LEAD_NUGGET.get(),ModTags.Items.NUGGETS_LEAD, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_nugget");
        nineBlockStorageRecipe(consumer, ModItems.RAW_LEAD.get(), ModBlocks.RAW_LEAD_BLOCK.get(),simpleItemName(ModItems.RAW_LEAD.get())+"_from_block");
        ShapedRecipeBuilder.shaped(ModItems.LEAD_GEAR.get()).define('T',ModTags.Items.INGOTS_LEAD).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_LEAD)).save(consumer);
        oreSmelting(consumer,LEAD_SMELTABLES, ModItems.LEAD_INGOT.get(),0F,200);
        oreBlasting(consumer,LEAD_SMELTABLES, ModItems.LEAD_INGOT.get(),0F,100);
        // Steel
        nineBlockStorageRecipe(consumer, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL, ModBlocks.STEEL_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_block");
        nineBlockStorageRecipe(consumer, ModItems.STEEL_NUGGET.get(),ModTags.Items.NUGGETS_STEEL, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_nugget");
        ShapedRecipeBuilder.shaped(ModItems.STEEL_GEAR.get()).define('T',ModTags.Items.INGOTS_STEEL).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_STEEL)).save(consumer);
        oreSmelting(consumer,STEEL_SMELTABLES, ModItems.STEEL_INGOT.get(),0F,200);
        oreBlasting(consumer,STEEL_SMELTABLES, ModItems.STEEL_INGOT.get(),0F,100);
    }

    protected void nineBlockStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike block, String saveName) {
        ShapelessRecipeBuilder.shapeless(item,9).requires(Ingredient.of(block)).group(simpleItemName(item)).unlockedBy("has_item",has(item)).save(consumer,modLoc(saveName));
        ShapedRecipeBuilder.shaped(block).define('#',Ingredient.of(item)).pattern("###").pattern("###").pattern("###").unlockedBy("has_item",has(item)).save(consumer);
    }

    protected void nineBlockStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike resultItem, Tag<Item> itemTag, ItemLike resultBlock, Tag<Item> blockTag, String saveName) {
        ShapelessRecipeBuilder.shapeless(resultItem,9).requires(Ingredient.of(blockTag)).group(simpleItemName(resultItem)).unlockedBy("has_item",has(itemTag)).save(consumer,modLoc(saveName));
        ShapedRecipeBuilder.shaped(resultBlock).define('#',Ingredient.of(itemTag)).pattern("###").pattern("###").pattern("###").unlockedBy("has_item",has(itemTag)).save(consumer);
    }

    public void oreSmelting(Consumer<FinishedRecipe> consumer, ImmutableList<ItemLike> input, Item result, float experience, int cookingTime) {
        oreCooking(consumer,input,result,experience,cookingTime,SimpleCookingSerializer.SMELTING_RECIPE,"_from_smelting");
    }

    public void oreBlasting(Consumer<FinishedRecipe> consumer, ImmutableList<ItemLike> input, Item result, float experience, int cookingTime) {
        oreCooking(consumer,input,result,experience,cookingTime,SimpleCookingSerializer.BLASTING_RECIPE,"_from_blasting");
    }

    public void oreCooking(Consumer<FinishedRecipe> consumer, ImmutableList<ItemLike> input, Item result, float experience, int cookingTime, SimpleCookingSerializer<?> serializer, String cookingTypeName) {
        input.forEach(itemLike -> {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemLike),result,experience,cookingTime,serializer).group(simpleItemName(result)).unlockedBy("has_item",has(itemLike.asItem())).save(consumer,modLoc(simpleItemName(result)+cookingTypeName+"_"+simpleItemName(itemLike)));
        });
    }

    private ResourceLocation modLoc(String path) {
        return new ResourceLocation(BigIndustries.MOD_ID,path);
    }

    private String simpleItemName(ItemLike item) {
        return item.asItem().getRegistryName().getPath();
    }

}
