package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.blockentity.FluidPipeEntity;
import com.tyrellplayz.big_industries.util.helper.VoxelShapeHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FluidPipeBlock extends PipeBlock implements EntityBlock {

    public FluidPipeBlock(Properties properties) {
        super(0.25F, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter getter = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = this.defaultBlockState();
        return getPipeState(state,getter,pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor accessor, BlockPos pos, BlockPos neighborPos) {
        return state.setValue(PROPERTY_BY_DIRECTION.get(direction), canConnectTo(state,accessor,pos,direction));
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        BlockState newState = getPipeState(state,level,pos);
        
    }

    public boolean canConnectTo(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.relative(direction);
        BlockEntity neighborBlockEntity = level.getBlockEntity(neighborPos);
        if(neighborBlockEntity instanceof FluidPipeEntity fluidPipeEntity) {
            return !fluidPipeEntity.isSideDisabled(direction);
        } else if (neighborBlockEntity != null && neighborBlockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, direction.getOpposite()).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        double camX = camera.getPosition().x;
        double camY = camera.getPosition().y;
        double camZ = camera.getPosition().z;


        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FluidPipeEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return EntityBlock.super.getTicker(level, state, type);
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends AbstractFurnaceBlockEntity> p_151990_) {
        return p_151988_.isClientSide ? null : createTickerHelper(p_151989_, p_151990_, AbstractFurnaceBlockEntity::serverTick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
        return type2 == type ? (BlockEntityTicker<A>)ticker : null;
    }

    protected BlockState getPipeState(BlockState state, BlockGetter level, BlockPos pos) {
        return state
                .setValue(DOWN, canConnectTo(state,level,pos,Direction.DOWN))
                .setValue(UP, canConnectTo(state,level,pos,Direction.UP))
                .setValue(NORTH, canConnectTo(state,level,pos,Direction.NORTH))
                .setValue(EAST, canConnectTo(state,level,pos,Direction.EAST))
                .setValue(SOUTH, canConnectTo(state,level,pos,Direction.SOUTH))
                .setValue(WEST, canConnectTo(state,level,pos,Direction.WEST));
    }

    public VoxelShape[] getCollisionShapes(LevelAccessor level, BlockState state, BlockPos pos) {
        List<VoxelShape> shapes = new ArrayList<>();
        int pipeSize = 5;
        shapes.add(VoxelShapeHelper.create(pipeSize,pipeSize,pipeSize,pipeSize,pipeSize,pipeSize));

        if(state.getValue(NORTH)) {
            shapes.add(VoxelShapeHelper.create(0,pipeSize,12,pipeSize,pipeSize,pipeSize));
        }
        if(state.getValue(EAST)) {
            shapes.add(VoxelShapeHelper.create(pipeSize,0,pipeSize,12,pipeSize,pipeSize));
        }
        if(state.getValue(SOUTH)) {
            shapes.add(VoxelShapeHelper.create(12,pipeSize,0,pipeSize,pipeSize,pipeSize));
        }
        if(state.getValue(WEST)) {
            shapes.add(VoxelShapeHelper.create(pipeSize,12,pipeSize,0,pipeSize,pipeSize));
        }
        if(state.getValue(UP)) {
            shapes.add(VoxelShapeHelper.create(pipeSize,pipeSize,pipeSize,pipeSize,0,12));
        }
        if(state.getValue(DOWN)) {
            shapes.add(VoxelShapeHelper.create(pipeSize,pipeSize,pipeSize,pipeSize,12,0));
        }

        return shapes.toArray(new VoxelShape[]{});
    }

}
