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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
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
        return state.setValue(PROPERTY_BY_DIRECTION.get(direction), connectsTo(accessor,neighborState,neighborPos,direction));
    }

    public boolean connectsTo(LevelAccessor level, BlockState neighborState, BlockPos neighborPos, Direction direction) {
        boolean flag1 = neighborState.getBlock() == this;
        boolean flag2 = true;
        if(flag1) {
            if(level.getBlockEntity(neighborPos) instanceof FluidPipeEntity fluidPipeEntity) {
                flag2 = !fluidPipeEntity.isSideDisabled(direction);
            }else {
                flag2 = false;
            }
        }
        return flag1 && flag2;
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

    @javax.annotation.Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
        return type2 == type ? (BlockEntityTicker<A>)ticker : null;
    }

    public VoxelShape[] getCollisionShapes(LevelAccessor level, BlockState state, BlockPos pos) {
        List<VoxelShape> shapes = new ArrayList<>();
        shapes.add(VoxelShapeHelper.create(4,4,4,4,4,4));

        if(state.getValue(NORTH)) {
            shapes.add(VoxelShapeHelper.create(0,4,12,4,4,4));
        }
        if(state.getValue(EAST)) {
            shapes.add(VoxelShapeHelper.create(4,0,4,12,4,4));
        }
        if(state.getValue(SOUTH)) {
            shapes.add(VoxelShapeHelper.create(12,4,0,4,4,4));
        }
        if(state.getValue(WEST)) {
            shapes.add(VoxelShapeHelper.create(4,12,4,0,4,4));
        }
        if(state.getValue(UP)) {
            shapes.add(VoxelShapeHelper.create(4,4,4,4,0,12));
        }
        if(state.getValue(DOWN)) {
            shapes.add(VoxelShapeHelper.create(4,4,4,4,12,0));
        }

        return shapes.toArray(new VoxelShape[]{});
    }

}
