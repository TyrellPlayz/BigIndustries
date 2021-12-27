package com.tyrellplayz.big_industries.blockentity;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlockEntity extends BlockEntity {

    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(BIBlockEntities.TEST_BLOCK.get(), pos, state);
    }
}
