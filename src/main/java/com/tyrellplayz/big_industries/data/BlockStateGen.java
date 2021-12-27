package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGen extends BlockStateProvider {

    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BigIndustries.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Tin
        simpleBlock(BIBlocks.TIN_BLOCK.get());
        simpleBlock(BIBlocks.TIN_ORE.get());
        simpleBlock(BIBlocks.DEEPSLATE_TIN_ORE.get());
        simpleBlock(BIBlocks.RAW_TIN_BLOCK.get());

        // Lead
        simpleBlock(BIBlocks.LEAD_BLOCK.get());
        simpleBlock(BIBlocks.LEAD_ORE.get());
        simpleBlock(BIBlocks.DEEPSLATE_LEAD_ORE.get());
        simpleBlock(BIBlocks.RAW_LEAD_BLOCK.get());

        // Aluminium
        simpleBlock(BIBlocks.ALUMINIUM_BLOCK.get());
        simpleBlock(BIBlocks.ALUMINIUM_ORE.get());
        simpleBlock(BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        simpleBlock(BIBlocks.RAW_ALUMINIUM_BLOCK.get());

        // Silver
        simpleBlock(BIBlocks.SILVER_BLOCK.get());
        simpleBlock(BIBlocks.SILVER_ORE.get());
        simpleBlock(BIBlocks.DEEPSLATE_SILVER_ORE.get());
        simpleBlock(BIBlocks.RAW_SILVER_BLOCK.get());

        // Steel
        simpleBlock(BIBlocks.STEEL_BLOCK.get());

        simpleBlock(BIBlocks.BLAST_BRICK.get());
    }

}
