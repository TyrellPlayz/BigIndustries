package com.tyrellplayz.big_industries;

import com.tyrellplayz.big_industries.client.ClientEvents;
import com.tyrellplayz.big_industries.client.renderer.FluidTankRenderer;
import com.tyrellplayz.big_industries.client.screen.BlastFurnaceScreen;
import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.core.BIMenus;
import com.tyrellplayz.zlib.proxy.ModProxy;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements ModProxy {

    @Override
    public void onCommonSetup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    public void onClientSetup(FMLClientSetupEvent fmlClientSetupEvent) {
        MenuScreens.register(BIMenus.BLAST_FURNACE.get(), BlastFurnaceScreen::new);

        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }

    /**
     * Is used to register custom entity renderers
     * @param event
     */
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BIBlockEntities.FLUID_TANK.get(), FluidTankRenderer::new);
    }

}
