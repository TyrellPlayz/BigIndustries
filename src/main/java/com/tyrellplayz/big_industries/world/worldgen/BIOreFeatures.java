package com.tyrellplayz.big_industries.world.worldgen;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class BIOreFeatures {

    public static ImmutableList<OreConfiguration.TargetBlockState> ORE_TIN_TARGET_LIST;
    public static ImmutableList<OreConfiguration.TargetBlockState> ORE_LEAD_TARGET_LIST;
    public static ImmutableList<OreConfiguration.TargetBlockState> ORE_ALUMINIUM_TARGET_LIST;
    public static ImmutableList<OreConfiguration.TargetBlockState> ORE_SILVER_TARGET_LIST;

    public static ConfiguredFeature<?,?> ORE_TIN;
    public static ConfiguredFeature<?,?> ORE_LEAD;
    public static ConfiguredFeature<?,?> ORE_ALUMINIUM;
    public static ConfiguredFeature<?,?> ORE_SILVER;

    private BIOreFeatures() {}

    public static void init() {
        ORE_TIN_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BIBlocks.TIN_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));
        ORE_LEAD_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BIBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState()));
        ORE_ALUMINIUM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BIBlocks.ALUMINIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState()));
        ORE_SILVER_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BIBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));

        ORE_TIN = FeatureUtils.register(BigIndustries.MOD_ID+":ore_tin", Feature.ORE.configured(new OreConfiguration(ORE_TIN_TARGET_LIST,9)));
        ORE_LEAD = FeatureUtils.register(BigIndustries.MOD_ID+":ore_lead", Feature.ORE.configured(new OreConfiguration(ORE_LEAD_TARGET_LIST,9)));
        ORE_ALUMINIUM = FeatureUtils.register(BigIndustries.MOD_ID+":ore_aluminium", Feature.ORE.configured(new OreConfiguration(ORE_ALUMINIUM_TARGET_LIST,9)));
        ORE_SILVER = FeatureUtils.register(BigIndustries.MOD_ID+":ore_silver", Feature.ORE.configured(new OreConfiguration(ORE_SILVER_TARGET_LIST,9)));
    }

}
