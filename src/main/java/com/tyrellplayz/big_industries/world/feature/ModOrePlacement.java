package com.tyrellplayz.big_industries.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier modifier) {
        return List.of(p_195347_, InSquarePlacement.spread(), modifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int triesPerChunk, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(triesPerChunk), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), modifier);
    }

}
