package com.tyrellplayz.big_industries.tile.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tyrellplayz.big_industries.tile.WaterwheelTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaterwheelTileRenderer extends TileEntityRenderer<WaterwheelTile> {

    public WaterwheelTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }


    @Override
    public void render(WaterwheelTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        //BlockState state = tileEntityIn.getBlockState();

        //BlockRendererDispatcher renderer = Minecraft.getInstance().getBlockRendererDispatcher();

        //matrixStackIn.push();
        //matrixStackIn.translate(0.5D, 0.5D, 0.5D);
        //matrixStackIn.rotate(new Quaternion(-90, 0, 1, 0));

        //renderer.renderBlock(state,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn);

        //matrixStackIn.pop();
    }
}
