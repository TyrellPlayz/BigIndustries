package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.block.BlastBrickBlock;
import com.tyrellplayz.big_industries.block.FluidPipeBlock;
import com.tyrellplayz.big_industries.block.multiblock.BlastFurnaceBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class BIBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,BigIndustries.MOD_ID);
    public static final Map<Metals,RegistryObject<Block>> ORE = new HashMap<>();
    public static final Map<Metals,RegistryObject<Block>> DEEPSLATE_ORE = new HashMap<>();
    public static final Map<Metals,RegistryObject<Block>> RAW_STORAGE_BLOCK = new HashMap<>();
    public static final Map<Metals,RegistryObject<Block>> STORAGE_BLOCK = new HashMap<>();

    static {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                String oreName = metal+"_ore";
                ORE.put(metal,register(oreName,() -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F)), BIBlocks::tabBlockItem));
                String deepslateName = "deepslate_"+metal+"_ore";
                DEEPSLATE_ORE.put(metal,register(deepslateName,() -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), BIBlocks::tabBlockItem));
                String rawName = "raw_"+metal+"_block";
                RAW_STORAGE_BLOCK.put(metal,register(rawName,() -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)), BIBlocks::tabBlockItem));
            }
            String blockName = metal+"_block";
            STORAGE_BLOCK.put(metal,register(blockName,() -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F,6.0F)), BIBlocks::tabBlockItem));
        }
    }

    public static final RegistryObject<Block> BLAST_BRICK = register("blast_brick",() -> new BlastBrickBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 9.0F)), BIBlocks::tabBlockItem);
    //public static final RegistryObject<Block> BLAST_FURNACE = register("blast_furnace",() -> new BlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,9.0F).noDrops().dynamicShape().noOcclusion()), BIBlocks::simpleBlockItem);

    public static final RegistryObject<Block> FLUID_PIPE = register("fluid_pipe",() -> new FluidPipeBlock(BlockBehaviour.Properties.of(Material.METAL).dynamicShape().noOcclusion()), BIBlocks::tabBlockItem);

    /**
     * Registers a block.
     * @param registryName The registry name of the block.
     * @param blockSupplier A new instance of the block.
     * @param blockItemFunction
     * @return
     */
    public static <T extends Block> RegistryObject<T> register(String registryName, Supplier<T> blockSupplier, Function<T,BlockItem> blockItemFunction) {
        RegistryObject<T> registryObject = REGISTER.register(registryName,blockSupplier);
        if(blockItemFunction != null) {
            BIItems.REGISTER.register(registryName,() -> blockItemFunction.apply(registryObject.get()));
        }
        return registryObject;
    }

    private static BlockItem simpleBlockItem(Block block) {
        return new BlockItem(block,new Item.Properties());
    }

    private static BlockItem tabBlockItem(Block block) {
        return new BlockItem(block,new Item.Properties().tab(BigIndustries.BLOCK_TAB));
    }

}
