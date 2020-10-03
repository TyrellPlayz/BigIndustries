package com.tyrellplayz.big_industries.util;

import com.tyrellplayz.big_industries.grid.GridNetwork;
import com.tyrellplayz.big_industries.tile.grid.GridNetworkTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Util {

    private Util() {}

    public static <T extends TileEntity> List<T> getTileNeighboursOfSameType(T startTile) {
        return (List<T>) getTileNeighboursOfSameType(startTile,startTile.getClass());
    }

    public static <T extends TileEntity> List<T> getTileNeighboursOfSameType(TileEntity startTile,Class<T> type) {
        World world = startTile.getWorld();
        Objects.requireNonNull(world);
        List<T> tiles = new ArrayList<>();

        BlockPos[] posArray = getNeighbourBlockPositions(startTile.getPos());
        for (BlockPos blockPos : posArray) {
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if(tileEntity != null && tileEntity.getClass().equals(type)) {
                tiles.add((T) tileEntity);
            }
        }
        return tiles;
    }

    public static BlockPos[] getNeighbourBlockPositions(BlockPos startPos) {
        return new BlockPos[]{startPos.north(),startPos.east(),startPos.south(),startPos.west(),startPos.down(),startPos.up()};
    }

    public static <GRID extends GridNetwork<GRID,TILE>, TILE extends GridNetworkTile<GRID,TILE>> List<GRID> getGridsOfType(Class<GRID> networkClass, TileEntity startTile) {
        List<GRID> grids = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            GRID grid = getGridFromSide(startTile,direction);
            if(networkClass.isInstance(grid)) grids.add(grid);
        }
        return grids;
    }

    /**
     * Gets the grid next to the given {@link TileEntity} on the given {@link Direction}.
     * @param startTile
     * @param side
     * @return The grid or null if there is none.
     */
    public static <GRID extends GridNetwork<GRID,TILE>, TILE extends GridNetworkTile<GRID,TILE>> GRID getGridFromSide(TileEntity startTile, Direction side) {
        World world = startTile.getWorld();
        TileEntity neighbourTile = world.getTileEntity(startTile.getPos().offset(side));
        if(neighbourTile instanceof GridNetworkTile && ((TILE) neighbourTile).hasNetwork()) {
            return ((TILE) neighbourTile).getGridNetwork();
        }
        return null;
    }

}
