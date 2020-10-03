package com.tyrellplayz.big_industries.grid;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.big_industries.network.server.SEnergyGridCreatedMessage;
import com.tyrellplayz.big_industries.network.server.SEnergyGridDeletedMessage;
import com.tyrellplayz.big_industries.tile.grid.EnergyGridNetworkTile;
import com.tyrellplayz.big_industries.tile.grid.GridNetworkTile;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class ClientGridManager extends GridManager {

    public ClientGridManager(IWorld world) {
        super(world);
    }

    @Override
    public <GRID extends GridNetwork<GRID, TILE>, TILE extends GridNetworkTile<GRID, TILE>> GRID createGrid(Class<GRID> gridClass, TILE startTile, UUID gridId) {
        try {
            if(startTile.getWorld() != world) {
                LogManager.getLogger().error("Can't create grid because the startTiles world is not correct.");
                return null;
            }
            GRID grid = gridClass.getDeclaredConstructor(UUID.class,EnergyGridNetworkTile.class).newInstance(gridId,startTile);
            GRID_NETWORK_LISTS.add(grid);
            return grid;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteGrid(UUID gridId) {
        if(getGrid(gridId) != null) {
            GridNetwork<?,?> grid = getGrid(gridId);
            grid.deleteGrid();
            GRID_NETWORK_LISTS.remove(grid);
        }
    }


    public void handleCreatedPacket(SEnergyGridCreatedMessage gridCreatedMessage) {
        System.out.println("Grid "+gridCreatedMessage.getGridId()+" created on server.");
        World world = Minecraft.getInstance().player.getEntityWorld();
        TileEntity tileEntity = world.getTileEntity(gridCreatedMessage.getStartPos());
        if(tileEntity instanceof GridNetworkTile) {
            //FIXME: Add different types of grids.
            createGrid(EnergyGridNetwork.class,(EnergyGridNetworkTile) tileEntity,gridCreatedMessage.getGridId());
        }
    }

    public void handleDeletedPacket(SEnergyGridDeletedMessage gridDeletedMessage) {
        System.out.println("Grid "+gridDeletedMessage.getGridId()+" deleted on server.");
        deleteGrid(gridDeletedMessage.getGridId());
    }

}
