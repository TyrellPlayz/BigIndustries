package com.tyrellplayz.big_industries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;

public class BITags {

    public static class Blocks {

        public static final Map<Metals,Tag.Named<Block>> METAL_ORES = new HashMap<>();
        public static final Map<Metals,Tag.Named<Block>> METAL_RAW_STORAGE_BLOCKS = new HashMap<>();
        public static final Map<Metals,Tag.Named<Block>> METAL_STORAGE_BLOCKS = new HashMap<>();

        static {
            for (Metals metal : Metals.values()) {
                if(!metal.isAlloy()) {
                    METAL_ORES.put(metal,forgeTag("ores/"+metal));
                    METAL_RAW_STORAGE_BLOCKS.put(metal,forgeTag("raw_storage_blocks/"+metal));
                }
                METAL_STORAGE_BLOCKS.put(metal,forgeTag("storage_blocks/"+metal));
            }
        }

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final Map<Metals,Tag.Named<Item>> METAL_RAW = new HashMap<>();
        public static final Map<Metals,Tag.Named<Item>> METAL_INGOTS = new HashMap<>();
        public static final Map<Metals,Tag.Named<Item>> METAL_NUGGETS = new HashMap<>();
        public static final Map<Metals,Tag.Named<Item>> METAL_CRUSHED = new HashMap<>();

        public static final Map<Metals,Tag.Named<Item>> METAL_ORES = new HashMap<>();
        public static final Map<Metals,Tag.Named<Item>> METAL_RAW_STORAGE_BLOCKS = new HashMap<>();
        public static final Map<Metals,Tag.Named<Item>> METAL_STORAGE_BLOCKS = new HashMap<>();

        static {
            for (Metals metal : Metals.values()) {
                if(!metal.isAlloy()) {
                    METAL_RAW.put(metal,forgeTag("raw_ores/"+metal));

                    METAL_ORES.put(metal,forgeTag("ores/"+metal));
                    METAL_RAW_STORAGE_BLOCKS.put(metal,forgeTag("raw_storage_blocks/"+metal));
                }
                METAL_INGOTS .put(metal,forgeTag("ingots/"+metal));
                METAL_NUGGETS.put(metal,forgeTag("nuggets/"+metal));
                METAL_CRUSHED.put(metal,forgeTag("dusts/"+metal));

                METAL_STORAGE_BLOCKS.put(metal,forgeTag("storage_blocks/"+metal));
            }
        }

        // Global
        public static final Tag.Named<Item> RAW_ORES = forgeTag("raw_ores");
        public static final Tag.Named<Item> DUSTS = forgeTag("dusts");
        public static final Tag.Named<Item> INGOTS = forgeTag("ingots");
        public static final Tag.Named<Item> NUGGETS = forgeTag("nuggets");

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
