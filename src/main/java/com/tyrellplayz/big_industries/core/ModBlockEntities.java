package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.block.entity.BlastFurnaceEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,BigIndustries.MOD_ID);

    //public static final RegistryObject<BlockEntityType<BlastFurnaceEntity>> BLAST_FURNACE = register("blastfurnace",BlastFurnaceEntity::new,() -> new Block[]{ModBlocks.BLAST_FURNACE.get()});

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryName, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> validBlocksSupplier) {
        return REGISTER.register(registryName, () -> BlockEntityType.Builder.of(supplier,validBlocksSupplier.get()).build(null));
    }

}
