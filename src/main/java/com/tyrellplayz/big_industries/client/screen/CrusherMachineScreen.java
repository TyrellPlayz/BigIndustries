package com.tyrellplayz.big_industries.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.inventory.container.CrusherMachineContainer;
import com.tyrellplayz.zlib.client.screen.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CrusherMachineScreen extends ContainerScreen<CrusherMachineContainer> {

    private static final ResourceLocation ENGINE_GUI_TEXTURES = new ResourceLocation(BigIndustries.MOD_ID,"textures/gui/container/combustion_engine.png");
    protected static final ResourceLocation COMPONENT_TEXTURES = new ResourceLocation("zlib", "textures/gui/components.png");

    private TankComponent tankComponent;

    public CrusherMachineScreen(CrusherMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, ENGINE_GUI_TEXTURES);
        this.xSize = 176;
        this.ySize = 166;

        tankComponent = (TankComponent) new TankComponent(8,8,61,COMPONENT_TEXTURES).setOnTick(component -> {
            ((TankComponent)component).setStored(container.getStoredEnergy());
            ((TankComponent) component).setMaxAmount(container.getMaxEnergy());
        });

        components.add(tankComponent);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack,partialTicks,mouseX,mouseY);

        if(tankComponent.isMouseInside(mouseX,mouseY)) {
            String text = container.getStoredEnergy()+"/"+container.getMaxEnergy()+" RF";
            renderTooltip(stack,new StringTextComponent(text),mouseX,mouseY);
        }

    }

}
