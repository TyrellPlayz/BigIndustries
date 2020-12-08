package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.tile.CokeOvenTile;
import com.tyrellplayz.big_industries.tile.CombustionEngineTile;
import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.DyeColor;
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

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(ServerHelper.isServerWorld(worldIn)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CokeOvenTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player,(INamedContainerProvider) tileEntity,pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CokeOvenTile();
    }
}
