package com.tyrellplayz.big_industries.proxy;

import com.tyrellplayz.big_industries.client.ClientEvents;
import com.tyrellplayz.big_industries.client.screen.CombustionEngineScreen;
import com.tyrellplayz.big_industries.client.screen.CrusherMachineScreen;
import com.tyrellplayz.big_industries.core.BIContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void onCommonSetup(FMLCommonSetupEvent fmlCommonSetupEvent) {
        super.onCommonSetup(fmlCommonSetupEvent);
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent fmlClientSetupEvent) {
        ScreenManager.registerFactory(BIContainers.COMBUSTION_ENGINE, CombustionEngineScreen::new);
        ScreenManager.registerFactory(BIContainers.CRUSHER_MACHINE, CrusherMachineScreen::new);

        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }
}
