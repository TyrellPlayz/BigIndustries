package com.tyrellplayz.big_industries.world.worldgen;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BIOrePlacements {

    public static PlacedFeature ORE_TIN;
    public static PlacedFeature ORE_LEAD;
    public static PlacedFeature ORE_ALUMINIUM;
    public static PlacedFeature ORE_SILVER;
    public static PlacedFeature ORE_NICKEL;

    private BIOrePlacements() {}

    public static void init() {
        ORE_TIN = PlacementUtils.register(modLoc("ore_tin"),BIOreFeatures.ORE_TIN
                .placed(commonOrePlacement(20,HeightRangePlacement.triangle(VerticalAnchor.absolute(38),VerticalAnchor.absolute(112)))));

        ORE_LEAD = PlacementUtils.register(modLoc("ore_lead"),BIOreFeatures.ORE_LEAD
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(30)))));

        ORE_ALUMINIUM = PlacementUtils.register(modLoc("ore_aluminium"),BIOreFeatures.ORE_ALUMINIUM
                .placed(commonOrePlacement(20,HeightRangePlacement.triangle(VerticalAnchor.absolute(0),VerticalAnchor.absolute(56)))));

        ORE_SILVER = PlacementUtils.register(modLoc("ore_silver"),BIOreFeatures.ORE_SILVER
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.absolute(-15),VerticalAnchor.absolute(56)))));

        ORE_NICKEL = PlacementUtils.register(modLoc("ore_nickel"), BIOreFeatures.ORE_NICKEL
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(20)))));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier modifier) {
        return List.of(p_195347_, InSquarePlacement.spread(), modifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int triesPerChunk, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(triesPerChunk), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), modifier);
    }

    private static String modLoc(String path) {
        return BigIndustries.MOD_ID+":"+path;
    }

}
