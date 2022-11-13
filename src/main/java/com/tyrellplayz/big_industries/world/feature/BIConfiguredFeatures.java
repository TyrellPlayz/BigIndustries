package com.tyrellplayz.big_industries.world.feature;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BIConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY,BigIndustries.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?,?>> ORE_TIN = CONFIGURED_FEATURES.register("tin_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9)));
    public static final RegistryObject<ConfiguredFeature<?,?>> ORE_LEAD = CONFIGURED_FEATURES.register("lead_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9)));
    public static final RegistryObject<ConfiguredFeature<?,?>> ORE_ALUMINIUM = CONFIGURED_FEATURES.register("aluminum_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9)));
    public static final RegistryObject<ConfiguredFeature<?,?>> ORE_SILVER = CONFIGURED_FEATURES.register("silver_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9)));
    public static final RegistryObject<ConfiguredFeature<?,?>> ORE_NICKEL = CONFIGURED_FEATURES.register("nickel_ore",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(Metals.TIN.getOreTargetList(),9)));

}
