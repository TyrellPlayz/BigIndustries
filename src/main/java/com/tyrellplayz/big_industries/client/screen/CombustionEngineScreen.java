package com.tyrellplayz.big_industries.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.inventory.container.CombustionEngineContainer;
import com.tyrellplayz.zlib.client.screen.ContainerScreen;
import com.tyrellplayz.zlib.client.screen.component.BurnComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CombustionEngineScreen extends ContainerScreen<CombustionEngineContainer> {

    private static final ResourceLocation ENGINE_GUI_TEXTURES = new ResourceLocation(BigIndustries.MOD_ID,"textures/gui/container/engine/combustion_engine.png");
    protected static final ResourceLocation COMPONENT_TEXTURES = new ResourceLocation("zlib", "textures/gui/components.png");

    private BurnComponent burnComponent;
    private TankComponent tankComponent;

    public CombustionEngineScreen(CombustionEngineContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title,ENGINE_GUI_TEXTURES);
        this.xSize = 176;
        this.ySize = 178;

        burnComponent = new BurnComponent(87,29).setOnTick(component -> {
            ((BurnComponent)component).setBurnTime(container.getBurnTime());
            ((BurnComponent) component).setCurrentBurnTime(container.getCurrentItemBurnTime());
        });

        tankComponent = new TankComponent(8,8,72,COMPONENT_TEXTURES).setOnTick(component -> {
            ((TankComponent)component).setStored(container.getStoredEnergy());
            ((TankComponent) component).setMaxAmount(container.getMaxEnergy());
        });

        addComponent(burnComponent);
        addComponent(tankComponent);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(matrixStack,partialTicks,mouseX,mouseY);

        if(tankComponent.isMouseInside(mouseX,mouseY)) {
            String text = container.getStoredEnergy()+"/"+container.getMaxEnergy()+" RF";
            renderTooltip(matrixStack,new StringTextComponent(text),mouseX,mouseY);
        }

    }

}
