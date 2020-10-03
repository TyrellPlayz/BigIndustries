package com.tyrellplayz.big_industries.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Represents a group of blocks that form on giant block. One being the parent and the others children of that parent.
 */
public class Multiblock {

    private MultiblockType<?> type;

    private World world;
    private BlockPos parent;
    private List<BlockPos> children;

    public Multiblock() {
    }

    /**
     * Gets called when the multiblock is created.
     */
    public void onCreate() {

    }

    /**
     * Gets called when the multiblock is destroyed which is before the parent or a child is removed.
     * @param removedBlockPos The pos of the block that is being removed.
     */
    public void onDestroyed(BlockPos removedBlockPos) {
        if(world.getTileEntity(parent) != null) {
            TileEntity tile = world.getTileEntity(parent);
            if(tile instanceof IInventory) InventoryHelper.dropInventoryItems(getWorld(),removedBlockPos, (IInventory) tile);
        }
    }

    public MultiblockType<?> getType() {
        return type;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getParent() {
        return parent;
    }

    public List<BlockPos> getChildren() {
        return children;
    }
}
