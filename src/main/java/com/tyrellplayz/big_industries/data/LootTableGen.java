package com.tyrellplayz.big_industries.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.core.ModBlocks;
import com.tyrellplayz.big_industries.core.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTableGen extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> TABLES =
            ImmutableList.of(Pair.of(Block::new, LootContextParamSets.BLOCK));

    public LootTableGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        // do not validate against all registered loot tables
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return TABLES;
    }

    @Override
    public String getName() {
        return "LootTables: big_industries";
    }

    private static class Block extends BlockLoot {

        @Override
        protected void addTables() {
            // Tin
            this.add(ModBlocks.TIN_ORE.get(), block -> createOreDrop(block, ModItems.RAW_TIN.get()));
            this.dropSelf(ModBlocks.TIN_BLOCK.get());
            this.dropSelf(ModBlocks.RAW_TIN_BLOCK.get());

            // Steel
            this.add(ModBlocks.LEAD_ORE.get(), block -> createOreDrop(block, ModItems.RAW_LEAD.get()));
            this.dropSelf(ModBlocks.LEAD_BLOCK.get());
            this.dropSelf(ModBlocks.RAW_LEAD_BLOCK.get());

            // Steel
            this.dropSelf(ModBlocks.STEEL_BLOCK.get());

            this.dropSelf(ModBlocks.BLAST_BRICK.get());
        }

        @Override
        protected Iterable<net.minecraft.world.level.block.Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName() != null && BigIndustries.MOD_ID.equals(block.getRegistryName().getNamespace())).collect(Collectors.toSet());
        }
    }

}
