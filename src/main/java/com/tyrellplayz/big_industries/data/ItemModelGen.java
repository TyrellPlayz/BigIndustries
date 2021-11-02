package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BigIndustries.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItemModels();
        registerBlockModels();
    }

    private void registerItemModels() {
        // Tin
        simpleItem(BIItems.RAW_TIN.get());
        simpleItem(BIItems.CRUSHED_TIN.get());
        simpleItem(BIItems.TIN_INGOT.get());
        simpleItem(BIItems.TIN_NUGGET.get());
        simpleItem(BIItems.TIN_GEAR.get());

        // Lead
        simpleItem(BIItems.RAW_LEAD.get());
        simpleItem(BIItems.CRUSHED_LEAD.get());
        simpleItem(BIItems.LEAD_INGOT.get());
        simpleItem(BIItems.LEAD_NUGGET.get());
        simpleItem(BIItems.LEAD_GEAR.get());

        // Aluminium
        simpleItem(BIItems.RAW_ALUMINIUM.get());
        simpleItem(BIItems.CRUSHED_ALUMINIUM.get());
        simpleItem(BIItems.ALUMINIUM_INGOT.get());
        simpleItem(BIItems.ALUMINIUM_NUGGET.get());

        // Silver
        simpleItem(BIItems.SILVER_INGOT.get());

        // Steel
        simpleItem(BIItems.CRUSHED_STEEL.get());
        simpleItem(BIItems.STEEL_INGOT.get());
        simpleItem(BIItems.STEEL_NUGGET.get());
        simpleItem(BIItems.STEEL_GEAR.get());

        simpleItem(BIItems.CRUSHED_COAL.get());
        simpleItem(BIItems.CRUSHED_IRON.get());
        simpleItem(BIItems.CRUSHED_COPPER.get());
        simpleItem(BIItems.CRUSHED_GOLD.get());

        //simpleItem(ModItems.COPPER_WIRE.get());

        //simpleItem(ModItems.SOLAR_CELL.get());
    }

    private void registerBlockModels() {
        // Tin
        simpleItem(BIBlocks.TIN_ORE.get());
        simpleItem(BIBlocks.DEEPSLATE_TIN_ORE.get());
        simpleItem(BIBlocks.RAW_TIN_BLOCK.get());
        simpleItem(BIBlocks.TIN_BLOCK.get());

        // Lead
        simpleItem(BIBlocks.LEAD_ORE.get());
        simpleItem(BIBlocks.DEEPSLATE_LEAD_ORE.get());
        simpleItem(BIBlocks.RAW_LEAD_BLOCK.get());
        simpleItem(BIBlocks.LEAD_BLOCK.get());

        // Aluminium
        simpleItem(BIBlocks.ALUMINIUM_ORE.get());
        simpleItem(BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        simpleItem(BIBlocks.RAW_ALUMINIUM_BLOCK.get());
        simpleItem(BIBlocks.ALUMINIUM_BLOCK.get());

        // Steel
        simpleItem(BIBlocks.STEEL_BLOCK.get());

        simpleItem(BIBlocks.BLAST_BRICK.get());

        //simpleItem(ModBlocks.BOILER);
        //simpleItem(ModBlocks.PIPE);
    }

    public void simpleItem(Item item) {
        getBuilder(item.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",modLoc("item/"+item.getRegistryName().getPath()));
    }

    public void simpleItem(Block block) {
        getBuilder(block.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/"+block.getRegistryName().getPath())));
    }

    @Override
    public void run(HashCache cache) throws IOException {
        super.run(cache);
    }
}
