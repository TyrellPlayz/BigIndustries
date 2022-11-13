package com.tyrellplayz.big_industries.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.tyrellplayz.big_industries.block.FluidPipeBlock;
import com.tyrellplayz.big_industries.multiblock.IMultiblockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.event.DrawSelectionEvent;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    /*
    @SubscribeEvent
    public static void onRenderOutline(final DrawSelectionEvent.HighlightBlock event) {
        Minecraft minecraft = Minecraft.getInstance();
        BlockPos pos = event.getTarget().getBlockPos();
        if (minecraft.level.getBlockState(pos).getBlock() instanceof FluidPipeBlock block) {
            event.setCanceled(true);

            BlockState state = minecraft.level.getBlockState(pos);
            VoxelShape[] collisionShapes = block.getCollisionShapes(minecraft.level,state,pos);

            Camera camera = minecraft.gameRenderer.getMainCamera();
            double camX = camera.getPosition().x;
            double camY = camera.getPosition().y;
            double camZ = camera.getPosition().z;

            VertexConsumer builder = event.getMultiBufferSource().getBuffer(RenderType.LINES);
            for (VoxelShape collisionShape : collisionShapes) {
                renderHitOutline(event.getPoseStack(),builder,collisionShape,camX,camY,camZ,pos,state);
            }
        }
    }


     */
    private static void renderHitOutline(PoseStack stack, VertexConsumer consumer, VoxelShape shape, double camX, double camY, double camZ, BlockPos blockPos, BlockState state) {
        LevelRenderer.renderShape(stack, consumer, shape, (double)blockPos.getX() - camX, (double)blockPos.getY() - camY, (double)blockPos.getZ() - camZ, 0.0F, 0.0F, 0.0F, 0.4F);
    }

    // FIXME: Update to 1.19
    /*
    @SubscribeEvent
    public static void onDebugOverlay(final RenderGameOverlayEvent.Text event){
        Minecraft minecraft = Minecraft.getInstance();
        if(minecraft.options.renderDebug){
            Entity entity = minecraft.getCameraEntity();

            HitResult block = entity.pick(20.0D, 0.0F, false);

            if(block.getType() == HitResult.Type.BLOCK) {
                BlockPos blockpos = ((BlockHitResult)block).getBlockPos();
                BlockEntity blockEntity = minecraft.level.getBlockEntity(blockpos);
                if(blockEntity != null) {
                    event.getRight().add("");
                    event.getRight().add(ChatFormatting.UNDERLINE + "Targeted Block Entity: " + blockpos.getX() + ", " + blockpos.getY() + ", " + blockpos.getZ());
                    event.getRight().add(String.valueOf(blockEntity.getType().getRegistryName()));
                    if(blockEntity instanceof IMultiblockEntity mbEntity) {
                        BlockPos parentPos = mbEntity.getParent();
                        if(parentPos == null) parentPos = new BlockPos(0,0,0);
                        event.getRight().add("MB Parent: " + parentPos.getX() + ", " + parentPos.getY() + ", " + parentPos.getZ());
                    }
                }
            }

        }

    }

     */

}
