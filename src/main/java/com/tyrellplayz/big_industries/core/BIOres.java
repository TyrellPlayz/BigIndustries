package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.util.BlockNames;
import com.tyrellplayz.zlib.registry.OreRegistry;
import com.tyrellplayz.zlib.world.ore.OreType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BIOres extends OreRegistry {

    public static final OreType COPPER_ORE = register(BlockNames.COPPER_ORE, OreType.createOverworldOre(BIBlocks.COPPER_ORE.getDefaultState(),8,8,40,75));
    public static final OreType LEAD_ORE = register(BlockNames.LEAD_ORE, OreType.createOverworldOre(BIBlocks.LEAD_ORE.getDefaultState(),8,4,10,35));
    public static final OreType TIN_ORE = register(BlockNames.TIN_ORE, OreType.createOverworldOre(BIBlocks.TIN_ORE.getDefaultState(),8,7,20,55));
    public static final OreType SILVER_ORE = register(BlockNames.SILVER_ORE, OreType.createOverworldOre(BIBlocks.SILVER_ORE.getDefaultState(),8,4,5,30));
    public static final OreType NICKEL_ORE = register(BlockNames.NICKEL_ORE, OreType.createOverworldOre(BIBlocks.NICKEL_ORE.getDefaultState(),8,2,5,20));

}
