package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.common.container.CrusherMachineContainer;
import com.tyrellplayz.big_industries.tile.CrusherMachineTile;
import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class CrusherMachineBlock extends ZHorizontalBlock {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CrusherMachineBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(LIT,false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(LIT) ? super.getLightValue(state,world,pos) : 0;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(ServerHelper.isServerWorld(worldIn)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CrusherMachineTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player,(INamedContainerProvider) tileEntity,pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LIT);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CrusherMachineTile();
    }
}
