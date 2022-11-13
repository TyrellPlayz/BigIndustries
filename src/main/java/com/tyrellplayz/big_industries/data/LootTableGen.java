package com.tyrellplayz.big_industries.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
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
import java.util.Objects;
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
            for (Metals metal : Metals.values()) {
                if(!metal.isAlloy()) {
                    this.add(metal.getOre(), block -> createOreDrop(block, metal.getRaw()));
                    this.add(metal.getDeepslateOre(), block -> createOreDrop(block, metal.getRaw()));
                    this.dropSelf(metal.getRawStorageBlock());
                }
                this.dropSelf(metal.getStorageBlock());
            }

            this.dropSelf(BIBlocks.BLAST_BRICK.get());
            this.dropSelf(BIBlocks.FLUID_PIPE.get());
        }

        @Override
        protected Iterable<net.minecraft.world.level.block.Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block) != null && BigIndustries.MOD_ID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace())).collect(Collectors.toSet());
        }
    }

}
