package com.tyrellplayz.big_industries.grid.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class BasicEnergyStorage extends EnergyStorage {

    public BasicEnergyStorage(int capacity) {
        super(capacity);
    }

    public BasicEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public BasicEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public BasicEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void read(CompoundNBT compound) {
        this.energy = compound.getInt("Energy");
        this.capacity = compound.getInt("Capacity");
        this.maxReceive = compound.getInt("MaxReceive");
        this.maxExtract = compound.getInt("MaxExtract");
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("Energy",this.energy);
        compound.putInt("Capacity",this.capacity);
        compound.putInt("MaxReceive",this.maxReceive);
        compound.putInt("MaxExtract",this.maxExtract);
        return compound;
    }

}
