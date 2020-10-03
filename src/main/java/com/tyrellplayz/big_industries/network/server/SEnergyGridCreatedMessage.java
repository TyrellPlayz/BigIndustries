package com.tyrellplayz.big_industries.network.server;

import com.tyrellplayz.big_industries.events.WorldEvents;
import com.tyrellplayz.big_industries.grid.ClientGridManager;
import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.zlib.network.message.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Used to send a message to clients about a newly created {@link com.tyrellplayz.big_industries.grid.GridNetwork}
 */
public class SEnergyGridCreatedMessage extends Message<SEnergyGridCreatedMessage> {

    private UUID gridId;
    private BlockPos startPos;

    public SEnergyGridCreatedMessage() {
        super("Energy_Grid_Created");
    }

    public SEnergyGridCreatedMessage(EnergyGridNetwork energyGridNetwork, BlockPos startPos) {
        this();
        this.gridId = energyGridNetwork.getNetworkId();
        this.startPos = startPos;
    }

    @Override
    public void writePacket(PacketBuffer packetBuffer) {
        packetBuffer.writeString(gridId.toString());
        packetBuffer.writeBlockPos(startPos);
    }

    @Override
    public SEnergyGridCreatedMessage readPacket(PacketBuffer packetBuffer) {
        gridId = UUID.fromString(packetBuffer.readString());
        startPos = packetBuffer.readBlockPos();
        return this;
    }

    @Override
    public BiConsumer<SEnergyGridCreatedMessage, Supplier<NetworkEvent.Context>> handlePacket() {
        return (sEnergyGridCreatedMessage, contextSupplier) -> {
            contextSupplier.get().enqueueWork(() -> {
                IWorld world = Minecraft.getInstance().player.getEntityWorld();
                ((ClientGridManager)WorldEvents.worldIGridManagerMap.get(world)).handleCreatedPacket(this);
            });
            contextSupplier.get().setPacketHandled(true);
        };
    }

    public UUID getGridId() {
        return gridId;
    }

    public BlockPos getStartPos() {
        return startPos;
    }
}
