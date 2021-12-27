package com.tyrellplayz.big_industries.proxy;

import com.tyrellplayz.big_industries.client.ClientEvents;
import com.tyrellplayz.big_industries.client.screen.BlastFurnaceScreen;
import com.tyrellplayz.big_industries.core.BIMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void onCommonSetup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    public void onClientSetup(FMLClientSetupEvent fmlClientSetupEvent) {
        MenuScreens.register(BIMenus.BLAST_FURNACE.get(), BlastFurnaceScreen::new);

        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }

}
