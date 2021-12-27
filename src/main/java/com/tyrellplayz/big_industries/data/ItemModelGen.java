package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
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
        simpleItem(BIItems.TIN_DUST.get());
        simpleItem(BIItems.TIN_INGOT.get());
        simpleItem(BIItems.TIN_NUGGET.get());
        simpleItem(BIItems.TIN_GEAR.get());

        // Lead
        simpleItem(BIItems.RAW_LEAD.get());
        simpleItem(BIItems.LEAD_DUST.get());
        simpleItem(BIItems.LEAD_INGOT.get());
        simpleItem(BIItems.LEAD_NUGGET.get());
        simpleItem(BIItems.LEAD_GEAR.get());

        // Aluminium
        simpleItem(BIItems.RAW_ALUMINIUM.get());
        simpleItem(BIItems.ALUMINIUM_DUST.get());
        simpleItem(BIItems.ALUMINIUM_INGOT.get());
        simpleItem(BIItems.ALUMINIUM_NUGGET.get());

        // Silver
        simpleItem(BIItems.SILVER_INGOT.get());

        // Steel
        simpleItem(BIItems.STEEL_DUST.get());
        simpleItem(BIItems.STEEL_INGOT.get());
        simpleItem(BIItems.STEEL_NUGGET.get());
        simpleItem(BIItems.STEEL_GEAR.get());

        simpleItem(BIItems.COAL_DUST.get());
        simpleItem(BIItems.IRON_DUST.get());
        simpleItem(BIItems.COPPER_DUST.get());
        simpleItem(BIItems.GOLD_DUST.get());

        //simpleItem(ModItems.COPPER_WIRE.get());

        //simpleItem(ModItems.SOLAR_CELL.get());
        simpleTool(BIItems.HAMMER.get());
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

        // Silver
        simpleItem(BIBlocks.SILVER_ORE.get());
        simpleItem(BIBlocks.DEEPSLATE_SILVER_ORE.get());
        simpleItem(BIBlocks.RAW_SILVER_BLOCK.get());
        simpleItem(BIBlocks.SILVER_BLOCK.get());

        // Steel
        simpleItem(BIBlocks.STEEL_BLOCK.get());

        simpleItem(BIBlocks.BLAST_BRICK.get());
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

    public void simpleTool(Item item) {
        getBuilder(item.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0",modLoc("item/"+item.getRegistryName().getPath()));
    }

    @Override
    public void run(HashCache cache) throws IOException {
        super.run(cache);
    }
}
