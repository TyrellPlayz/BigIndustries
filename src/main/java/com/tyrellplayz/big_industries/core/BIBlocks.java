package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class BIBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,BigIndustries.MOD_ID);

    // Tin
    public static final RegistryObject<Block> TIN_ORE = register("tin_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = register("deepslate_tin_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> RAW_TIN_BLOCK = register("raw_tin_block",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> TIN_BLOCK = register("tin_block",new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F,6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    // Lead
    public static final RegistryObject<Block> LEAD_ORE = register("lead_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = register("deepslate_lead_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = register("raw_lead_block",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> LEAD_BLOCK = register("lead_block",new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    // Aluminium
    public static final RegistryObject<Block> ALUMINIUM_ORE = register("aluminium_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINIUM_ORE = register("deepslate_aluminium_ore",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> RAW_ALUMINIUM_BLOCK = register("raw_aluminium_block",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    public static final RegistryObject<Block> ALUMINIUM_BLOCK = register("aluminium_block",new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    // Steel
    public static final RegistryObject<Block> STEEL_BLOCK = register("steel_block",new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F,6.0F)),block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    public static final RegistryObject<Block> BLAST_BRICK = register("blast_brick",new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 9.0F)),block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));
    //public static final RegistryObject<Block> BLAST_FURNACE = register("blast_furnace",new BlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,9.0F)), block -> new BlockItem(block,new Item.Properties()));

    //public static final RegistryObject<Block> BOILER = register("boiler",new BoilerBlock(BlockBehaviour.Properties.of(Material.METAL)), block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    //public static final RegistryObject<Block> PIPE = register("pipe",new PipeBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()),block -> new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB)));

    /**
     * Registers a block.
     * @param registryName The registry name of the block.
     * @param block A new instance of the block.
     * @param blockItemFunction
     * @return
     */
    public static <T extends Block> RegistryObject<T> register(String registryName, T block, Function<Block,BlockItem> blockItemFunction) {
        if(blockItemFunction != null) {
            BIItems.REGISTER.register(registryName,() -> blockItemFunction.apply(block));
        }
        return REGISTER.register(registryName,() -> block);
    }

}
