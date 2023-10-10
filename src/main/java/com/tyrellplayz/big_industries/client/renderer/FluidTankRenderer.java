package com.tyrellplayz.big_industries.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.tyrellplayz.big_industries.blockentity.FluidTankEntity;
import com.tyrellplayz.zlib.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

@OnlyIn(Dist.CLIENT)
public class FluidTankRenderer implements BlockEntityRenderer<FluidTankEntity> {

    public FluidTankRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(FluidTankEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource source, int combinedLight, int combinedOverlay) {
        stack.pushPose();
        this.drawFluidInTank(blockEntity.getTank(),blockEntity.getLevel(),blockEntity.getBlockPos(),stack,source,
                2F * 0.0625F, 2F * 0.0625F, 2F * 0.0625F, 12F * 0.0625F, 12F * 0.0625F, 12F * 0.0625F,combinedLight);
        stack.popPose();
    }


    private void drawFluidInTank(IFluidHandler handler, Level level, BlockPos pos, PoseStack poseStack, MultiBufferSource source, float x, float y, float z, float width, float height, float depth, int light) {
        FluidStack fluidStack = handler.getFluidInTank(0);
        Fluid fluid = fluidStack.getFluid();
        if(fluid == Fluids.EMPTY)
            return;

        IClientFluidTypeExtensions fluidType = IClientFluidTypeExtensions.of(fluid);
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidType.getStillTexture(fluid.defaultFluidState(), level, pos));
        float minU = sprite.getU0();
        float maxU = Math.min(minU + (sprite.getU1() - minU) * depth, sprite.getU1());
        float minV = sprite.getV0();
        float maxV = Math.min(minV + (sprite.getV1() - minV) * width, sprite.getV1());
        int waterColor = fluidType.getTintColor(fluid.defaultFluidState(), level, pos);
        float red = (float) (waterColor >> 16 & 255) / 255.0F;
        float green = (float) (waterColor >> 8 & 255) / 255.0F;
        float blue = (float) (waterColor & 255) / 255.0F;

        height *= ((double) handler.getFluidInTank(0).getAmount() / (double) handler.getTankCapacity(0));

        VertexConsumer consumer = source.getBuffer(RenderType.translucent());
        Matrix4f matrix = poseStack.last().pose();

        // TOP
        consumer.vertex(matrix, x, y + height, z).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        consumer.vertex(matrix, x, y + height, z + depth).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        consumer.vertex(matrix, x + width, y + height, z + depth).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        consumer.vertex(matrix, x + width, y + height, z).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();

        consumer.vertex(matrix, x, y, z).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(0, 0, -1).endVertex();
        consumer.vertex(matrix, x, y+height, z).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(0, 0, -1).endVertex();
        consumer.vertex(matrix, 1 - x, y+height, z).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(0, 0, -1).endVertex();
        consumer.vertex(matrix, 1 - x, y, z).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(0, 0, -1).endVertex();
        // max z
        consumer.vertex(matrix, x, y, z+depth).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(0, 0, 1).endVertex();
        consumer.vertex(matrix, 1 - x, y, z+depth).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(0, 0, 1).endVertex();
        consumer.vertex(matrix, 1 - x, y+height, z+depth).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(0, 0, 1).endVertex();
        consumer.vertex(matrix, x, y+height, z+depth).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(0, 0, 1).endVertex();
        // min x
        consumer.vertex(matrix, x, y+height, z+depth).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(-1, 0, 0).endVertex();
        consumer.vertex(matrix, x, y+height, z).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(-1, 0, 0).endVertex();
        consumer.vertex(matrix, x, y, z).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(-1, 0, 0).endVertex();
        consumer.vertex(matrix, x, y, z+depth).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(-1, 0, 0).endVertex();
        // max x
        consumer.vertex(matrix, x+width, y+height, z+depth).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(1, 0, 0).endVertex();
        consumer.vertex(matrix, x+width, y, z+depth).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(1, 0, 0).endVertex();
        consumer.vertex(matrix, x+width, y, z).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(1, 0, 0).endVertex();
        consumer.vertex(matrix, x+width, y+height, z).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(1, 0, 0).endVertex();

    }

}
