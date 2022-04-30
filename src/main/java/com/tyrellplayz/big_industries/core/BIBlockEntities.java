package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.block.FluidPipeBlock;
import com.tyrellplayz.big_industries.blockentity.BlastFurnaceEntity;
import com.tyrellplayz.big_industries.blockentity.FluidPipeEntity;
import com.tyrellplayz.big_industries.blockentity.MultiblockEntityChild;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BIBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,BigIndustries.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlastFurnaceEntity>> BLAST_FURNACE = register("blastfurnace", BlastFurnaceEntity::new,() -> new Block[]{BIBlocks.BLAST_FURNACE.get()});
    public static final RegistryObject<BlockEntityType<MultiblockEntityChild>> MULTI_BLOCK_CHILD = register("multiblock_child", MultiblockEntityChild::new,() -> new Block[]{BIBlocks.BLAST_FURNACE.get()});

    public static final RegistryObject<BlockEntityType<FluidPipeEntity>> FLUID_PIPE = register("fluid_pipe", FluidPipeEntity::new, () -> new Block[]{BIBlocks.FLUID_PIPE.get()});

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryName, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> validBlocksSupplier) {
        return REGISTER.register(registryName, () -> BlockEntityType.Builder.of(supplier,validBlocksSupplier.get()).build(null));
    }

}
