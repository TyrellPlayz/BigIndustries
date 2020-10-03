package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class CokeOvenController extends ZHorizontalBlock {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CokeOvenController(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(LIT,false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LIT);
    }

}
