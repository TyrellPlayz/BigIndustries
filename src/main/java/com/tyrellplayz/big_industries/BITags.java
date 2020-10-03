package com.tyrellplayz.big_industries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class BITags {

    public static class Items {

        // Block items
        public static final INamedTag<Item> STORAGE_BLOCKS_COPPER = makeForgeItemTag("storage_blocks/copper");
        public static final INamedTag<Item> STORAGE_BLOCKS_LEAD = makeForgeItemTag("storage_blocks/lead");
        public static final INamedTag<Item> STORAGE_BLOCKS_TIN = makeForgeItemTag("storage_blocks/tin");
        public static final INamedTag<Item> STORAGE_BLOCKS_SILVER = makeForgeItemTag("storage_blocks/silver");
        public static final INamedTag<Item> STORAGE_BLOCKS_NICKEL = makeForgeItemTag("storage_blocks/nickel");
        public static final INamedTag<Item> STORAGE_BLOCKS_STEEL = makeForgeItemTag("storage_blocks/steel");
        public static final INamedTag<Item> STORAGE_BLOCKS_BRONZE = makeForgeItemTag("storage_blocks/bronze");
        public static final INamedTag<Item> STORAGE_BLOCKS_INVAR = makeForgeItemTag("storage_blocks/invar");

        // Ore items
        public static final INamedTag<Item> ORES_COPPER = makeForgeItemTag("ores/copper");
        public static final INamedTag<Item> ORES_LEAD = makeForgeItemTag("ores/lead");
        public static final INamedTag<Item> ORES_TIN = makeForgeItemTag("ores/tin");
        public static final INamedTag<Item> ORES_SILVER = makeForgeItemTag("ores/silver");
        public static final INamedTag<Item> ORES_NICKEL = makeForgeItemTag("ores/nickel");

        // Ingot items
        public static final INamedTag<Item> INGOTS_COPPER = makeForgeItemTag("ingots/copper");
        public static final INamedTag<Item> INGOTS_LEAD = makeForgeItemTag("ingots/lead");
        public static final INamedTag<Item> INGOTS_TIN = makeForgeItemTag("ingots/tin");
        public static final INamedTag<Item> INGOTS_SILVER = makeForgeItemTag("ingots/silver");
        public static final INamedTag<Item> INGOTS_NICKEL = makeForgeItemTag("ingots/nickel");
        public static final INamedTag<Item> INGOTS_STEEL = makeForgeItemTag("ingots/steel");
        public static final INamedTag<Item> INGOTS_BRONZE = makeForgeItemTag("ingots/bronze");
        public static final INamedTag<Item> INGOTS_INVAR = makeForgeItemTag("ingots/invar");
        // Nugget items
        public static final INamedTag<Item> NUGGETS_COPPER = makeForgeItemTag("nuggets/copper");
        public static final INamedTag<Item> NUGGETS_LEAD = makeForgeItemTag("nuggets/lead");
        public static final INamedTag<Item> NUGGETS_TIN = makeForgeItemTag("nuggets/tin");
        public static final INamedTag<Item> NUGGETS_SILVER = makeForgeItemTag("nuggets/silver");
        public static final INamedTag<Item> NUGGETS_NICKEL = makeForgeItemTag("nuggets/nickel");
        public static final INamedTag<Item> NUGGETS_STEEL = makeForgeItemTag("nuggets/steel");
        public static final INamedTag<Item> NUGGETS_BRONZE = makeForgeItemTag("nuggets/bronze");
        public static final INamedTag<Item> NUGGETS_INVAR = makeForgeItemTag("nuggets/invar");

        // Dust Items
        public static final INamedTag<Item> DUSTS_COPPER = makeForgeItemTag("dusts/copper");
        public static final INamedTag<Item> DUSTS_LEAD = makeForgeItemTag("dusts/lead");
        public static final INamedTag<Item> DUSTS_TIN = makeForgeItemTag("dusts/tin");
        public static final INamedTag<Item> DUSTS_SILVER = makeForgeItemTag("dusts/silver");
        public static final INamedTag<Item> DUSTS_NICKEL = makeForgeItemTag("dusts/nickel");
        public static final INamedTag<Item> DUSTS_STEEL = makeForgeItemTag("dusts/steel");
        public static final INamedTag<Item> DUSTS_BRONZE = makeForgeItemTag("dusts/bronze");
        public static final INamedTag<Item> DUSTS_INVAR = makeForgeItemTag("dusts/invar");
        public static final INamedTag<Item> DUSTS_COAL = makeForgeItemTag("dusts/coal");
        public static final INamedTag<Item> DUSTS_IRON = makeForgeItemTag("dusts/iron");
        public static final INamedTag<Item> DUSTS_GOLD = makeForgeItemTag("dusts/gold");
        public static final INamedTag<Item> DUSTS_DIAMOND = makeForgeItemTag("dusts/diamond");
        public static final INamedTag<Item> DUSTS_EMERALD = makeForgeItemTag("dusts/emerald");
        public static final INamedTag<Item> DUSTS_OBSIDIAN = makeForgeItemTag("dusts/obsidian");

        private static INamedTag<Item> makeForgeItemTag(String name) {
            return ItemTags.makeWrapperTag("forge:"+name);
        }

        private static INamedTag<Item> makeItemTag(String name) {
            return ItemTags.makeWrapperTag(BigIndustries.MOD_ID+":"+name);
        }

    }

    public static class Blocks {

        public static final INamedTag<Block> STORAGE_BLOCKS_COPPER = makeForgeBlockTag("storage_blocks/copper");
        public static final INamedTag<Block> STORAGE_BLOCKS_LEAD = makeForgeBlockTag("storage_blocks/lead");
        public static final INamedTag<Block> STORAGE_BLOCKS_TIN = makeForgeBlockTag("storage_blocks/tin");
        public static final INamedTag<Block> STORAGE_BLOCKS_SILVER = makeForgeBlockTag("storage_blocks/silver");
        public static final INamedTag<Block> STORAGE_BLOCKS_NICKEL = makeForgeBlockTag("storage_blocks/nickel");
        public static final INamedTag<Block> STORAGE_BLOCKS_STEEL = makeForgeBlockTag("storage_blocks/steel");
        public static final INamedTag<Block> STORAGE_BLOCKS_BRONZE = makeForgeBlockTag("storage_blocks/bronze");
        public static final INamedTag<Block> STORAGE_BLOCKS_INVAR = makeForgeBlockTag("storage_blocks/invar");
        public static final INamedTag<Block> ORES_COPPER = makeForgeBlockTag("ores/copper");
        public static final INamedTag<Block> ORES_LEAD = makeForgeBlockTag("ores/lead");
        public static final INamedTag<Block> ORES_TIN = makeForgeBlockTag("ores/tin");
        public static final INamedTag<Block> ORES_SILVER = makeForgeBlockTag("ores/silver");
        public static final INamedTag<Block> ORES_NICKEL = makeForgeBlockTag("ores/nickel");

        private static INamedTag<Block> makeForgeBlockTag(String name) {
            return BlockTags.makeWrapperTag("forge:"+name);
        }

        private static INamedTag<Block> makeBlockTag(String name) {
            return BlockTags.makeWrapperTag(BigIndustries.MOD_ID+":"+name);
        }

    }

}
