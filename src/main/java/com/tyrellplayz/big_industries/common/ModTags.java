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
        public static final Tag.Named<Block> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");
        public static final Tag.Named<Block> ORES_TIN = forgeTag("ores/tin");

        // Lead
        public static final Tag.Named<Block> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final Tag.Named<Block> ORES_LEAD = forgeTag("ores/lead");

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

        // Tin
        public static final Tag.Named<Item> INGOTS_TIN = forgeTag("ingots/tin");
        public static final Tag.Named<Item> NUGGETS_TIN = forgeTag("nuggets/tin");
        public static final Tag.Named<Item> CRUSHED_TIN = forgeTag("crushed/tin");
        public static final Tag.Named<Item> GEARS_TIN = forgeTag("gears/tin");

        public static final Tag.Named<Item> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");
        public static final Tag.Named<Item> ORES_TIN = forgeTag("ores/tin");

        // Lead
        public static final Tag.Named<Item> INGOTS_LEAD = forgeTag("ingots/lead");
        public static final Tag.Named<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
        public static final Tag.Named<Item> CRUSHED_LEAD = forgeTag("crushed/lead");
        public static final Tag.Named<Item> GEARS_LEAD = forgeTag("gears/lead");

        public static final Tag.Named<Item> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final Tag.Named<Item> ORES_LEAD = forgeTag("ores/lead");

        // Steel
        public static final Tag.Named<Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final Tag.Named<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final Tag.Named<Item> CRUSHED_STEEL = forgeTag("crushed/steel");
        public static final Tag.Named<Item> GEARS_STEEL = forgeTag("gears/steel");

        public static final Tag.Named<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        public static final Tag.Named<Item> CRUSHED_COAL = forgeTag("crushed/coal");
        public static final Tag.Named<Item> CRUSHED_IRON = forgeTag("crushed/iron");
        public static final Tag.Named<Item> CRUSHED_COPPER = forgeTag("crushed/copper");
        public static final Tag.Named<Item> CRUSHED_GOLD = forgeTag("crushed/gold");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Fluids {

        public static final Tag.Named<Fluid> OIL = forgeTag("oil");

        private static Tags.IOptionalNamedTag<Fluid> tag(String name) {
            return FluidTags.createOptional(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Fluid> forgeTag(String name) {
            return FluidTags.createOptional(new ResourceLocation("forge", name));
        }

    }

}
