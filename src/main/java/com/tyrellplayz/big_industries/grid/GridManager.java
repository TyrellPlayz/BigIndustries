package com.tyrellplayz.big_industries.grid;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.big_industries.network.server.SEnergyGridCreatedMessage;
import com.tyrellplayz.big_industries.network.server.SEnergyGridDeletedMessage;
import com.tyrellplayz.big_industries.tile.grid.EnergyGridNetworkTile;
import com.tyrellplayz.big_industries.tile.grid.GridNetworkTile;
import com.tyrellplayz.big_industries.util.Util;
import net.minecraft.world.IWorld;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GridManager {

    protected final IWorld world;
    protected final List<GridNetwork<?,?>> GRID_NETWORK_LISTS = new ArrayList<>();

    public GridManager(IWorld world) {
        this.world = world;
    }

    /**
     * Create a new grid.
     * @return The created grid.
     */
    public <GRID extends GridNetwork<GRID,TILE>, TILE extends GridNetworkTile<GRID,TILE>> GRID createGrid(Class<GRID> gridClass ,TILE startTile) {
        return createGrid(gridClass,startTile,UUID.randomUUID());
    }

    /**
     * Create a new grid.
     * @return The created grid.
     */
    public <GRID extends GridNetwork<GRID, TILE>, TILE extends GridNetworkTile<GRID, TILE>> GRID createGrid(Class<GRID> gridClass, TILE startTile, UUID gridId) {
        try {
            if(startTile.getWorld() != world) {
                LogManager.getLogger().error("Can't create grid because the startTiles world is not correct.");
                return null;
            }
            GRID grid = gridClass.getDeclaredConstructor(UUID.class, EnergyGridNetworkTile.class).newInstance(gridId,startTile);
            GRID_NETWORK_LISTS.add(grid);
            SEnergyGridCreatedMessage gridCreatedMessage = new SEnergyGridCreatedMessage((EnergyGridNetwork) grid,startTile.getPos());
            BigIndustries.NETWORK_MANAGER.sendToAll(gridCreatedMessage);
            return grid;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes the grid if the given id.
     * @param gridId The id of the grid.
     */
    public void deleteGrid(UUID gridId) {
        if(getGrid(gridId) != null) {
            System.out.println("deleteGrid");
            GridNetwork<?,?> grid = getGrid(gridId);
            grid.deleteGrid();
            GRID_NETWORK_LISTS.remove(grid);
            SEnergyGridDeletedMessage gridDeletedMessage = new SEnergyGridDeletedMessage((EnergyGridNetwork) grid);
            BigIndustries.NETWORK_MANAGER.sendToAll(gridDeletedMessage);
        }
    }

    /**
     * Merges multiple grids together.
     * @param grids The grid to be merged together.
     * @return The merged grid.
     */
    public <GRID extends GridNetwork<GRID, TILE>, TILE extends GridNetworkTile<GRID, TILE>> GRID mergeGrids(Class<GRID> gridClass, TILE startTile, GRID... grids) {
        GRID newGrid = createGrid(gridClass, startTile);
        newGrid.mergeGrids(grids);
        for (GRID grid : grids) {
            deleteGrid(grid.getNetworkId());
        }
        return newGrid;
    }

    /**
     * Splits the grid of the given tile into multiple grids.
     * @param startTile The tile.
     * @return A list of the created grids.
     */
    public <GRID extends GridNetwork<GRID, TILE>, TILE extends GridNetworkTile<GRID, TILE>> List<GRID> splitGrid(Class<GRID> gridClass, TILE startTile) {
        UUID oldGridId = startTile.getGridNetwork().getNetworkId();
        List<TILE> neighbourTiles = Util.getTileNeighboursOfSameType(startTile);
        List<GRID> newGrids = new ArrayList<>();

        neighbourTiles.forEach(neighbourTile -> {
            GRID newGrid = createGrid(gridClass,neighbourTile);
            newGrids.add(newGrid);
        });
        deleteGrid(oldGridId);
        return newGrids;
    }

    /**
     * @param gridId The id of the grid.
     * @return The grid with the given id. Null id no network is found.
     */
    @Nullable
    public GridNetwork<?,?> getGrid(UUID gridId) {
        return GRID_NETWORK_LISTS
                .stream().filter(gridNetwork -> gridNetwork.getNetworkId().equals(gridId)).findFirst().orElse(null);
    }

    public List<GridNetwork<?,?>> getGrids() {
        return GRID_NETWORK_LISTS;
    }

    public List<GridNetwork<?,?>> getGrids(GridType gridType) {
        return null;
    }

}
