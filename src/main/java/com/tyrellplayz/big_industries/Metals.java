package com.tyrellplayz.big_industries;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public enum Metals {

    TIN(false, Tiers.STONE),
    LEAD(false, Tiers.IRON),
    ALUMINIUM(false, Tiers.STONE),
    SILVER(false, Tiers.STONE),
    NICKEL(false, Tiers.IRON),
    STEEL(true, Tiers.IRON);

    private final boolean alloy;
    private final Tier requiredToolTier;

    Metals(boolean isAlloy, Tier requiredToolTier) {
        this.alloy = isAlloy;
        this.requiredToolTier = requiredToolTier;
    }

    public boolean isAlloy() {
        return this.alloy;
    }

    public Tier getRequiredToolTier() {
        return this.requiredToolTier;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

        /*
        Items
         */

    public Item getCrushed() {
        return BIItems.CRUSHED.get(this).get();
    }

    public TagKey<Item> getCrushedTag() {
        return BITags.Items.METAL_CRUSHED.get(this);
    }

    public Item getIngot() {
        return BIItems.INGOT.get(this).get();
    }

    public TagKey<Item> getIngotTag() {
        return BITags.Items.METAL_INGOTS.get(this);
    }

    public Item getNugget() {
        return BIItems.NUGGET.get(this).get();
    }

    public TagKey<Item> getNuggetTag() {
        return BITags.Items.METAL_NUGGETS.get(this);
    }

    public Item getRaw() {
        if(isAlloy()) return null;
        return BIItems.RAW.get(this).get();
    }

    public TagKey<Item> getRawTag() {
        if(isAlloy()) return null;
        return BITags.Items.METAL_RAW.get(this);
    }

        /*
        Blocks
         */

    public Block getStorageBlock() {
        return BIBlocks.STORAGE_BLOCK.get(this).get();
    }

    public TagKey<Item> getStorageBlockTag() {
        return BITags.Items.METAL_STORAGE_BLOCKS.get(this);
    }

    public Block getRawStorageBlock() {
        if(isAlloy()) return null;
        return BIBlocks.RAW_STORAGE_BLOCK.get(this).get();
    }

    public TagKey<Item> getRawStorageBlockTag() {
        return BITags.Items.METAL_RAW_STORAGE_BLOCKS.get(this);
    }

    public Block getOre() {
        if(isAlloy()) return null;
        return BIBlocks.ORE.get(this).get();
    }

    public TagKey<Item> getOreTag() {
        return BITags.Items.METAL_ORES.get(this);
    }

    public Block getDeepslateOre() {
        if(isAlloy()) return null;
        return BIBlocks.DEEPSLATE_ORE.get(this).get();
    }

    public ImmutableList<ItemLike> getSmeltables() {
        if(isAlloy()) {
            return ImmutableList.of(getCrushed());
        }else {
            return ImmutableList.of(getOre(),getDeepslateOre(), getRaw(), getCrushed());
        }

    }

    public ImmutableList<OreConfiguration.TargetBlockState> getOreTargetList() {
        return ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, getOre().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, getDeepslateOre().defaultBlockState()));
    }

}
