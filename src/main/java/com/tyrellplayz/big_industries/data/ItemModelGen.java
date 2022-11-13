package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.util.Objects;

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
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                simpleItem(BIItems.RAW.get(metal).get());
            }
            simpleItem(BIItems.DUST.get(metal).get());
            simpleItem(BIItems.INGOT.get(metal).get());
            simpleItem(BIItems.NUGGET.get(metal).get());
        }

        // Tin
        //simpleItem(BIItems.RAW_TIN.get());
        //simpleItem(BIItems.CRUSHED_TIN.get());
        //simpleItem(BIItems.TIN_INGOT.get());
        //simpleItem(BIItems.TIN_NUGGET.get());
        //simpleItem(BIItems.TIN_GEAR.get());

        // Lead
        //simpleItem(BIItems.RAW_LEAD.get());
        //simpleItem(BIItems.CRUSHED_LEAD.get());
        //simpleItem(BIItems.LEAD_INGOT.get());
        //simpleItem(BIItems.LEAD_NUGGET.get());
        //simpleItem(BIItems.LEAD_GEAR.get());

        // Aluminium
        //simpleItem(BIItems.RAW_ALUMINIUM.get());
        //simpleItem(BIItems.CRUSHED_ALUMINIUM.get());
        //simpleItem(BIItems.ALUMINIUM_INGOT.get());
        //simpleItem(BIItems.ALUMINIUM_NUGGET.get());

        // Silver
        //simpleItem(BIItems.RAW_SILVER.get());
        //simpleItem(BIItems.CRUSHED_SILVER.get());
        //simpleItem(BIItems.SILVER_INGOT.get());
        //simpleItem(BIItems.SILVER_NUGGET.get());

        // Nickel
        //simpleItem(BIItems.RAW_NICKEL.get());
        //simpleItem(BIItems.CRUSHED_NICKEL.get());
        //simpleItem(BIItems.NICKEL_INGOT.get());
        //simpleItem(BIItems.NICKEL_NUGGET.get());

        // Steel
        //simpleItem(BIItems.CRUSHED_STEEL.get());
        //simpleItem(BIItems.STEEL_INGOT.get());
        //simpleItem(BIItems.STEEL_NUGGET.get());
        //simpleItem(BIItems.STEEL_GEAR.get());

        simpleItem(BIItems.CRUSHED_COAL.get());
        simpleItem(BIItems.CRUSHED_IRON.get());
        simpleItem(BIItems.CRUSHED_COPPER.get());
        simpleItem(BIItems.CRUSHED_GOLD.get());

        simpleItem(BIItems.COKE.get());

        simpleTool(BIItems.HAMMER.get());
    }

    private void registerBlockModels() {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                simpleItem(BIBlocks.ORE.get(metal).get());
                simpleItem(BIBlocks.DEEPSLATE_ORE.get(metal).get());
                simpleItem(BIBlocks.RAW_STORAGE_BLOCK.get(metal).get());
            }
            simpleItem(BIBlocks.STORAGE_BLOCK.get(metal).get());
        }

        // Tin
        //simpleItem(BIBlocks.TIN_ORE.get());
        //simpleItem(BIBlocks.DEEPSLATE_TIN_ORE.get());
        //simpleItem(BIBlocks.RAW_TIN_BLOCK.get());
        //simpleItem(BIBlocks.TIN_BLOCK.get());

        // Lead
        //simpleItem(BIBlocks.LEAD_ORE.get());
        //simpleItem(BIBlocks.DEEPSLATE_LEAD_ORE.get());
        //simpleItem(BIBlocks.RAW_LEAD_BLOCK.get());
        //simpleItem(BIBlocks.LEAD_BLOCK.get());

        // Aluminium
        //simpleItem(BIBlocks.ALUMINIUM_ORE.get());
        //simpleItem(BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        //simpleItem(BIBlocks.RAW_ALUMINIUM_BLOCK.get());
        //simpleItem(BIBlocks.ALUMINIUM_BLOCK.get());

        // Silver
        //simpleItem(BIBlocks.SILVER_ORE.get());
        //simpleItem(BIBlocks.DEEPSLATE_SILVER_ORE.get());
        //simpleItem(BIBlocks.RAW_SILVER_BLOCK.get());
        //simpleItem(BIBlocks.SILVER_BLOCK.get());

        // Nickel
        //simpleItem(BIBlocks.NICKEL_ORE.get());
        //simpleItem(BIBlocks.DEEPSLATE_NICKEL_ORE.get());
        //simpleItem(BIBlocks.RAW_NICKEL_BLOCK.get());
        //simpleItem(BIBlocks.NICKEL_BLOCK.get());

        // Steel
        //simpleItem(BIBlocks.STEEL_BLOCK.get());

        simpleItem(BIBlocks.BLAST_BRICK.get());
    }

    public void simpleItem(Item item) {
        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",modLoc("item/"+name));
    }

    public void simpleItem(Block block) {
        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).getPath();
        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/"+name)));
    }

    public void simpleTool(Item item) {
        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0",modLoc("item/"+name));
    }

    @Override
    public void run(CachedOutput cache) throws IOException {
        super.run(cache);
    }

}
