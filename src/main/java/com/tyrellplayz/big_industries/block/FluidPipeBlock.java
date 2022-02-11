package com.tyrellplayz.big_industries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FluidPipeBlock extends PipeBlock {

    public FluidPipeBlock(Properties properties) {
        super(0.25F, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter getter = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
                .setValue(DOWN, getter.getBlockState(pos.below()).is(this))
                .setValue(UP, getter.getBlockState(pos.above()).is(this))
                .setValue(NORTH, getter.getBlockState(pos.north()).is(this))
                .setValue(EAST, getter.getBlockState(pos.east()).is(this))
                .setValue(SOUTH, getter.getBlockState(pos.south()).is(this))
                .setValue(WEST, getter.getBlockState(pos.west()).is(this));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
        boolean flag = neighborState.getBlock() == this;
        
        return state.setValue(PROPERTY_BY_DIRECTION.get(direction), flag);
    }

}
