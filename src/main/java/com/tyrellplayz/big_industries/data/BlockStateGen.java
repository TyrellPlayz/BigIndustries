package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGen extends BlockStateProvider {

    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BigIndustries.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                simpleBlock(BIBlocks.ORE.get(metal).get());
                simpleBlock(BIBlocks.DEEPSLATE_ORE.get(metal).get());
                simpleBlock(BIBlocks.RAW_STORAGE_BLOCK.get(metal).get());
            }
            simpleBlock(BIBlocks.STORAGE_BLOCK.get(metal).get());
        }

        simpleBlock(BIBlocks.BLAST_BRICK.get());
    }

}
