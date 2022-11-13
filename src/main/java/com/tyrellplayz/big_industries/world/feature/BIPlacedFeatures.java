package com.tyrellplayz.big_industries.world.feature;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class BIPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY,BigIndustries.MOD_ID);

     public static final RegistryObject<PlacedFeature> ORE_TIN = PLACED_FEATURES.register("tin_ore_placed",
             () -> new PlacedFeature(BIConfiguredFeatures.ORE_TIN.getHolder().get(),ModOrePlacement.commonOrePlacement(
                    20,HeightRangePlacement.triangle(VerticalAnchor.absolute(38), VerticalAnchor.absolute(112)))));

    public static final RegistryObject<PlacedFeature> ORE_LEAD = PLACED_FEATURES.register("lead_ore_placed",
            () -> new PlacedFeature(BIConfiguredFeatures.ORE_TIN.getHolder().get(),ModOrePlacement.commonOrePlacement(
                    15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(30)))));

    public static final RegistryObject<PlacedFeature> ORE_ALUMINIUM = PLACED_FEATURES.register("aluminium_ore_placed",
            () -> new PlacedFeature(BIConfiguredFeatures.ORE_ALUMINIUM.getHolder().get(),ModOrePlacement.commonOrePlacement(
                    20,HeightRangePlacement.triangle(VerticalAnchor.absolute(0),VerticalAnchor.absolute(56)))));

    public static final RegistryObject<PlacedFeature> ORE_SILVER = PLACED_FEATURES.register("silver_ore_placed",
            () -> new PlacedFeature(BIConfiguredFeatures.ORE_SILVER.getHolder().get() ,ModOrePlacement.commonOrePlacement(
                    15,HeightRangePlacement.triangle(VerticalAnchor.absolute(-15),VerticalAnchor.absolute(56)))));

    public static final RegistryObject<PlacedFeature> ORE_NICKEL = PLACED_FEATURES.register("nickel_ore_placed",
            () -> new PlacedFeature(BIConfiguredFeatures.ORE_NICKEL.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(20)))));

}
