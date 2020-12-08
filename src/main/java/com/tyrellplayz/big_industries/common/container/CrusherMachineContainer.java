package com.tyrellplayz.big_industries.common.container;

import com.tyrellplayz.big_industries.core.BIContainers;
import com.tyrellplayz.big_industries.common.container.slot.OutputSlot;
import com.tyrellplayz.zlib.container.ZContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrusherMachineContainer extends ZContainer {

    public final static Properties PROPERTIES = new Properties(BIContainers.CRUSHER_MACHINE,2,2);

    public CrusherMachineContainer(int id, PlayerInventory playerInventoryIn) {
        super(id, PROPERTIES, playerInventoryIn);
    }

    public CrusherMachineContainer(int id, PlayerInventory playerInventory, IInventory containerInventory, IIntArray containerData) {
        super(id, PROPERTIES, playerInventory, containerInventory, containerData);
    }

    @Override
    public void createSlots(PlayerInventory playerInventory, IInventory iInventory) {
        this.addSlot(new Slot(iInventory, 0, 56, 17));
        this.addSlot(new OutputSlot(playerInventory.player, iInventory, 1, 116, 35));
        createPlayerInventory(8,84,playerInventory);
    }

    @Override
    public TransferResponse transferStackToContainer(ItemStack itemStack, int i) {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public int getStoredEnergy() {
        return this.containerData.get(0);
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxEnergy() {
        return this.containerData.get(1);
    }

}
