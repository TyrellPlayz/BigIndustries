package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.tyrellplayz.big_industries.BITags.Blocks;
import static com.tyrellplayz.big_industries.BITags.Items;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, BigIndustries.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        for (Metals metal : Metals.values()) {
            if (!metal.isAlloy()) {
                this.tag(Items.METAL_RAW.get(metal))
                        .add(BIItems.RAW.get(metal).get());

                this.copy(Blocks.METAL_ORES.get(metal), Items.METAL_ORES.get(metal));
                this.copy(Blocks.METAL_RAW_STORAGE_BLOCKS.get(metal),Items.METAL_RAW_STORAGE_BLOCKS.get(metal));

                this.tag(Items.RAW_ORES).addTags(Items.METAL_RAW.get(metal));
            }
            this.tag(Items.METAL_CRUSHED.get(metal))
                    .add(BIItems.CRUSHED.get(metal).get());
            this.tag(Items.METAL_INGOTS.get(metal))
                    .add(BIItems.INGOT.get(metal).get());
            this.tag(Items.METAL_NUGGETS.get(metal))
                    .add(BIItems.NUGGET.get(metal).get());

            this.copy(Blocks.METAL_STORAGE_BLOCKS.get(metal),Items.METAL_STORAGE_BLOCKS.get(metal));

            this.tag(Items.DUSTS)
                    .addTags(Items.METAL_CRUSHED.get(metal));
            this.tag(Items.INGOTS)
                    .addTags(Items.METAL_INGOTS.get(metal));
            this.tag(Items.NUGGETS)
                    .addTags(Items.METAL_NUGGETS.get(metal));
        }

        // Vil
        this.tag(Items.DUSTS_COAL)
                .add(BIItems.CRUSHED_COAL.get());
        this.tag(Items.DUSTS_IRON)
                .add(BIItems.CRUSHED_IRON.get());
        this.tag(Items.DUSTS_COPPER)
                .add(BIItems.CRUSHED_COPPER.get());
        this.tag(Items.DUSTS_GOLD)
                .add(BIItems.CRUSHED_GOLD.get());

        // Global
        this.tag(Items.DUSTS)
                .addTags(Items.DUSTS_COAL,Items.DUSTS_IRON,Items.DUSTS_COAL,Items.DUSTS_GOLD);
    }

    @Override
    public String getName() {
        return "Item Tags: "+modId;
    }

}
