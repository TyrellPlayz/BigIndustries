package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.tyrellplayz.big_industries.common.ModTags.Blocks;
import static com.tyrellplayz.big_industries.common.ModTags.Items;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, BigIndustries.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        // Tin
        this.tag(Items.RAW_ORES_TIN).add(ModItems.RAW_TIN.get());
        this.tag(Items.DUSTS_TIN).add(ModItems.CRUSHED_TIN.get());
        this.tag(Items.INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        this.tag(Items.NUGGETS_TIN).add(ModItems.TIN_NUGGET.get());
        this.tag(Items.GEARS_TIN).add(ModItems.TIN_GEAR.get());
        this.copy(Blocks.ORES_TIN, Items.ORES_TIN);
        this.copy(Blocks.STORAGE_BLOCKS_TIN,Items.STORAGE_BLOCKS_TIN);

        // Lead
        this.tag(Items.RAW_ORES_LEAD).add(ModItems.RAW_LEAD.get());
        this.tag(Items.DUSTS_LEAD).add(ModItems.CRUSHED_LEAD.get());
        this.tag(Items.INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        this.tag(Items.NUGGETS_LEAD).add(ModItems.LEAD_NUGGET.get());
        this.tag(Items.GEARS_LEAD).add(ModItems.LEAD_GEAR.get());
        this.copy(Blocks.ORES_LEAD,Items.ORES_LEAD);
        this.copy(Blocks.STORAGE_BLOCKS_LEAD,Items.STORAGE_BLOCKS_LEAD);

        // Aluminium
        this.tag(Items.RAW_ORES_ALUMINIUM).add(ModItems.RAW_ALUMINIUM.get());
        this.tag(Items.DUSTS_ALUMINIUM).add(ModItems.CRUSHED_ALUMINIUM.get());
        this.tag(Items.INGOTS_ALUMINIUM).add(ModItems.ALUMINIUM_INGOT.get());
        this.tag(Items.NUGGETS_ALUMINIUM).add(ModItems.ALUMINIUM_NUGGET.get());
        //this.copy(ModTags.Blocks.ORES_ALUMINIUM, ModTags.Items.ORES_ALUMINIUM);
        //this.copy(ModTags.Blocks.STORAGE_BLOCKS_ALUMINIUM,ModTags.Items.STORAGE_BLOCKS_ALUMINIUM);

        // Steel
        this.copy(Blocks.STORAGE_BLOCKS_STEEL,Items.STORAGE_BLOCKS_STEEL);

        // Global
        this.tag(Items.RAW_ORES).addTags(Items.RAW_ORES_TIN,Items.RAW_ORES_LEAD,Items.RAW_ORES_ALUMINIUM);
        this.tag(Items.DUSTS).addTags(Items.DUSTS_TIN,Items.DUSTS_LEAD,Items.DUSTS_ALUMINIUM);
        this.tag(Items.INGOTS).addTags(Items.INGOTS_TIN,Items.INGOTS_LEAD,Items.INGOTS_ALUMINIUM);
        this.tag(Items.NUGGETS).addTags(Items.NUGGETS_TIN,Items.NUGGETS_LEAD,Items.NUGGETS_ALUMINIUM);
    }

    @Override
    public String getName() {
        return "Item Tags: "+modId;
    }

}
