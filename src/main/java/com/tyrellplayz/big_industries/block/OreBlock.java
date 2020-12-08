package com.tyrellplayz.big_industries.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class OreBlock extends Block {

    public OreBlock(ItemTier tier) {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(tier.getHarvestLevel()));
    }

}
