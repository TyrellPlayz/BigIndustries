package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.ModTags;
import com.tyrellplayz.big_industries.core.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, BigIndustries.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Forge
        // Tin
        this.copy(ModTags.Blocks.ORES_TIN, ModTags.Items.ORES_TIN);
        this.tag(ModTags.Items.INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        this.tag(ModTags.Items.NUGGETS_TIN).add(ModItems.TIN_NUGGET.get());
        this.tag(ModTags.Items.CRUSHED_TIN).add(ModItems.CRUSHED_TIN.get());
        this.tag(ModTags.Items.GEARS_TIN).add(ModItems.TIN_GEAR.get());
        this.copy(ModTags.Blocks.STORAGE_BLOCKS_TIN,ModTags.Items.STORAGE_BLOCKS_TIN);

        // Lead
        this.copy(ModTags.Blocks.ORES_LEAD, ModTags.Items.ORES_LEAD);
        this.tag(ModTags.Items.INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        this.tag(ModTags.Items.NUGGETS_LEAD).add(ModItems.LEAD_NUGGET.get());
        this.tag(ModTags.Items.CRUSHED_LEAD).add(ModItems.CRUSHED_LEAD.get());
        this.tag(ModTags.Items.GEARS_LEAD).add(ModItems.LEAD_GEAR.get());
        this.copy(ModTags.Blocks.STORAGE_BLOCKS_LEAD,ModTags.Items.STORAGE_BLOCKS_LEAD);

        // Steel
        this.tag(ModTags.Items.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        this.tag(ModTags.Items.NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());
        this.tag(ModTags.Items.CRUSHED_STEEL).add(ModItems.CRUSHED_STEEL.get());
        this.tag(ModTags.Items.GEARS_STEEL).add(ModItems.STEEL_GEAR.get());
        this.copy(ModTags.Blocks.STORAGE_BLOCKS_STEEL,ModTags.Items.STORAGE_BLOCKS_STEEL);

        this.tag(ModTags.Items.CRUSHED_COAL).add(ModItems.CRUSHED_COAL.get());
        this.tag(ModTags.Items.CRUSHED_IRON).add(ModItems.CRUSHED_IRON.get());
        this.tag(ModTags.Items.CRUSHED_COPPER).add(ModItems.CRUSHED_COPPER.get());
        this.tag(ModTags.Items.CRUSHED_GOLD).add(ModItems.CRUSHED_GOLD.get());
        // Minecraft

    }

    @Override
    public String getName() {
        return "Item Tags: "+modId;
    }

}
