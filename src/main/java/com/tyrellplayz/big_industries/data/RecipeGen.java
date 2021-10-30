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
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    private static final ImmutableList<ItemLike> TIN_SMELTABLES = ImmutableList.of(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get(), ModItems.CRUSHED_TIN.get());
    private static final ImmutableList<ItemLike> LEAD_SMELTABLES = ImmutableList.of(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get(), ModItems.CRUSHED_LEAD.get());
    private static final ImmutableList<ItemLike> ALUMINIUM_SMELTABLES = ImmutableList.of(ModBlocks.ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get(), ModItems.CRUSHED_ALUMINIUM.get());
    private static final ImmutableList<ItemLike> STEEL_SMELTABLES = ImmutableList.of(ModItems.CRUSHED_STEEL.get());

    public RecipeGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Tin
        metalRecipe(consumer,ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN,ModItems.TIN_NUGGET.get(),ModTags.Items.NUGGETS_TIN,ModBlocks.TIN_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_TIN,ModItems.RAW_TIN.get(),ModTags.Items.RAW_ORES_TIN,ModBlocks.RAW_TIN_BLOCK.get(),ModTags.Items.RAW_STORAGE_BLOCKS_TIN);
        //nineStorageRecipe(consumer, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN, ModBlocks.TIN_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.TIN_NUGGET.get(),ModTags.Items.NUGGETS_TIN, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCK.get(),simpleItemName(ModItems.RAW_TIN.get())+"_from_block");
        ShapedRecipeBuilder.shaped(ModItems.TIN_GEAR.get()).define('T',ModTags.Items.INGOTS_TIN).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_TIN)).save(consumer);
        oreSmelting(consumer,TIN_SMELTABLES, ModItems.TIN_INGOT.get(),0F,200);
        oreBlasting(consumer,TIN_SMELTABLES, ModItems.TIN_INGOT.get(),0F,100);
        // Lead
        metalRecipe(consumer,ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD,ModItems.LEAD_NUGGET.get(),ModTags.Items.NUGGETS_LEAD,ModBlocks.LEAD_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_LEAD,ModItems.RAW_LEAD.get(),ModTags.Items.RAW_ORES_LEAD,ModBlocks.RAW_LEAD_BLOCK.get(),ModTags.Items.RAW_STORAGE_BLOCKS_LEAD);
        //nineStorageRecipe(consumer, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD, ModBlocks.LEAD_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.LEAD_NUGGET.get(),ModTags.Items.NUGGETS_LEAD, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_LEAD.get(), ModBlocks.RAW_LEAD_BLOCK.get(),simpleItemName(ModItems.RAW_LEAD.get())+"_from_block");
        ShapedRecipeBuilder.shaped(ModItems.LEAD_GEAR.get()).define('T',ModTags.Items.INGOTS_LEAD).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_LEAD)).save(consumer);
        oreSmelting(consumer,LEAD_SMELTABLES, ModItems.LEAD_INGOT.get(),0F,200);
        oreBlasting(consumer,LEAD_SMELTABLES, ModItems.LEAD_INGOT.get(),0F,100);
        // Aluminium
        metalRecipe(consumer,ModItems.ALUMINIUM_INGOT.get(),ModTags.Items.INGOTS_ALUMINIUM,ModItems.ALUMINIUM_NUGGET.get(),ModTags.Items.NUGGETS_ALUMINIUM,ModBlocks.ALUMINIUM_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_ALUMINIUM,ModItems.RAW_ALUMINIUM.get(),ModTags.Items.RAW_ORES_ALUMINIUM,ModBlocks.RAW_ALUMINIUM_BLOCK.get(),ModTags.Items.RAW_STORAGE_BLOCKS_ALUMINIUM);
        //nineStorageRecipe(consumer, ModItems.ALUMINIUM_INGOT.get(),ModTags.Items.INGOTS_ALUMINIUM, ModBlocks.ALUMINIUM_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_ALUMINIUM,simpleItemName(ModItems.ALUMINIUM_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.ALUMINIUM_NUGGET.get(),ModTags.Items.NUGGETS_ALUMINIUM, ModItems.ALUMINIUM_INGOT.get(),ModTags.Items.INGOTS_ALUMINIUM,simpleItemName(ModItems.ALUMINIUM_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_ALUMINIUM.get(), ModBlocks.RAW_ALUMINIUM_BLOCK.get(),simpleItemName(ModItems.RAW_ALUMINIUM.get())+"_from_block");
        oreSmelting(consumer,ALUMINIUM_SMELTABLES, ModItems.ALUMINIUM_INGOT.get(),0F,200);
        oreBlasting(consumer,ALUMINIUM_SMELTABLES, ModItems.ALUMINIUM_INGOT.get(),0F,100);
        // Steel
        metalRecipeAlloy(consumer,ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL,ModItems.STEEL_NUGGET.get(),ModTags.Items.NUGGETS_STEEL,ModBlocks.STEEL_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_STEEL);
        //nineStorageRecipe(consumer, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL, ModBlocks.STEEL_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.STEEL_NUGGET.get(),ModTags.Items.NUGGETS_STEEL, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_nugget");
        ShapedRecipeBuilder.shaped(ModItems.STEEL_GEAR.get()).define('T',ModTags.Items.INGOTS_STEEL).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(ModTags.Items.INGOTS_STEEL)).save(consumer);
        oreSmelting(consumer,STEEL_SMELTABLES, ModItems.STEEL_INGOT.get(),0F,200);
        oreBlasting(consumer,STEEL_SMELTABLES, ModItems.STEEL_INGOT.get(),0F,100);
    }

    protected void metalRecipe(Consumer<FinishedRecipe> consumer, Item ingotItem, Tag<Item> ingotTag, Item nuggetItem, Tag<Item> nuggetTag, Block storageBlock, Tag<Item> storageBlockTag, Item rawItem, Tag<Item> rawTag, Block rawStorageBlock, Tag<Item> rawStorageBlockTag) {
        metalRecipeAlloy(consumer,ingotItem,ingotTag,nuggetItem,nuggetTag,storageBlock,storageBlockTag);
        nineStorageRecipe(consumer,rawItem,rawTag,rawStorageBlock,rawStorageBlockTag,simpleItemName(rawItem)+"_from_block");
    }

    protected void metalRecipeAlloy(Consumer<FinishedRecipe> consumer, Item ingotItem, Tag<Item> ingotTag, Item nuggetItem, Tag<Item> nuggetTag, Block storageBlock, Tag<Item> storageBlockTag) {
        nineStorageRecipe(consumer,ingotItem,ingotTag,storageBlock,storageBlockTag,simpleItemName(ingotItem)+"_from_block");
        nineStorageRecipe(consumer,nuggetItem,nuggetTag,ingotItem,ingotTag,simpleItemName(ingotItem)+"_from_nugget");
    }

    protected void nineStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike block, String saveName) {
        ShapelessRecipeBuilder.shapeless(item,9).requires(Ingredient.of(block)).group(simpleItemName(item)).unlockedBy("has_item",has(item)).save(consumer,modLoc(saveName));
        ShapedRecipeBuilder.shaped(block).define('#',Ingredient.of(item)).pattern("###").pattern("###").pattern("###").unlockedBy("has_item",has(item)).save(consumer);
    }

    protected void nineStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike mainItem, Tag<Item> mainItemTag, ItemLike secondaryItem, Tag<Item> secondaryItemTag, String saveName) {
        ShapelessRecipeBuilder.shapeless(mainItem,9).requires(secondaryItemTag).group(simpleItemName(mainItem)).unlockedBy("has_item",has(mainItemTag)).save(consumer,modLoc(saveName));
        ShapedRecipeBuilder.shaped(secondaryItem).define('#',mainItemTag).pattern("###").pattern("###").pattern("###").unlockedBy("has_item",has(mainItemTag)).save(consumer);
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
