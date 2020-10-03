package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.big_industries.core.BITiles;
import com.tyrellplayz.big_industries.tile.grid.EnergyGridNetworkTile;
import net.minecraft.tileentity.ITickableTileEntity;

public class EnergyConduitTile extends EnergyGridNetworkTile implements ITickableTileEntity {

    public EnergyConduitTile() {
        super(BITiles.ENERGY_CONDUIT);
    }

    @Override
    public void tick() {

    }

}
