package com.tyrellplayz.big_industries.multiblock;

import net.minecraft.block.Block;

public class MultiblockType<T extends Multiblock> extends net.minecraftforge.registries.ForgeRegistryEntry<MultiblockType<T>> {

    public MultiblockType() {
    }

    public T create(Block parentBlock) {
        return null;
    }

}
