package com.tyrellplayz.big_industries.grid.energy;

import com.tyrellplayz.big_industries.grid.GridNetwork;
import com.tyrellplayz.big_industries.grid.GridType;
import com.tyrellplayz.big_industries.tile.grid.EnergyGridNetworkTile;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnergyGridNetwork extends GridNetwork<EnergyGridNetwork, EnergyGridNetworkTile> implements IEnergyStorage {

    // The grids energy storage.
    private final IEnergyStorage ENERGY_STORAGE;
    // A list of all energy acceptors. (Blocks that accept/require energy)
    private final List<IEnergyStorage> ENERGY_ACCEPTORS = new ArrayList<>();

    public EnergyGridNetwork(UUID networkID, EnergyGridNetworkTile startTile) {
        this(networkID,startTile,new BasicEnergyStorage(10000));
    }

    public EnergyGridNetwork(UUID NETWORK_ID, EnergyGridNetworkTile startTile, IEnergyStorage ENERGY_STORAGE) {
        super(NETWORK_ID, startTile);
        this.ENERGY_STORAGE = ENERGY_STORAGE;
    }

    @Override
    public GridType getType() {
        return GridType.ENERGY;
    }

    @Override
    public void deleteGrid() {
        LINKED_TILES.forEach(energyGridNetworkTile -> {
            if(energyGridNetworkTile.getGridNetwork().equals(this)) {
                energyGridNetworkTile.setGridNetwork(null);
            }
        });
    }

    @Override
    public void mergeGrids(EnergyGridNetwork... grids) {
        for (EnergyGridNetwork grid : grids) {
            grid.LINKED_TILES.forEach(this::addTile);
            grid.ENERGY_ACCEPTORS.forEach(this::addEnergyAcceptor);
        }
    }

    public void addEnergyAcceptor(IEnergyStorage energyAcceptor) {
        ENERGY_ACCEPTORS.add(energyAcceptor);
    }

    public void removeEnergyAcceptor(IEnergyStorage energyAcceptor) {
        ENERGY_ACCEPTORS.remove(energyAcceptor);
    }

    @Override
    public void onBlockPlacedNextTo(IWorld world, BlockState blockState, BlockPos blockPos) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        if(tileEntity instanceof IEnergyStorage) {
            addEnergyAcceptor((IEnergyStorage)tileEntity);
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return ENERGY_STORAGE.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return ENERGY_STORAGE.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored() {
        return ENERGY_STORAGE.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return ENERGY_STORAGE.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return ENERGY_STORAGE.canExtract();
    }

    @Override
    public boolean canReceive() {
        return ENERGY_STORAGE.canReceive();
    }
}
