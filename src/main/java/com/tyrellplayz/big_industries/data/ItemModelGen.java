package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.ModBlocks;
import com.tyrellplayz.big_industries.core.ModItems;
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
        // Items
        // Tin
        simpleItem(ModItems.RAW_TIN.get());
        simpleItem(ModItems.CRUSHED_TIN.get());
        simpleItem(ModItems.TIN_INGOT.get());
        simpleItem(ModItems.TIN_NUGGET.get());
        simpleItem(ModItems.TIN_GEAR.get());

        // Lead
        simpleItem(ModItems.RAW_LEAD.get());
        simpleItem(ModItems.CRUSHED_LEAD.get());
        simpleItem(ModItems.LEAD_INGOT.get());
        simpleItem(ModItems.LEAD_NUGGET.get());
        simpleItem(ModItems.LEAD_GEAR.get());

        // Steel
        simpleItem(ModItems.CRUSHED_STEEL.get());
        simpleItem(ModItems.STEEL_INGOT.get());
        simpleItem(ModItems.STEEL_NUGGET.get());
        simpleItem(ModItems.STEEL_GEAR.get());

        simpleItem(ModItems.CRUSHED_COAL.get());
        simpleItem(ModItems.CRUSHED_IRON.get());
        simpleItem(ModItems.CRUSHED_COPPER.get());
        simpleItem(ModItems.CRUSHED_GOLD.get());

        simpleItem(ModItems.COPPER_WIRE.get());

        simpleItem(ModItems.SOLAR_CELL.get());

        // Blocks
        simpleItem(ModBlocks.TIN_BLOCK.get());
        simpleItem(ModBlocks.TIN_ORE.get());
        simpleItem(ModBlocks.RAW_TIN_BLOCK.get());

        simpleItem(ModBlocks.LEAD_BLOCK.get());
        simpleItem(ModBlocks.LEAD_ORE.get());
        simpleItem(ModBlocks.RAW_LEAD_BLOCK.get());

        simpleItem(ModBlocks.STEEL_BLOCK.get());

        simpleItem(ModBlocks.BLAST_BRICK.get());

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
