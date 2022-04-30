package com.tyrellplayz.big_industries.world.worldgen;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class BIOreFeatures {

    public static Holder<ConfiguredFeature<OreConfiguration,?>>  ORE_TIN;
    public static Holder<ConfiguredFeature<OreConfiguration,?>> ORE_LEAD;
    public static Holder<ConfiguredFeature<OreConfiguration,?>> ORE_ALUMINIUM;
    public static Holder<ConfiguredFeature<OreConfiguration,?>> ORE_SILVER;
    public static Holder<ConfiguredFeature<OreConfiguration,?>> ORE_NICKEL;

    private BIOreFeatures() {}

    public static void init() {
        ORE_TIN = FeatureUtils.register(modLoc("ore_tin"),Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9));
        ORE_LEAD = FeatureUtils.register(modLoc("ore_lead"),Feature.ORE,new OreConfiguration(Metals.LEAD.getOreTargetList(),9));
        ORE_ALUMINIUM = FeatureUtils.register(modLoc("ore_aluminium"),Feature.ORE,new OreConfiguration(Metals.ALUMINIUM.getOreTargetList(),9));
        ORE_SILVER = FeatureUtils.register(modLoc("ore_silver"),Feature.ORE,new OreConfiguration(Metals.SILVER.getOreTargetList(),9));
        ORE_NICKEL = FeatureUtils.register(modLoc("ore_nickel"),Feature.ORE,new OreConfiguration(Metals.NICKEL.getOreTargetList(),2));
    }

    private static String modLoc(String path) {
        return BigIndustries.MOD_ID+":"+path;
    }

}
