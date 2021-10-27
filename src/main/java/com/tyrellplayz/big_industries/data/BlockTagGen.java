package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.ModTags;
import com.tyrellplayz.big_industries.core.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BigIndustries.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Forge Tags
        // Tin
        this.tag(ModTags.Blocks.STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        this.tag(ModTags.Blocks.ORES_TIN).add(ModBlocks.TIN_ORE.get());
        this.tag(Tags.Blocks.ORES).add(ModBlocks.TIN_ORE.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(ModBlocks.TIN_BLOCK.get(), ModBlocks.RAW_TIN_BLOCK.get());

        // Lead
        this.tag(ModTags.Blocks.STORAGE_BLOCKS_LEAD).add(ModBlocks.LEAD_BLOCK.get());
        this.tag(ModTags.Blocks.ORES_LEAD).add(ModBlocks.LEAD_ORE.get());
        this.tag(Tags.Blocks.ORES).add(ModBlocks.LEAD_ORE.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(ModBlocks.LEAD_BLOCK.get(), ModBlocks.RAW_LEAD_BLOCK.get());

        // Steel
        this.tag(ModTags.Blocks.STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(ModBlocks.STEEL_BLOCK.get());

        // Minecraft Tags
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TIN_BLOCK.get(), ModBlocks.TIN_ORE.get(), ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get(), ModBlocks.LEAD_ORE.get(), ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_BLOCK.get(), ModBlocks.TIN_ORE.get(), ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get(), ModBlocks.LEAD_ORE.get(), ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get());
    }

    @Override
    public String getName() {
        return "Block Tags: "+modId;
    }
}
