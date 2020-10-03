package com.tyrellplayz.big_industries.network.server;

import com.tyrellplayz.big_industries.events.WorldEvents;
import com.tyrellplayz.big_industries.grid.ClientGridManager;
import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.zlib.network.message.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Used to send a message to clients about a deleted {@link com.tyrellplayz.big_industries.grid.GridNetwork}
 */
public class SEnergyGridDeletedMessage extends Message<SEnergyGridDeletedMessage> {

    private UUID gridId;

    public SEnergyGridDeletedMessage() {
        super("Energy_Grid_Deleted");
    }

    public SEnergyGridDeletedMessage(EnergyGridNetwork energyGridNetwork) {
        this();
        this.gridId = energyGridNetwork.getNetworkId();
    }

    @Override
    public void writePacket(PacketBuffer packetBuffer) {
        packetBuffer.writeString(gridId.toString());
    }

    @Override
    public SEnergyGridDeletedMessage readPacket(PacketBuffer packetBuffer) {
        gridId = UUID.fromString(packetBuffer.readString());
        return this;
    }

    @Override
    public BiConsumer<SEnergyGridDeletedMessage, Supplier<NetworkEvent.Context>> handlePacket() {
        return (sEnergyGridDeletedMessage, contextSupplier) -> {
            contextSupplier.get().enqueueWork(() -> {
                IWorld world = Minecraft.getInstance().player.getEntityWorld();
                ((ClientGridManager) WorldEvents.worldIGridManagerMap.get(world)).handleDeletedPacket(this);
            });
            contextSupplier.get().setPacketHandled(true);
        };
    }

    public UUID getGridId() {
        return gridId;
    }
}
