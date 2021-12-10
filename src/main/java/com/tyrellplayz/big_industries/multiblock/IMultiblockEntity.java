package com.tyrellplayz.big_industries.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IMultiblockEntity {

    BlockPos getParent();

    BlockEntity getParentEntity();

}
