package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BITags;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BIItemTagsProvider extends ItemTagsProvider {

    public BIItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, String modId, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        // Copper
        this.copy(BITags.Blocks.STORAGE_BLOCKS_COPPER, BITags.Items.STORAGE_BLOCKS_COPPER);
        this.copy(BITags.Blocks.ORES_COPPER, BITags.Items.ORES_COPPER);
        this.getOrCreateBuilder(BITags.Items.INGOTS_COPPER)
                .add(BIItems.COPPER_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_COPPER)
                .add(BIItems.COPPER_NUGGET);

        // Lead
        this.copy(BITags.Blocks.STORAGE_BLOCKS_LEAD, BITags.Items.STORAGE_BLOCKS_LEAD);
        this.copy(BITags.Blocks.ORES_LEAD, BITags.Items.ORES_LEAD);
        this.getOrCreateBuilder(BITags.Items.INGOTS_LEAD)
                .add(BIItems.LEAD_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_LEAD)
                .add(BIItems.LEAD_NUGGET);

        // Tin
        this.copy(BITags.Blocks.STORAGE_BLOCKS_TIN, BITags.Items.STORAGE_BLOCKS_TIN);
        this.copy(BITags.Blocks.ORES_TIN, BITags.Items.ORES_TIN);
        this.getOrCreateBuilder(BITags.Items.INGOTS_TIN)
                .add(BIItems.TIN_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_TIN)
                .add(BIItems.TIN_NUGGET);

        // Silver
        this.copy(BITags.Blocks.STORAGE_BLOCKS_SILVER, BITags.Items.STORAGE_BLOCKS_SILVER);
        this.copy(BITags.Blocks.ORES_SILVER, BITags.Items.ORES_SILVER);
        this.getOrCreateBuilder(BITags.Items.INGOTS_SILVER)
                .add(BIItems.SILVER_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_SILVER)
                .add(BIItems.SILVER_NUGGET);

        // Nickel
        this.copy(BITags.Blocks.STORAGE_BLOCKS_NICKEL, BITags.Items.STORAGE_BLOCKS_NICKEL);
        this.copy(BITags.Blocks.ORES_NICKEL, BITags.Items.ORES_NICKEL);
        this.getOrCreateBuilder(BITags.Items.INGOTS_NICKEL)
                .add(BIItems.NICKEL_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_NICKEL)
                .add(BIItems.NICKEL_NUGGET);

        // Steel
        this.copy(BITags.Blocks.STORAGE_BLOCKS_STEEL, BITags.Items.STORAGE_BLOCKS_STEEL);
        this.getOrCreateBuilder(BITags.Items.INGOTS_STEEL)
                .add(BIItems.STEEL_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_STEEL)
                .add(BIItems.STEEL_NUGGET);

        // Bronze
        this.copy(BITags.Blocks.STORAGE_BLOCKS_BRONZE, BITags.Items.STORAGE_BLOCKS_BRONZE);
        this.getOrCreateBuilder(BITags.Items.INGOTS_BRONZE)
                .add(BIItems.BRONZE_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_BRONZE)
                .add(BIItems.BRONZE_NUGGET);

        // Invar
        this.copy(BITags.Blocks.STORAGE_BLOCKS_INVAR, BITags.Items.STORAGE_BLOCKS_INVAR);
        this.getOrCreateBuilder(BITags.Items.INGOTS_INVAR)
                .add(BIItems.INVAR_INGOT);
        this.getOrCreateBuilder(BITags.Items.NUGGETS_INVAR)
                .add(BIItems.INVAR_NUGGET);
    }
}
