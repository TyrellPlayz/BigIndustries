package com.tyrellplayz.big_industries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;

public class BITags {

    public static class Blocks {

        public static final Map<Metals, TagKey<Block>> METAL_ORES = new HashMap<>();
        public static final Map<Metals,TagKey<Block>> METAL_RAW_STORAGE_BLOCKS = new HashMap<>();
        public static final Map<Metals,TagKey<Block>> METAL_STORAGE_BLOCKS = new HashMap<>();

        static {
            for (Metals metal : Metals.values()) {
                if(!metal.isAlloy()) {
                    METAL_ORES.put(metal,forgeTag("ores/"+metal));
                    METAL_RAW_STORAGE_BLOCKS.put(metal,forgeTag("raw_storage_blocks/"+metal));
                }
                METAL_STORAGE_BLOCKS.put(metal,forgeTag("storage_blocks/"+metal));
            }
        }

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final Map<Metals,TagKey<Item>> METAL_RAW = new HashMap<>();
        public static final Map<Metals,TagKey<Item>> METAL_INGOTS = new HashMap<>();
        public static final Map<Metals,TagKey<Item>> METAL_NUGGETS = new HashMap<>();
        public static final Map<Metals,TagKey<Item>> METAL_CRUSHED = new HashMap<>();

        public static final Map<Metals,TagKey<Item>> METAL_ORES = new HashMap<>();
        public static final Map<Metals,TagKey<Item>> METAL_RAW_STORAGE_BLOCKS = new HashMap<>();
        public static final Map<Metals,TagKey<Item>> METAL_STORAGE_BLOCKS = new HashMap<>();

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
        public static final TagKey<Item> RAW_ORES = forgeTag("raw_ores");
        public static final TagKey<Item> DUSTS = forgeTag("dusts");
        public static final TagKey<Item> INGOTS = forgeTag("ingots");
        public static final TagKey<Item> NUGGETS = forgeTag("nuggets");

        public static final TagKey<Item> DUSTS_COAL = forgeTag("dusts/coal");
        public static final TagKey<Item> DUSTS_IRON = forgeTag("dusts/iron");
        public static final TagKey<Item> DUSTS_COPPER = forgeTag("dusts/copper");
        public static final TagKey<Item> DUSTS_GOLD = forgeTag("dusts/gold");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(BigIndustries.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

}
