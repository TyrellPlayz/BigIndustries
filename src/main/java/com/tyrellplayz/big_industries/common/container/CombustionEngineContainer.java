package com.tyrellplayz.big_industries.common.container;

import com.tyrellplayz.big_industries.core.BIContainers;
import com.tyrellplayz.big_industries.common.container.slot.SolidFuelSlot;
import com.tyrellplayz.zlib.container.ZContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;

public class CombustionEngineContainer extends ZContainer {

    public final static Properties PROPERTIES = new Properties(BIContainers.COMBUSTION_ENGINE,1,4);

    public final static int FUEL_SLOT = 0;

    public CombustionEngineContainer(int id, PlayerInventory playerInventoryIn) {
        super(id, PROPERTIES, playerInventoryIn);
    }

    public CombustionEngineContainer(int id, PlayerInventory playerInventory, IInventory containerInventory, IIntArray containerData) {
        super(id, PROPERTIES, playerInventory, containerInventory, containerData);
    }

    @Override
    public void createSlots(PlayerInventory playerInventory, IInventory containerInventory) {
        this.addSlot(new SolidFuelSlot(containerInventory, FUEL_SLOT, 87, 46));
        createPlayerInventory(8,96,playerInventory);
    }

    /**
     * Transfers a itemStack to the containers inventory from the players inventory.
     * @param itemStack The itemStack to be transferred.
     * @param slotIndex The slot index of the item to be transferred.
     * @return
     */
    @Override
    public ZContainer.TransferResponse transferStackToContainer(ItemStack itemStack, int slotIndex) {
        if(isFuel(itemStack)) return new TransferResponse(0,1);
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnTime() {
        return this.containerData.get(0);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.containerData.get(0) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int i = this.containerData.get(1);
        if (i == 0) i = 200;
        return this.containerData.get(0) * 13 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCurrentItemBurnTime() {
        return this.containerData.get(1);
    }

    @OnlyIn(Dist.CLIENT)
    public int getStoredEnergy() {
        return this.containerData.get(2);
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxEnergy() {
        return this.containerData.get(3);
    }

    public static boolean isFuel(ItemStack itemStack) {
        return ForgeHooks.getBurnTime(itemStack) > 0;
    }

}
