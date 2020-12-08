package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.block.*;
import com.tyrellplayz.big_industries.util.BlockNames;
import com.tyrellplayz.zlib.registry.BlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = com.tyrellplayz.big_industries.BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BIBlocks extends BlockRegistry {

    // Copper
    public static final Block COPPER_BLOCK = register(BlockNames.COPPER_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    public static final Block COPPER_ORE = register(BlockNames.COPPER_ORE, new OreBlock(ItemTier.STONE), BigIndustries.BLOCKS_GROUP);
    // Lead
     public static final Block LEAD_BLOCK = register(BlockNames.LEAD_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    public static final Block LEAD_ORE = register(BlockNames.LEAD_ORE, new OreBlock(ItemTier.IRON), BigIndustries.BLOCKS_GROUP);
    // Tin
    public static final Block TIN_BLOCK = register(BlockNames.TIN_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    public static final Block TIN_ORE = register(BlockNames.TIN_ORE, new OreBlock(ItemTier.IRON), BigIndustries.BLOCKS_GROUP);
    // Silver
    public static final Block SILVER_BLOCK = register(BlockNames.SILVER_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    public static final Block SILVER_ORE = register(BlockNames.SILVER_ORE, new OreBlock(ItemTier.IRON), BigIndustries.BLOCKS_GROUP);
    // Nickel
    public static final Block NICKEL_BLOCK = register(BlockNames.NICKEL_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    public static final Block NICKEL_ORE = register(BlockNames.NICKEL_ORE, new OreBlock(ItemTier.IRON), BigIndustries.BLOCKS_GROUP);
    // Steel
    public static final Block STEEL_BLOCK = register(BlockNames.STEEL_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    // Bronze
    public static final Block BRONZE_BLOCK = register(BlockNames.BRONZE_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);
    // Invar
    public static final Block INVAR_BLOCK = register(BlockNames.INVAR_BLOCK, new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);

    public static final Block WATERWHEEL = register(BlockNames.WATERWHEEL, new WaterwheelBlock(AbstractBlock.Properties.create(Material.WOOD).notSolid()),BigIndustries.BLOCKS_GROUP);

    public static final Block COKE_BRICKS = register(BlockNames.COKE_BRICKS,new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.0F, 6.0F)), BigIndustries.BLOCKS_GROUP);
    public static final Block COKE_OVEN_CONTROLLER = register(BlockNames.COKE_OVEN_CONTROLLER,new CokeOvenController(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.0F,6.0F).setLightLevel(returnLightValueIfLit(5))), BigIndustries.BLOCKS_GROUP);

    public static final Block STEAM_ENGINE = register(BlockNames.STEAM_ENGINE,new SteamEngine(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.METAL)),BigIndustries.BLOCKS_GROUP);
    //public static final Block COMBUSTION_ENGINE = register(BlockNames.COMBUSTION_ENGINE,new CombustionEngineBlock(Block.Properties.create(Material.IRON)), BigIndustries.BLOCKS_GROUP);
    public static final Block ENERGY_CONDUIT = register(BlockNames.ENERGY_CONDUIT, new EnergyConduitBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL)), BigIndustries.BLOCKS_GROUP);

    // Machines
    public static final Block CRUSHER_MACHINE = register(BlockNames.CRUSHER_MACHINE,new CrusherMachineBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).setLightLevel(returnLightValueIfLit(5))), BigIndustries.BLOCKS_GROUP);

    public static Collection<Block> getBlocks(){
        return BLOCKS;
    }

}
