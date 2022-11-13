package com.tyrellplayz.big_industries.block.multiblock;

import com.tyrellplayz.big_industries.blockentity.BlastFurnaceEntity;
import com.tyrellplayz.big_industries.blockentity.MultiblockEntityChild;
import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.item.HammerItem;
import com.tyrellplayz.big_industries.multiblock.IMultiblockEntity;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class BlastFurnaceBlock extends MultiblockBlock {


    public BlastFurnaceBlock(Properties properties) {
        super(null,properties);
        //super(MultiblockType.BLAST_FURNACE,properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if(state.getValue(PARENT)) return new BlastFurnaceEntity(pos,state);
        return new MultiblockEntityChild(pos, state);
    }

    /*
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, BIBlockEntities.BLAST_FURNACE.get(), BlastFurnaceEntity::onServerTick);
    }
     */
}
