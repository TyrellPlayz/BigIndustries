package com.tyrellplayz.big_industries.data;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIItems;
import com.tyrellplayz.zlib.util.ItemUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        for (Metals metals : Metals.values()) {
            if (!metals.isAlloy()) {
                nineStorageRecipe(consumer, metals.getRaw(), metals.getRawTag(), metals.getRawStorageBlock(), metals.getRawStorageBlockTag(),simpleItemName(metals.getRaw())+"_from_block");
                oreSmelting(consumer, metals.getSmeltables(), metals.getIngot(),0F,200);
                oreBlasting(consumer, metals.getSmeltables(), metals.getIngot(),0F,100);
            }
            // Ingots from Block
            nineStorageRecipe(consumer, metals.getIngot(), metals.getIngotTag(), metals.getStorageBlock(), metals.getStorageBlockTag(),simpleItemName(metals.getIngot())+"_from_block");
            // Ingot from Nuggets
            nineStorageRecipe(consumer, metals.getNugget(), metals.getNuggetTag(), metals.getIngot(), metals.getIngotTag(),simpleItemName(metals.getIngot())+"_from_nugget");
        }

        ShapedRecipeBuilder.shaped(BIItems.HAMMER.get()).define('T', Metals.TIN.getIngot()).define('I',Items.IRON_INGOT).define('S',Items.STICK).pattern("TIT").pattern("TST").pattern(" S ").unlockedBy("has_item",has(Metals.TIN.getIngot())).save(consumer);
    }

    protected void metalRecipe(Consumer<FinishedRecipe> consumer, Item ingotItem, TagKey<Item> ingotTag, Item nuggetItem, TagKey<Item> nuggetTag, Block storageBlock, TagKey<Item> storageBlockTag, Item rawItem, TagKey<Item> rawTag, Block rawStorageBlock, TagKey<Item> rawStorageBlockTag) {
        metalRecipeAlloy(consumer,ingotItem,ingotTag,nuggetItem,nuggetTag,storageBlock,storageBlockTag);
        nineStorageRecipe(consumer,rawItem,rawTag,rawStorageBlock,rawStorageBlockTag,simpleItemName(rawItem)+"_from_block");
    }

    protected void metalRecipeAlloy(Consumer<FinishedRecipe> consumer, Item ingotItem, TagKey<Item> ingotTag, Item nuggetItem, TagKey<Item> nuggetTag, Block storageBlock, TagKey<Item> storageBlockTag) {
        nineStorageRecipe(consumer,ingotItem,ingotTag,storageBlock,storageBlockTag,simpleItemName(ingotItem)+"_from_block");
        nineStorageRecipe(consumer,nuggetItem,nuggetTag,ingotItem,ingotTag,simpleItemName(ingotItem)+"_from_nugget");
    }

    protected void nineStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike block, String saveName) {
        ShapelessRecipeBuilder.shapeless(item,9).requires(Ingredient.of(block)).group(simpleItemName(item)).unlockedBy("has_item",has(item)).save(consumer,modLoc(saveName));
        ShapedRecipeBuilder.shaped(block).define('#',Ingredient.of(item)).pattern("###").pattern("###").pattern("###").unlockedBy("has_item",has(item)).save(consumer);
    }

    protected void nineStorageRecipe(Consumer<FinishedRecipe> consumer, ItemLike mainItem, TagKey<Item> mainItemTag, ItemLike secondaryItem, TagKey<Item> secondaryItemTag, String saveName) {
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
        return ItemUtil.simpleItemName(item);
    }

}
