package com.tyrellplayz.big_industries.world.worldgen;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BIOrePlacements {

    public static PlacedFeature ORE_TIN;
    public static PlacedFeature ORE_LEAD;
    public static PlacedFeature ORE_ALUMINIUM;

    private BIOrePlacements() {}

    public static void init() {
        ORE_TIN = PlacementUtils.register(BigIndustries.MOD_ID+":ore_tin",BIOreFeatures.ORE_TIN
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(56)))));
        ORE_LEAD = PlacementUtils.register(BigIndustries.MOD_ID+":ore_lead",BIOreFeatures.ORE_LEAD
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(56)))));
        ORE_ALUMINIUM = PlacementUtils.register(BigIndustries.MOD_ID+":ore_aluminium",BIOreFeatures.ORE_ALUMINIUM
                .placed(commonOrePlacement(15,HeightRangePlacement.triangle(VerticalAnchor.bottom(),VerticalAnchor.absolute(56)))));
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

}
