package com.tyrellplayz.big_industries.data;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.BITags;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
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

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    private static final ImmutableList<ItemLike> TIN_SMELTABLES = ImmutableList.of(BIBlocks.TIN_ORE.get(),BIBlocks.DEEPSLATE_TIN_ORE.get(), BIItems.RAW_TIN.get(), BIItems.TIN_DUST.get());
    private static final ImmutableList<ItemLike> LEAD_SMELTABLES = ImmutableList.of(BIBlocks.LEAD_ORE.get(),BIBlocks.DEEPSLATE_LEAD_ORE.get(), BIItems.RAW_LEAD.get(), BIItems.LEAD_DUST.get());
    private static final ImmutableList<ItemLike> ALUMINIUM_SMELTABLES = ImmutableList.of(BIBlocks.ALUMINIUM_ORE.get(),BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), BIItems.RAW_ALUMINIUM.get(), BIItems.ALUMINIUM_DUST.get());
    private static final ImmutableList<ItemLike> SILVER_SMELTABLES = ImmutableList.of(BIBlocks.SILVER_ORE.get(),BIBlocks.DEEPSLATE_SILVER_ORE.get(), BIItems.RAW_SILVER.get(), BIItems.SILVER_DUST.get());
    private static final ImmutableList<ItemLike> STEEL_SMELTABLES = ImmutableList.of(BIItems.STEEL_DUST.get());

    public RecipeGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Tin
        metalRecipe(consumer, BIItems.TIN_INGOT.get(), BITags.Items.INGOTS_TIN, BIItems.TIN_NUGGET.get(), BITags.Items.NUGGETS_TIN, BIBlocks.TIN_BLOCK.get(), BITags.Items.STORAGE_BLOCKS_TIN, BIItems.RAW_TIN.get(), BITags.Items.RAW_ORES_TIN, BIBlocks.RAW_TIN_BLOCK.get(), BITags.Items.RAW_STORAGE_BLOCKS_TIN);
        //nineStorageRecipe(consumer, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN, ModBlocks.TIN_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.TIN_NUGGET.get(),ModTags.Items.NUGGETS_TIN, ModItems.TIN_INGOT.get(),ModTags.Items.INGOTS_TIN,simpleItemName(ModItems.TIN_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCK.get(),simpleItemName(ModItems.RAW_TIN.get())+"_from_block");
        ShapedRecipeBuilder.shaped(BIItems.TIN_GEAR.get()).define('T', BITags.Items.INGOTS_TIN).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(BITags.Items.INGOTS_TIN)).save(consumer);
        oreSmelting(consumer,TIN_SMELTABLES, BIItems.TIN_INGOT.get(),0F,200);
        oreBlasting(consumer,TIN_SMELTABLES, BIItems.TIN_INGOT.get(),0F,100);
        // Lead
        metalRecipe(consumer, BIItems.LEAD_INGOT.get(), BITags.Items.INGOTS_LEAD, BIItems.LEAD_NUGGET.get(), BITags.Items.NUGGETS_LEAD, BIBlocks.LEAD_BLOCK.get(), BITags.Items.STORAGE_BLOCKS_LEAD, BIItems.RAW_LEAD.get(), BITags.Items.RAW_ORES_LEAD, BIBlocks.RAW_LEAD_BLOCK.get(), BITags.Items.RAW_STORAGE_BLOCKS_LEAD);
        //nineStorageRecipe(consumer, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD, ModBlocks.LEAD_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.LEAD_NUGGET.get(),ModTags.Items.NUGGETS_LEAD, ModItems.LEAD_INGOT.get(),ModTags.Items.INGOTS_LEAD,simpleItemName(ModItems.LEAD_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_LEAD.get(), ModBlocks.RAW_LEAD_BLOCK.get(),simpleItemName(ModItems.RAW_LEAD.get())+"_from_block");
        ShapedRecipeBuilder.shaped(BIItems.LEAD_GEAR.get()).define('T', BITags.Items.INGOTS_LEAD).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(BITags.Items.INGOTS_LEAD)).save(consumer);
        oreSmelting(consumer,LEAD_SMELTABLES, BIItems.LEAD_INGOT.get(),0F,200);
        oreBlasting(consumer,LEAD_SMELTABLES, BIItems.LEAD_INGOT.get(),0F,100);
        // Aluminium
        metalRecipe(consumer, BIItems.ALUMINIUM_INGOT.get(), BITags.Items.INGOTS_ALUMINIUM, BIItems.ALUMINIUM_NUGGET.get(), BITags.Items.NUGGETS_ALUMINIUM, BIBlocks.ALUMINIUM_BLOCK.get(), BITags.Items.STORAGE_BLOCKS_ALUMINIUM, BIItems.RAW_ALUMINIUM.get(), BITags.Items.RAW_ORES_ALUMINIUM, BIBlocks.RAW_ALUMINIUM_BLOCK.get(), BITags.Items.RAW_STORAGE_BLOCKS_ALUMINIUM);
        //nineStorageRecipe(consumer, ModItems.ALUMINIUM_INGOT.get(),ModTags.Items.INGOTS_ALUMINIUM, ModBlocks.ALUMINIUM_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_ALUMINIUM,simpleItemName(ModItems.ALUMINIUM_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.ALUMINIUM_NUGGET.get(),ModTags.Items.NUGGETS_ALUMINIUM, ModItems.ALUMINIUM_INGOT.get(),ModTags.Items.INGOTS_ALUMINIUM,simpleItemName(ModItems.ALUMINIUM_INGOT.get())+"_from_nugget");
        //nineStorageRecipe(consumer, ModItems.RAW_ALUMINIUM.get(), ModBlocks.RAW_ALUMINIUM_BLOCK.get(),simpleItemName(ModItems.RAW_ALUMINIUM.get())+"_from_block");
        oreSmelting(consumer,ALUMINIUM_SMELTABLES, BIItems.ALUMINIUM_INGOT.get(),0F,200);
        oreBlasting(consumer,ALUMINIUM_SMELTABLES, BIItems.ALUMINIUM_INGOT.get(),0F,100);
        // Silver
        metalRecipe(consumer, BIItems.SILVER_INGOT.get(), BITags.Items.INGOTS_SILVER, BIItems.SILVER_NUGGET.get(), BITags.Items.NUGGETS_SILVER, BIBlocks.SILVER_BLOCK.get(), BITags.Items.STORAGE_BLOCKS_SILVER, BIItems.RAW_SILVER.get(), BITags.Items.RAW_ORES_SILVER, BIBlocks.RAW_SILVER_BLOCK.get(), BITags.Items.RAW_STORAGE_BLOCKS_SILVER);
        oreSmelting(consumer,SILVER_SMELTABLES, BIItems.SILVER_INGOT.get(),0F,200);
        oreBlasting(consumer,SILVER_SMELTABLES, BIItems.SILVER_INGOT.get(),0F,100);
        // Steel
        metalRecipeAlloy(consumer, BIItems.STEEL_INGOT.get(), BITags.Items.INGOTS_STEEL, BIItems.STEEL_NUGGET.get(), BITags.Items.NUGGETS_STEEL, BIBlocks.STEEL_BLOCK.get(), BITags.Items.STORAGE_BLOCKS_STEEL);
        //nineStorageRecipe(consumer, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL, ModBlocks.STEEL_BLOCK.get(),ModTags.Items.STORAGE_BLOCKS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_block");
        //nineStorageRecipe(consumer, ModItems.STEEL_NUGGET.get(),ModTags.Items.NUGGETS_STEEL, ModItems.STEEL_INGOT.get(),ModTags.Items.INGOTS_STEEL,simpleItemName(ModItems.STEEL_INGOT.get())+"_from_nugget");
        ShapedRecipeBuilder.shaped(BIItems.STEEL_GEAR.get()).define('T', BITags.Items.INGOTS_STEEL).define('S', Items.STICK).pattern(" T ").pattern("TST").pattern(" T ").group("gear").unlockedBy("has_item",has(BITags.Items.INGOTS_STEEL)).save(consumer);
        oreSmelting(consumer,STEEL_SMELTABLES, BIItems.STEEL_INGOT.get(),0F,200);
        oreBlasting(consumer,STEEL_SMELTABLES, BIItems.STEEL_INGOT.get(),0F,100);
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
