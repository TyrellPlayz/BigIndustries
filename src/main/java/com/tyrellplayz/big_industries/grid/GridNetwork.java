package com.tyrellplayz.big_industries.grid;

import com.tyrellplayz.big_industries.tile.grid.GridNetworkTile;
import com.tyrellplayz.big_industries.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nonnull;
import java.util.*;

public abstract class GridNetwork<NETWORK extends GridNetwork<NETWORK,TILE>, TILE extends GridNetworkTile<NETWORK,TILE>> {

    // The networks id
    private final UUID NETWORK_ID;
    protected final List<TILE> LINKED_TILES = new ArrayList<>();

    public GridNetwork(UUID gridId, TILE startTile) {
        this.NETWORK_ID = gridId;
        floodFillNetwork(startTile);
    }

    /**
     * @return The type of gird the grid is.
     */
    public abstract GridType getType();

    /**
     * Deletes the grid and removes it from linked tiles.
     */
    public abstract void deleteGrid();

    /**
     * Merges multiple grids together into the grid.
     * @return The new grid.
     */
    public abstract void mergeGrids(NETWORK... grids);

    public void addTile(TILE tile) {
        if(tile.getGridNetwork() != null && tile.getGridNetwork().equals(this)) return;
        tile.setGridNetwork((NETWORK) this);
        LINKED_TILES.add(tile);
    }

    public void onBlockPlacedNextTo(IWorld world, BlockState blockState, BlockPos blockPos) {

    }

    protected void floodFillNetwork(TILE startTile) {
        Queue<TILE> tilesToCheck = new ArrayDeque<>();
        tilesToCheck.add(startTile);

        while (tilesToCheck.size() > 0) {
            TILE tile = tilesToCheck.remove();
            if (!LINKED_TILES.contains(tile)) {
                addTile(tile);
                List<TILE> neighbours = Util.getTileNeighboursOfSameType(tile);
                neighbours.forEach(neighbourTile -> {
                    if (neighbourTile != null && !LINKED_TILES.contains(neighbourTile) && !neighbourTile.isRemoved()) tilesToCheck.add(neighbourTile);
                });

            }
        }

    }

    public UUID getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridNetwork<?, ?> that = (GridNetwork<?, ?>) o;

        return NETWORK_ID.equals(that.NETWORK_ID);
    }

    @Override
    public int hashCode() {
        return NETWORK_ID.hashCode();
    }
}
