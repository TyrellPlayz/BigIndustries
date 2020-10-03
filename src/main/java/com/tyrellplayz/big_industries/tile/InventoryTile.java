package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.zlib.tile.ZTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class InventoryTile extends ZTile implements IInventory {

    protected NonNullList<ItemStack> itemStacks;

    public InventoryTile(TileEntityType<?> tileEntityTypeIn, int size) {
        super(tileEntityTypeIn);
        itemStacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    public int getSizeInventory() {
        return itemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.itemStacks) {
            if (!itemstack.isEmpty())return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.itemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.itemStacks,index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.itemStacks.set(index,stack);
        if(stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) return false;
        else return !(player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) > 64.0D);
    }

    @Override
    public void openInventory(PlayerEntity player) {
    }

    @Override
    public void closeInventory(PlayerEntity player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return true;
    }


    @Override
    public void clear() {
        this.itemStacks.clear();
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState,compound);
        this.itemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);

        return compound;
    }
}
