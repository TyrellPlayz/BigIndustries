package com.tyrellplayz.big_industries.client;

import com.tyrellplayz.big_industries.tile.grid.GridNetworkTile;
import com.tyrellplayz.zlib.util.helpers.MathsHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onDebugOverlay(final RenderGameOverlayEvent.Text event){
        if(Minecraft.getInstance().gameSettings.showDebugInfo){
            World world = Minecraft.getInstance().player.getEntityWorld();
            PlayerEntity player = Minecraft.getInstance().player;

            BlockPos pos = MathsHelper.getBlockPosPlayerIsLookingAt(player);

            if(pos != null){
                TileEntity tileEntity = world.getTileEntity(pos);
                if(tileEntity != null){
                    event.getRight().add("");
                    event.getRight().add("Targeted TileEntity");
                    event.getRight().add(tileEntity.getType().getRegistryName().toString());
                    if(tileEntity instanceof GridNetworkTile) {
                        event.getRight().add("Targeted Network");
                        if(((GridNetworkTile) tileEntity).hasNetwork()) {
                            event.getRight().add("Type: "+((GridNetworkTile) tileEntity).getGridNetwork().getType());
                            event.getRight().add("Id: "+((GridNetworkTile) tileEntity).getGridNetwork().getNetworkId());
                        }else {
                            event.getRight().add("Type: null");
                            event.getRight().add("Id: null");
                        }

                    }
                }
            }

        }

    }

}
