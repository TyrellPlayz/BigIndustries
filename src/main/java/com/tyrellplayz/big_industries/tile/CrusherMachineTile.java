package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.big_industries.core.BITiles;
import com.tyrellplayz.big_industries.grid.energy.BasicEnergyStorage;
import com.tyrellplayz.big_industries.common.container.CrusherMachineContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrusherMachineTile extends InventoryTile implements ITickableTileEntity, INamedContainerProvider {

    public BasicEnergyStorage energyStorage = new BasicEnergyStorage(10000);
    protected final IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch(index) {
                case 0:
                    return CrusherMachineTile.this.energyStorage.getEnergyStored();
                case 1:
                    return CrusherMachineTile.this.energyStorage.getMaxEnergyStored();
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CrusherMachineTile.this.energyStorage.setEnergy(value);
                    break;
                case 1:
                    break;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public CrusherMachineTile() {
        super(BITiles.CRUSHER_MACHINE, 2);
    }

    @Override
    public void tick() {

    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrusherMachineContainer(id,playerInventory,this,data);
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState,compound);
        this.energyStorage.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.energyStorage.write(compound);
        return compound;
    }

    private LazyOptional<?> energyHandler = LazyOptional.of(() -> this.energyStorage);
    private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return this.energyHandler.cast();
        }
        if (!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY )
            return this.itemHandler.cast();
        return super.getCapability(cap, side);
    }
}
