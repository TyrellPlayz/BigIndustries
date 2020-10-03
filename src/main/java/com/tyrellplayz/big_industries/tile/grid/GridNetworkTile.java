package com.tyrellplayz.big_industries.tile.grid;

import com.tyrellplayz.big_industries.events.WorldEvents;
import com.tyrellplayz.big_industries.grid.GridManager;
import com.tyrellplayz.big_industries.grid.GridNetwork;
import com.tyrellplayz.zlib.tile.ZTile;
import net.minecraft.tileentity.TileEntityType;

public abstract class GridNetworkTile<NETWORK extends GridNetwork<NETWORK,TILE>, TILE extends GridNetworkTile<NETWORK,TILE>> extends ZTile {

    private NETWORK gridNetwork;

    public GridNetworkTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public GridManager getGridManager() {
        return WorldEvents.worldIGridManagerMap.get(getWorld());
    }

    public NETWORK getGridNetwork() {
        return gridNetwork;
    }

    public void setGridNetwork(NETWORK gridNetwork) {
        this.gridNetwork = gridNetwork;
    }

    public boolean hasNetwork() {
        return gridNetwork != null;
    }

}
