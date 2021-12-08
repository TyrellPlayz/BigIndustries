package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.block.entity.BlastFurnaceEntity;
import com.tyrellplayz.big_industries.block.entity.MultiblockEntity;
import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class BlastFurnaceBlock extends MultiblockBlock {

    public BlastFurnaceBlock(Properties properties) {
        super(properties);
    }

    /*
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide) return InteractionResult.SUCCESS;
        if(level.getBlockEntity(pos) instanceof BlastFurnaceEntity blockEntity) {
            NetworkHooks.openGui((ServerPlayer) player,blockEntity,pos);
        }
        return InteractionResult.CONSUME;
    }
     */

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(stack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            //if(blockEntity instanceof BlastFurnaceEntity blastFurnaceEntity) {
            //    ((BlastFurnaceEntity)blockEntity).setCustomName(stack.getHoverName());
            //}
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean b) {
        if(!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof BlastFurnaceEntity blastFurnaceEntity) {
                if(level instanceof ServerLevel serverLevel) {
                    MultiblockType.BLAST_FURNACE.deconstruct(serverLevel,pos,blastFurnaceEntity.getParent());
                }
                level.updateNeighbourForOutputSignal(pos,this);
            }
            super.onRemove(state, level, pos, newState, b);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlastFurnaceEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, BIBlockEntities.BLAST_FURNACE.get(), BlastFurnaceEntity::onServerTick);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof MultiblockEntity<?,?> mbEntity) {
            BigIndustries.getLogger().info(mbEntity.getParent());
        }
        return InteractionResult.SUCCESS;
    }
}
