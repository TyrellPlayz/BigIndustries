package com.tyrellplayz.big_industries.tile.grid;

import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.big_industries.util.Util;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.List;

public class EnergyGridNetworkTile extends GridNetworkTile<EnergyGridNetwork,EnergyGridNetworkTile> implements IEnergyStorage {

    public EnergyGridNetworkTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(ServerHelper.isServerWorld(worldIn)) {
            // Check if the tile is next to another network. if so join that network or create a new one.
            // If the are multiple networks then merge them together.

            List<EnergyGridNetwork> networks = Util.getGridsOfType(EnergyGridNetwork.class,this);
            if(networks.size() > 0) {
                // if there are networks next to this network merge them together.
                getGridManager().mergeGrids(EnergyGridNetwork.class,this,networks.toArray(new EnergyGridNetwork[]{}));
            }else {
                getGridManager().createGrid(EnergyGridNetwork.class,this);
            }
        }
    }

    @Override
    public void remove() {
        this.removed = true;

        if(ServerHelper.isServerWorld(getWorld())) {
            List<EnergyGridNetwork> networks = Util.getGridsOfType(EnergyGridNetwork.class,this);
            if(networks.size() > 0) {
                getGridManager().splitGrid(EnergyGridNetwork.class,this);
            }else {
                getGridManager().deleteGrid(getGridNetwork().getNetworkId());
            }
        }

        this.invalidateCaps();
        requestModelDataUpdate();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return getGridNetwork().receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return getGridNetwork().extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored() {
        return getGridNetwork().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return getGridNetwork().getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return getGridNetwork().canExtract();
    }

    @Override
    public boolean canReceive() {
        return getGridNetwork().canReceive();
    }
}
