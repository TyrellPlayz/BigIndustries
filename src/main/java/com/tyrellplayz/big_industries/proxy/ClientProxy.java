package com.tyrellplayz.big_industries.proxy;

import com.tyrellplayz.big_industries.client.ClientEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void onCommonSetup(FMLCommonSetupEvent fmlCommonSetupEvent) {

    }

    @Override
    public void onClientSetup(FMLClientSetupEvent fmlClientSetupEvent) {
        //MenuScreens.register(ModContainers.BLAST_FURNACE.get(), BlastFurnaceScreen::new);

        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }
}
