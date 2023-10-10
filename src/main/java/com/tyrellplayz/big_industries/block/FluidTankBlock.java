package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.Port;
import com.tyrellplayz.big_industries.blockentity.FluidTankEntity;
import com.tyrellplayz.big_industries.core.BIItems;
import com.tyrellplayz.zlib.util.ServerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

public class FluidTankBlock extends Block implements EntityBlock{

    public static final Port.Property NORTH_PORT = Port.Property.create("north_port",Port.NONE,Port.IMPORT,Port.EXPORT);
    public static final Port.Property EAST_PORT = Port.Property.create("east_port",Port.NONE,Port.IMPORT,Port.EXPORT);
    public static final Port.Property SOUTH_PORT = Port.Property.create("south_port",Port.NONE,Port.IMPORT,Port.EXPORT);
    public static final Port.Property WEST_PORT = Port.Property.create("west_port",Port.NONE,Port.IMPORT,Port.EXPORT);
    public static final Port.Property UP_PORT = Port.Property.create("up_port",Port.NONE,Port.IMPORT,Port.EXPORT);
    public static final Port.Property DOWN_PORT = Port.Property.create("down_port",Port.NONE,Port.IMPORT,Port.EXPORT);

    public FluidTankBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH_PORT, Port.NONE)
                .setValue(EAST_PORT, Port.NONE)
                .setValue(SOUTH_PORT, Port.NONE)
                .setValue(WEST_PORT, Port.NONE)
                .setValue(UP_PORT, Port.NONE)
                .setValue(DOWN_PORT, Port.NONE));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH_PORT, EAST_PORT, SOUTH_PORT, WEST_PORT, UP_PORT, DOWN_PORT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(ServerUtil.isServerLevel(level)) {
            ItemStack heldItem = player.getItemInHand(hand);
            if(!heldItem.isEmpty() && heldItem.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
                return FluidUtil.interactWithFluidHandler(player, hand, level, pos, null) ? InteractionResult.SUCCESS : InteractionResult.PASS;
            }else if(!heldItem.isEmpty() && heldItem.is(BIItems.WRENCH.get())) {
                Direction side = hitResult.getDirection();
                Port.Property portProperty = portFromDirection(side);
                Port nextPort = state.getValue(portProperty).next();
                state = state.setValue(portProperty,nextPort);
                level.setBlockAndUpdate(pos,state);
                return InteractionResult.SUCCESS;
            }
            IFluidHandler handler = FluidUtil.getFluidHandler(level,pos,hitResult.getDirection()).orElse(null);
            player.sendSystemMessage(Component.literal("Stored: "+handler.getFluidInTank(1).getAmount()+"/"+handler.getTankCapacity(1)));
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FluidTankEntity(pos,state);
    }

    public static Port.Property portFromDirection(Direction direction) {
        switch (direction){
            case NORTH -> {
                return NORTH_PORT;
            }
            case EAST -> {
                return EAST_PORT;
            }
            case SOUTH -> {
                return SOUTH_PORT;
            }
            case WEST -> {
                return WEST_PORT;
            }
            case UP -> {
                return UP_PORT;
            }
            case DOWN -> {
                return DOWN_PORT;
            }
        }
        return NORTH_PORT;
    }

}
