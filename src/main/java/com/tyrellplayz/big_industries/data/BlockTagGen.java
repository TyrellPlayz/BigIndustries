package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import static com.tyrellplayz.big_industries.common.ModTags.*;
import com.tyrellplayz.big_industries.core.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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
        this.tag(Blocks.STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_TIN).add(ModBlocks.RAW_TIN_BLOCK.get());
        this.tag(Blocks.ORES_TIN).add(ModBlocks.TIN_ORE.get(),ModBlocks.DEEPSLATE_TIN_ORE.get());

        // Lead
        this.tag(Blocks.STORAGE_BLOCKS_LEAD).add(ModBlocks.LEAD_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_LEAD).add(ModBlocks.RAW_LEAD_BLOCK.get());
        this.tag(Blocks.ORES_LEAD).add(ModBlocks.LEAD_ORE.get(),ModBlocks.DEEPSLATE_LEAD_ORE.get());

        // Aluminium
        this.tag(Blocks.STORAGE_BLOCKS_ALUMINIUM).add(ModBlocks.ALUMINIUM_BLOCK.get());
        this.tag(Blocks.RAW_STORAGE_BLOCKS_ALUMINIUM).add(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        this.tag(Blocks.ORES_ALUMINIUM).add(ModBlocks.ALUMINIUM_ORE.get(),ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

        // Steel
        this.tag(Blocks.STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());

        // Minecraft Tags
        this.tag(Tags.Blocks.ORES).addTags(Blocks.ORES_TIN,Blocks.ORES_LEAD,Blocks.ORES_ALUMINIUM);
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(Blocks.STORAGE_BLOCKS_TIN,Blocks.STORAGE_BLOCKS_LEAD,Blocks.STORAGE_BLOCKS_STEEL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTags(Blocks.ORES_TIN, Blocks.ORES_LEAD, Blocks.ORES_ALUMINIUM)
                .add(ModBlocks.TIN_BLOCK.get(), ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get(), ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.ALUMINIUM_BLOCK.get(), ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_BLOCK.get(), ModBlocks.TIN_ORE.get(), ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get(), ModBlocks.LEAD_ORE.get(), ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.ALUMINIUM_BLOCK.get(), ModBlocks.ALUMINIUM_ORE.get(), ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.ALUMINIUM_ORE.get());
        this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
    }

    @Override
    public String getName() {
        return "Block Tags: "+modId;
    }
}
