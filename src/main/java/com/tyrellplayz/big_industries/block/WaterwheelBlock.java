package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.tile.WaterwheelTile;
import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class WaterwheelBlock extends ZHorizontalBlock {

    public WaterwheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WaterwheelTile();
    }
}
