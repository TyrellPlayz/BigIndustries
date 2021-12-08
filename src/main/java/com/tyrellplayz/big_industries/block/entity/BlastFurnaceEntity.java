package com.tyrellplayz.big_industries.block.entity;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.multiblock.BlastFurnaceMultiblock;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BlastFurnaceEntity extends MultiblockEntity<BlastFurnaceEntity, BlastFurnaceMultiblock> implements Container {

    public BlastFurnaceEntity(BlockPos pos, BlockState state) {
        super(BIBlockEntities.BLAST_FURNACE.get(), MultiblockType.BLAST_FURNACE, pos, state);
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int p_18941_) {
        return null;
    }

    @Override
    public ItemStack removeItem(int p_18942_, int p_18943_) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_18951_) {
        return null;
    }

    @Override
    public void setItem(int p_18944_, ItemStack p_18945_) {

    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
