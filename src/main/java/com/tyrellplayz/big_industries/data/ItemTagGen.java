package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.tyrellplayz.big_industries.common.BITags.Blocks;
import static com.tyrellplayz.big_industries.common.BITags.Items;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, BigIndustries.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        // Tin
        this.tag(Items.RAW_ORES_TIN).add(BIItems.RAW_TIN.get());
        this.tag(Items.DUSTS_TIN).add(BIItems.TIN_DUST.get());
        this.tag(Items.INGOTS_TIN).add(BIItems.TIN_INGOT.get());
        this.tag(Items.NUGGETS_TIN).add(BIItems.TIN_NUGGET.get());
        this.tag(Items.GEARS_TIN).add(BIItems.TIN_GEAR.get());
        this.copy(Blocks.ORES_TIN, Items.ORES_TIN);
        this.copy(Blocks.STORAGE_BLOCKS_TIN,Items.STORAGE_BLOCKS_TIN);
        this.copy(Blocks.RAW_STORAGE_BLOCKS_TIN,Items.RAW_STORAGE_BLOCKS_TIN);

        // Lead
        this.tag(Items.RAW_ORES_LEAD).add(BIItems.RAW_LEAD.get());
        this.tag(Items.DUSTS_LEAD).add(BIItems.LEAD_DUST.get());
        this.tag(Items.INGOTS_LEAD).add(BIItems.LEAD_INGOT.get());
        this.tag(Items.NUGGETS_LEAD).add(BIItems.LEAD_NUGGET.get());
        this.tag(Items.GEARS_LEAD).add(BIItems.LEAD_GEAR.get());
        this.copy(Blocks.ORES_LEAD,Items.ORES_LEAD);
        this.copy(Blocks.STORAGE_BLOCKS_LEAD,Items.STORAGE_BLOCKS_LEAD);
        this.copy(Blocks.RAW_STORAGE_BLOCKS_LEAD,Items.RAW_STORAGE_BLOCKS_LEAD);

        // Aluminium
        this.tag(Items.RAW_ORES_ALUMINIUM).add(BIItems.RAW_ALUMINIUM.get());
        this.tag(Items.DUSTS_ALUMINIUM).add(BIItems.ALUMINIUM_DUST.get());
        this.tag(Items.INGOTS_ALUMINIUM).add(BIItems.ALUMINIUM_INGOT.get());
        this.tag(Items.NUGGETS_ALUMINIUM).add(BIItems.ALUMINIUM_NUGGET.get());
        this.copy(Blocks.ORES_ALUMINIUM, Items.ORES_ALUMINIUM);
        this.copy(Blocks.STORAGE_BLOCKS_ALUMINIUM, Items.STORAGE_BLOCKS_ALUMINIUM);
        this.copy(Blocks.RAW_STORAGE_BLOCKS_ALUMINIUM,Items.RAW_STORAGE_BLOCKS_ALUMINIUM);

        // Silver
        this.tag(Items.RAW_ORES_SILVER).add(BIItems.RAW_SILVER.get());
        this.tag(Items.DUSTS_SILVER).add(BIItems.SILVER_DUST.get());
        this.tag(Items.INGOTS_SILVER).add(BIItems.SILVER_INGOT.get());
        this.tag(Items.NUGGETS_SILVER).add(BIItems.SILVER_NUGGET.get());
        this.copy(Blocks.ORES_SILVER, Items.ORES_SILVER);
        this.copy(Blocks.STORAGE_BLOCKS_SILVER, Items.STORAGE_BLOCKS_SILVER);
        this.copy(Blocks.RAW_STORAGE_BLOCKS_SILVER,Items.RAW_STORAGE_BLOCKS_SILVER);

        // Steel
        this.tag(Items.DUSTS_STEEL).add(BIItems.STEEL_DUST.get());
        this.tag(Items.INGOTS_STEEL).add(BIItems.STEEL_INGOT.get());
        this.tag(Items.NUGGETS_STEEL).add(BIItems.STEEL_NUGGET.get());
        this.tag(Items.GEARS_STEEL).add(BIItems.STEEL_GEAR.get());
        this.copy(Blocks.STORAGE_BLOCKS_STEEL,Items.STORAGE_BLOCKS_STEEL);

        // Vil
        this.tag(Items.DUSTS_COAL).add(BIItems.COAL_DUST.get());
        this.tag(Items.DUSTS_IRON).add(BIItems.IRON_DUST.get());
        this.tag(Items.DUSTS_COPPER).add(BIItems.COPPER_DUST.get());
        this.tag(Items.DUSTS_GOLD).add(BIItems.GOLD_DUST.get());

        // Global
        this.tag(Items.RAW_ORES).addTags(Items.RAW_ORES_TIN,Items.RAW_ORES_LEAD,Items.RAW_ORES_ALUMINIUM,Items.RAW_ORES_SILVER);
        this.tag(Items.DUSTS).addTags(Items.DUSTS_TIN,Items.DUSTS_LEAD,Items.DUSTS_ALUMINIUM,Items.DUSTS_SILVER,Items.DUSTS_STEEL,
                Items.DUSTS_COAL,Items.DUSTS_IRON,Items.DUSTS_COAL,Items.DUSTS_GOLD);
        this.tag(Items.INGOTS).addTags(Items.INGOTS_TIN,Items.INGOTS_LEAD,Items.INGOTS_ALUMINIUM,Items.INGOTS_SILVER,Items.INGOTS_STEEL);
        this.tag(Items.NUGGETS).addTags(Items.NUGGETS_TIN,Items.NUGGETS_LEAD,Items.NUGGETS_ALUMINIUM,Items.NUGGETS_SILVER,Items.NUGGETS_STEEL);
    }

    @Override
    public String getName() {
        return "Item Tags: "+modId;
    }

}
