package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.big_industries.item.HammerItem;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlastBrickBlock extends Block {

    public BlastBrickBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if((player.getItemInHand(hand).getItem() instanceof HammerItem) && !level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            if(MultiblockType.BLAST_FURNACE.isStructureValid(serverLevel,pos,player)) {
                System.out.println("Success");
                MultiblockType.BLAST_FURNACE.construct(serverLevel,pos,player);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
