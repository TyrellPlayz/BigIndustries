package com.tyrellplayz.big_industries.core;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BIFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_TIN = key("ore_tin");
    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_LEAD = key("ore_lead");
    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_ALUMINIUM = key("ore_aluminium");

    private static ResourceKey<ConfiguredFeature<?,?>> key(final String name) {
        return ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY,new ResourceLocation(BigIndustries.MOD_ID,name));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerEvent(final RegistryEvent.Register<Feature<?>> event) {
        ImmutableList<OreConfiguration.TargetBlockState> ORE_TIN_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BIBlocks.TIN_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));
        ImmutableList<OreConfiguration.TargetBlockState> ORE_LEAD_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BIBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState()));
        ImmutableList<OreConfiguration.TargetBlockState> ORE_ALUMINIUM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BIBlocks.ALUMINIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, BIBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState()));

        register(ORE_TIN,defaultOreFeature(ORE_TIN_TARGET_LIST,9,63,20));
        register(ORE_LEAD,defaultOreFeature(ORE_LEAD_TARGET_LIST,9,63,20));
        register(ORE_ALUMINIUM,defaultOreFeature(ORE_ALUMINIUM_TARGET_LIST,9,63,20));
    }

    private static ConfiguredFeature<?, ?> defaultOreFeature(ImmutableList<OreConfiguration.TargetBlockState> targetList, int maxVeinSize, int maxHeight, int maxPerChunk) {
        return Feature.ORE
                .configured(new OreConfiguration(targetList,maxVeinSize))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(maxHeight))
                .squared()
                .count(maxPerChunk);
    }

    private static <T extends ConfiguredFeature<?, ?>> T register(ResourceKey<ConfiguredFeature<?,?>> key, T configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key.location(),configuredFeature);
    }

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation(BigIndustries.MOD_ID,path);
    }

}
