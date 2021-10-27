package com.tyrellplayz.big_industries.core;

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
public class ModFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> TIN_ORE = key("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> LEAD_ORE = key("lead_ore");

    private static ResourceKey<ConfiguredFeature<?,?>> key(final String name) {
        return ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY,new ResourceLocation(BigIndustries.MOD_ID,name));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void register(final RegistryEvent.Register<Feature<?>> event) {
        register(TIN_ORE,Feature.ORE
                .configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState(), 9))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(63))
                .squared()
                .count(20));
        register(LEAD_ORE,Feature.ORE
                .configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState(),7))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(43))
                .squared()
                .count(15));
    }

    private static void register(final ResourceKey<ConfiguredFeature<?,?>> key, final ConfiguredFeature<?,?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,key.location(),configuredFeature);
    }

}
