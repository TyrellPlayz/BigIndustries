package com.tyrellplayz.big_industries.multiblock;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Multiblock<M extends Multiblock<M,T>, T extends MultiblockType<M>> {

    public Level world;
    public BlockPos parent;
    protected ImmutableList<BlockPos> children;

    public void construct(Level level, BlockPos pos, Player player) {

    }

    public void deconstruct(Level level, BlockPos pos, Player player) {

    }

    public boolean isStructureValid(Level level, BlockPos pos, Player player) {
        return false;
    }

}
