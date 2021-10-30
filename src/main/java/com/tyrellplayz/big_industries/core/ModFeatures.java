package com.tyrellplayz.big_industries.core;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {

    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_TIN_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_LEAD_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_ALUMINIUM_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.ALUMINIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState()));

    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_TIN = key("ore_tin");
    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_LEAD = key("ore_lead");
    public static final ResourceKey<ConfiguredFeature<?,?>> ORE_ALUMINIUM = key("ore_aluminium");

    private static ResourceKey<ConfiguredFeature<?,?>> key(final String name) {
        return ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY,new ResourceLocation(BigIndustries.MOD_ID,name));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void register(final RegistryEvent.Register<Feature<?>> event) {
        registerDefaultOre(ORE_TIN,ORE_TIN_TARGET_LIST,9,63,20);
        registerDefaultOre(ORE_LEAD,ORE_LEAD_TARGET_LIST,7,31,15);
        registerDefaultOre(ORE_ALUMINIUM,ORE_ALUMINIUM_TARGET_LIST,7,63,15);
        /*
        register(TIN_ORE,Feature.ORE
                .configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState(), 9))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(64))
                .squared()
                .count(20));
        register(LEAD_ORE,Feature.ORE
                .configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState(),7))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(32))
                .squared()
                .count(15));
        register(ALUMINIUM_ORE,Feature.ORE
                .configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.ALUMINIUM_ORE.get().defaultBlockState(),7))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(64))
                .squared()
                .count(20));
         */
    }

    private static void registerDefaultOre(final ResourceKey<ConfiguredFeature<?,?>> key, ImmutableList<OreConfiguration.TargetBlockState> targetList, int maxVeinSize, int maxHeight, int maxPerChunk) {
        register(key,Feature.ORE
                .configured(new OreConfiguration(targetList,maxVeinSize))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(maxHeight))
                .squared()
                .count(maxPerChunk));
    }

    private static void register(final ResourceKey<ConfiguredFeature<?,?>> key, final ConfiguredFeature<?,?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,key.location(),configuredFeature);
    }

}
