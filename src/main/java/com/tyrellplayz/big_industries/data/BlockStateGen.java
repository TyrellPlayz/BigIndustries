package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
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
