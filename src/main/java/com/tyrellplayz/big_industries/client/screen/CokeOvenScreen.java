package com.tyrellplayz.big_industries.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.container.CokeOvenContainer;
import com.tyrellplayz.zlib.client.screen.ContainerScreen;
import com.tyrellplayz.zlib.client.screen.component.BurnComponent;
import com.tyrellplayz.zlib.client.screen.component.ProgressComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;

public class CokeOvenScreen extends ContainerScreen<CokeOvenContainer> {

    private static final ResourceLocation OVEN_GUI_TEXTURES = new ResourceLocation(BigIndustries.MOD_ID,"textures/gui/container/coke_oven.png");
    protected static final ResourceLocation COMPONENT_TEXTURES = new ResourceLocation("zlib", "textures/gui/components.png");

    private final BurnComponent burnComponent;
    private final ProgressComponent progressComponent;

    public CokeOvenScreen(CokeOvenContainer screenContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(screenContainer, playerInventory, title, OVEN_GUI_TEXTURES);

        burnComponent = new BurnComponent(57,36).setOnTick(component -> {
            ((BurnComponent)component).setBurnTime(container.getBurnTime());
            ((BurnComponent) component).setCurrentBurnTime(container.getBurnTimeTotal());
        });

        progressComponent = new ProgressComponent(79,35).setOnTick(component -> {
            ((ProgressComponent)component).setCookTime(container.getCookTime());
            ((ProgressComponent) component).setCookTimeTotal(container.getCookTimeTotal());
        });

        addComponent(burnComponent);
        addComponent(progressComponent);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        String text = this.title.getString();
        this.font.drawString(matrixStack, text, (float)(this.xSize / 2 - this.font.getStringWidth(text) / 2), 6.0F, Color.WHITE.getRGB());
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float)(this.ySize - 96 + 2), Color.WHITE.getRGB());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(matrixStack,partialTicks,mouseX,mouseY);

        if(burnComponent.isMouseInside(mouseX,mouseY)) {
            String text = container.getBurnTime()+"/"+container.getBurnTimeTotal();
            renderTooltip(matrixStack,new StringTextComponent(text),mouseX,mouseY);
        }

        if(progressComponent.isMouseInside(mouseX,mouseY)) {
            String text = container.getCookTime()+"/"+container.getCookTimeTotal();
            renderTooltip(matrixStack,new StringTextComponent(text),mouseX,mouseY);
        }

    }

}
