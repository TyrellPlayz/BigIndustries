package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGen extends BlockStateProvider {

    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BigIndustries.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Tin
        simpleBlock(ModBlocks.TIN_BLOCK.get());
        simpleBlock(ModBlocks.TIN_ORE.get());
        simpleBlock(ModBlocks.RAW_TIN_BLOCK.get());

        // Lead
        simpleBlock(ModBlocks.LEAD_BLOCK.get());
        simpleBlock(ModBlocks.LEAD_ORE.get());
        simpleBlock(ModBlocks.RAW_LEAD_BLOCK.get());

        // Aluminium
        simpleBlock(ModBlocks.ALUMINIUM_BLOCK.get());
        simpleBlock(ModBlocks.ALUMINIUM_ORE.get());
        simpleBlock(ModBlocks.RAW_ALUMINIUM_BLOCK.get());

        // Steel
        simpleBlock(ModBlocks.STEEL_BLOCK.get());

        simpleBlock(ModBlocks.BLAST_BRICK.get());
    }

}
