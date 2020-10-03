package com.tyrellplayz.big_industries.events;

import com.tyrellplayz.big_industries.grid.ClientGridManager;
import com.tyrellplayz.big_industries.grid.GridManager;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

public class WorldEvents {

    public static Map<IWorld,GridManager> worldIGridManagerMap = new HashMap<>();

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if(worldIGridManagerMap.containsKey(event.getWorld())) {
            LogManager.getLogger().error("A grid manager already exists for this world? This should not happen here.");
        }
        if(ServerHelper.isServerWorld(event.getWorld())) {
            // Is a server world.
            worldIGridManagerMap.put(event.getWorld(),new GridManager(event.getWorld()));

        }else {
            // Is a client world.
            worldIGridManagerMap.put(event.getWorld(),new ClientGridManager(event.getWorld()));
        }
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) {

    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        worldIGridManagerMap.remove(event.getWorld());
    }

}
