package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BITags;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BIBlockTagsProvider extends BlockTagsProvider {

    public BIBlockTagsProvider(DataGenerator generatorIn, String modId, ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_COPPER)
                .add(BIBlocks.COPPER_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_LEAD)
                .add(BIBlocks.LEAD_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_TIN)
                .add(BIBlocks.TIN_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_SILVER)
                .add(BIBlocks.SILVER_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_NICKEL)
                .add(BIBlocks.NICKEL_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_STEEL)
                .add(BIBlocks.STEEL_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_BRONZE)
                .add(BIBlocks.BRONZE_BLOCK);
        this.getOrCreateBuilder(BITags.Blocks.STORAGE_BLOCKS_INVAR)
                .add(BIBlocks.INVAR_BLOCK);

        this.getOrCreateBuilder(BITags.Blocks.ORES_COPPER)
                .add(BIBlocks.COPPER_ORE);
        this.getOrCreateBuilder(BITags.Blocks.ORES_LEAD)
                .add(BIBlocks.LEAD_ORE);
        this.getOrCreateBuilder(BITags.Blocks.ORES_TIN)
                .add(BIBlocks.TIN_ORE);
        this.getOrCreateBuilder(BITags.Blocks.ORES_SILVER)
                .add(BIBlocks.SILVER_ORE);
        this.getOrCreateBuilder(BITags.Blocks.ORES_NICKEL)
                .add(BIBlocks.NICKEL_ORE);
    }
}
