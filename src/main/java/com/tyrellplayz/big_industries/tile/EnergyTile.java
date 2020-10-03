package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.big_industries.grid.energy.BasicEnergyStorage;
import com.tyrellplayz.zlib.tile.ZTile;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyTile extends ZTile implements ITickableTileEntity {

    private BasicEnergyStorage energyStorage;

    public EnergyTile(TileEntityType<?> tileEntityTypeIn, BasicEnergyStorage energyStorage) {
        super(tileEntityTypeIn);
        this.energyStorage = energyStorage;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.orEmpty(cap,LazyOptional.of(() -> energyStorage));
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {

    }
}
