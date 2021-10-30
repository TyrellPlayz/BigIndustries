package com.tyrellplayz.big_industries.common;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {

        // Tin
        public static final Tag.Named<Block> ORES_TIN = forgeTag("ores/tin");
        public static final Tag.Named<Block> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");

        // Lead
        public static final Tag.Named<Block> ORES_LEAD = forgeTag("ores/lead");
        public static final Tag.Named<Block> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");

        // Aluminium
        public static final Tag.Named<Block> ORES_ALUMINIUM = forgeTag("ores/aluminium");
        public static final Tag.Named<Block> STORAGE_BLOCKS_ALUMINIUM = forgeTag("storage_blocks/aluminium");

        // Steel
        public static final Tag.Named<Block> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        // Global
        public static final Tag.Named<Item> RAW_ORES = forgeTag("raw_ores");
        public static final Tag.Named<Item> DUSTS = forgeTag("dusts");
        public static final Tag.Named<Item> INGOTS = forgeTag("ingots");
        public static final Tag.Named<Item> NUGGETS = forgeTag("nuggets");

        // Tin
        public static final Tag.Named<Item> RAW_ORES_TIN = forgeTag("raw_ores/tin");
        public static final Tag.Named<Item> DUSTS_TIN = forgeTag("dusts/tin");
        public static final Tag.Named<Item> INGOTS_TIN = forgeTag("ingots/tin");
        public static final Tag.Named<Item> NUGGETS_TIN = forgeTag("nuggets/tin");
        public static final Tag.Named<Item> GEARS_TIN = forgeTag("gears/tin");

        public static final Tag.Named<Item> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");
        public static final Tag.Named<Item> ORES_TIN = forgeTag("ores/tin");

        // Lead
        public static final Tag.Named<Item> RAW_ORES_LEAD = forgeTag("raw_ores/lead");
        public static final Tag.Named<Item> DUSTS_LEAD = forgeTag("dusts/lead");
        public static final Tag.Named<Item> INGOTS_LEAD = forgeTag("ingots/lead");
        public static final Tag.Named<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
        public static final Tag.Named<Item> GEARS_LEAD = forgeTag("gears/lead");

        public static final Tag.Named<Item> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final Tag.Named<Item> ORES_LEAD = forgeTag("ores/lead");

        // Aluminium
        public static final Tag.Named<Item> RAW_ORES_ALUMINIUM = forgeTag("raw_ores/aluminium");
        public static final Tag.Named<Item> DUSTS_ALUMINIUM = forgeTag("dusts/aluminium");
        public static final Tag.Named<Item> INGOTS_ALUMINIUM = forgeTag("ingots/aluminium");
        public static final Tag.Named<Item> NUGGETS_ALUMINIUM = forgeTag("nuggets/aluminium");

        public static final Tag.Named<Item> STORAGE_BLOCKS_ALUMINIUM = forgeTag("storage_blocks/aluminium");
        public static final Tag.Named<Item> ORES_ALUMINIUM = forgeTag("ores/aluminium");

        // Steel
        public static final Tag.Named<Item> DUSTS_STEEL = forgeTag("dusts/steel");
        public static final Tag.Named<Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final Tag.Named<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final Tag.Named<Item> GEARS_STEEL = forgeTag("gears/steel");

        public static final Tag.Named<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        public static final Tag.Named<Item> DUSTS_COAL = forgeTag("dusts/coal");
        public static final Tag.Named<Item> DUSTS_IRON = forgeTag("dusts/iron");
        public static final Tag.Named<Item> DUSTS_COPPER = forgeTag("dusts/copper");
        public static final Tag.Named<Item> DUSTS_GOLD = forgeTag("dusts/gold");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

    }

}
