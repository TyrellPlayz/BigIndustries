package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import static com.tyrellplayz.big_industries.BITags.*;

import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BigIndustries.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                this.tag(Blocks.METAL_RAW_STORAGE_BLOCKS.get(metal))
                        .add(BIBlocks.RAW_STORAGE_BLOCK.get(metal).get());

                this.tag(Blocks.METAL_ORES.get(metal))
                        .add(BIBlocks.ORE.get(metal).get(), BIBlocks.DEEPSLATE_ORE.get(metal).get());

                this.tag(Tags.Blocks.ORES).addTags(Blocks.METAL_ORES.get(metal));

                this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .addTags(Blocks.METAL_RAW_STORAGE_BLOCKS.get(metal),Blocks.METAL_ORES.get(metal));

                this.tag(getToolTag(metal.getRequiredToolTier()))
                        .add(BIBlocks.ORE.get(metal).get(),BIBlocks.STORAGE_BLOCK.get(metal).get());
                this.tag(BlockTags.NEEDS_IRON_TOOL)
                        .add(BIBlocks.DEEPSLATE_ORE.get(metal).get());

                this.tag(BlockTags.STONE_ORE_REPLACEABLES).add(BIBlocks.ORE.get(metal).get());
                this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(BIBlocks.DEEPSLATE_ORE.get(metal).get());
            }
            this.tag(Blocks.METAL_STORAGE_BLOCKS.get(metal))
                    .add(BIBlocks.STORAGE_BLOCK.get(metal).get());

            this.tag(Tags.Blocks.STORAGE_BLOCKS)
                    .addTags(Blocks.METAL_STORAGE_BLOCKS.get(metal));

            this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .addTags(Blocks.METAL_STORAGE_BLOCKS.get(metal));

            this.tag(getToolTag(metal.getRequiredToolTier()))
                    .add(BIBlocks.STORAGE_BLOCK.get(metal).get());
        }

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BIBlocks.BLAST_BRICK.get()).add(BIBlocks.BLAST_FURNACE.get());
    }

    @Override
    public String getName() {
        return "Block Tags: "+modId;
    }

    private static TagKey<Block> getToolTag(Tier tier) {
        if(tier == Tiers.IRON) {
            return BlockTags.NEEDS_IRON_TOOL;
        }else if(tier == Tiers.DIAMOND) {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        }else if(tier == Tiers.NETHERITE) {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        }else {
            return BlockTags.NEEDS_STONE_TOOL;
        }
    }

}
