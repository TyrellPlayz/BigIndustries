package com.tyrellplayz.big_industries.level.levelgen;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.ModFeatures;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID)
public class ModLevelGen {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addFeaturesToBiomes(final BiomeLoadingEvent event) {
        final BiomeGenerationSettingsBuilder generation = event.getGeneration();
        final ResourceKey<Biome> biomeRegistryKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Biome registry name was null"));

        if(BiomeDictionary.hasType(biomeRegistryKey,BiomeDictionary.Type.OVERWORLD)) {
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,getFeature(ModFeatures.TIN_ORE));
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,getFeature(ModFeatures.LEAD_ORE));
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,getFeature(ModFeatures.ALUMINIUM_ORE));
        }
        if(BiomeDictionary.hasType(biomeRegistryKey,BiomeDictionary.Type.NETHER)) {

        }
        if(BiomeDictionary.hasType(biomeRegistryKey,BiomeDictionary.Type.END)) {

        }
    }

    private static ConfiguredFeature<?, ?> getFeature(final ResourceKey<ConfiguredFeature<?, ?>> key) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getOrThrow(key);
    }

}
