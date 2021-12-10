package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import static com.tyrellplayz.big_industries.common.BITags.*;

import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BigIndustries.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        // Forge Tags
        // Tin
        this.tag(Blocks.STORAGE_BLOCKS_TIN).add(BIBlocks.TIN_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_TIN).add(BIBlocks.RAW_TIN_BLOCK.get());
        this.tag(Blocks.ORES_TIN).add(BIBlocks.TIN_ORE.get(), BIBlocks.DEEPSLATE_TIN_ORE.get());

        // Lead
        this.tag(Blocks.STORAGE_BLOCKS_LEAD).add(BIBlocks.LEAD_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_LEAD).add(BIBlocks.RAW_LEAD_BLOCK.get());
        this.tag(Blocks.ORES_LEAD).add(BIBlocks.LEAD_ORE.get(), BIBlocks.DEEPSLATE_LEAD_ORE.get());

        // Aluminium
        this.tag(Blocks.STORAGE_BLOCKS_ALUMINIUM).add(BIBlocks.ALUMINIUM_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_ALUMINIUM).add(BIBlocks.RAW_ALUMINIUM_BLOCK.get());
        this.tag(Blocks.ORES_ALUMINIUM).add(BIBlocks.ALUMINIUM_ORE.get(), BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

        // Silver
        this.tag(Blocks.STORAGE_BLOCKS_SILVER).add(BIBlocks.SILVER_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_SILVER).add(BIBlocks.RAW_SILVER_BLOCK.get());
        this.tag(Blocks.ORES_SILVER).add(BIBlocks.SILVER_ORE.get(), BIBlocks.DEEPSLATE_SILVER_ORE.get());

        // Steel
        this.tag(Blocks.STORAGE_BLOCKS_STEEL).add(BIBlocks.STEEL_BLOCK.get());

        // Minecraft Tags
        this.tag(Tags.Blocks.ORES).addTags(Blocks.ORES_TIN,Blocks.ORES_LEAD,Blocks.ORES_ALUMINIUM,Blocks.ORES_SILVER);
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(Blocks.STORAGE_BLOCKS_TIN,Blocks.STORAGE_BLOCKS_LEAD,Blocks.STORAGE_BLOCKS_ALUMINIUM,Blocks.STORAGE_BLOCKS_SILVER,
                Blocks.STORAGE_BLOCKS_STEEL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTags(Blocks.ORES_TIN, Blocks.ORES_LEAD, Blocks.ORES_ALUMINIUM)
                .add(BIBlocks.TIN_BLOCK.get(), BIBlocks.RAW_TIN_BLOCK.get())
                .add(BIBlocks.LEAD_BLOCK.get(), BIBlocks.RAW_LEAD_BLOCK.get())
                .add(BIBlocks.ALUMINIUM_BLOCK.get(), BIBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(BIBlocks.SILVER_BLOCK.get(), BIBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(BIBlocks.STEEL_BLOCK.get())
                .add(BIBlocks.BLAST_BRICK.get()).add(BIBlocks.BLAST_FURNACE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BIBlocks.TIN_BLOCK.get(), BIBlocks.TIN_ORE.get(), BIBlocks.RAW_TIN_BLOCK.get())
                .add(BIBlocks.LEAD_BLOCK.get(), BIBlocks.LEAD_ORE.get(), BIBlocks.RAW_LEAD_BLOCK.get())
                .add(BIBlocks.ALUMINIUM_BLOCK.get(), BIBlocks.ALUMINIUM_ORE.get(), BIBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(BIBlocks.SILVER_BLOCK.get(), BIBlocks.SILVER_ORE.get(), BIBlocks.RAW_SILVER_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BIBlocks.DEEPSLATE_TIN_ORE.get())
                .add(BIBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(BIBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(BIBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(BIBlocks.TIN_ORE.get(),BIBlocks.LEAD_ORE.get(),BIBlocks.ALUMINIUM_ORE.get(),BIBlocks.SILVER_ORE.get());
        this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(BIBlocks.DEEPSLATE_TIN_ORE.get(),BIBlocks.DEEPSLATE_LEAD_ORE.get(),BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                BIBlocks.DEEPSLATE_SILVER_ORE.get());
    }

    @Override
    public String getName() {
        return "Block Tags: "+modId;
    }
}
