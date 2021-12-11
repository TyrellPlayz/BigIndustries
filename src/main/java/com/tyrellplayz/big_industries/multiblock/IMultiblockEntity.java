package com.tyrellplayz.big_industries.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IMultiblockEntity {

    /**
     * @return The parents position of the multiblock.
     */
    BlockPos getParent();

    /**
     * @return The parents entity of the multiblock.
     */
    BlockEntity getParentEntity();

    /**
     * @return The registry name of the block that was replaced by the multiblock.
     * Used when the multiblock is destroyed.
     */
    ResourceLocation getPreviousBlock();

    /**
     * @return If the Multiblock is being deconstructed
     */
    boolean isBeingDeconstructed();

}
