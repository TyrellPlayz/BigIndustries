package com.tyrellplayz.big_industries.common.container;

import com.tyrellplayz.big_industries.common.crafting.CokeOvenRecipe;
import com.tyrellplayz.big_industries.core.BIContainers;
import com.tyrellplayz.big_industries.common.container.slot.FuelSlot;
import com.tyrellplayz.big_industries.common.container.slot.OutputSlot;
import com.tyrellplayz.big_industries.tile.CokeOvenTile;
import com.tyrellplayz.zlib.container.ZContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;

import java.util.Optional;
import java.util.function.Supplier;

public class CokeOvenContainer extends ZContainer {

    public final static Properties PROPERTIES = new Properties(BIContainers.COKE_OVEN,3,4);

    public CokeOvenContainer(int id, PlayerInventory playerInventoryIn) {
        super(id, PROPERTIES, playerInventoryIn);
    }

    public CokeOvenContainer(int id, PlayerInventory playerInventory, IInventory containerInventory, IIntArray containerData) {
        super(id, PROPERTIES, playerInventory, containerInventory, containerData);
    }

    @Override
    public void createSlots(PlayerInventory playerInventory, IInventory iInventory) {
        this.addSlot(new Slot(containerInventory, CokeOvenTile.INPUT_SLOT,56,17));
        this.addSlot(new FuelSlot(containerInventory, CokeOvenTile.FUEL_SLOT,56,53));
        this.addSlot(new OutputSlot(playerInventory.player, containerInventory, CokeOvenTile.OUTPUT_SLOT,116,35));
        createPlayerInventory(8,84,playerInventory);
    }

    /**
     * Transfers a itemStack to the containers inventory from the players inventory.
     * @param itemStack The itemStack to be transferred.
     * @param slotIndex The slot index of the item to be transferred.
     * @return
     */
    @Override
    public TransferResponse transferStackToContainer(ItemStack itemStack, int slotIndex) {
        if(isFuel(itemStack)) return new TransferResponse(CokeOvenTile.FUEL_SLOT).elseIfFull(CokeOvenTile.INPUT_SLOT);
        if(hasRecipe(CokeOvenRecipe.TYPE,itemStack)) return new TransferResponse(CokeOvenTile.INPUT_SLOT);
        return null;
    }

    public static boolean isFuel(ItemStack itemStack) {
        return ForgeHooks.getBurnTime(itemStack) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnTime() {
        return this.containerData.get(CokeOvenTile.DATA_BURN_TIME);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return getBurnTime() > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnTimeTotal() {
        return this.containerData.get(CokeOvenTile.DATA_BURN_TIME_TOTAL);
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int i = this.containerData.get(getBurnTimeTotal());
        if (i == 0) i = 200;
        return this.containerData.get(getBurnTime()) * 13 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookTime() {
        return this.containerData.get(CokeOvenTile.DATA_COOK_TIME);
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookTimeTotal() {
        return this.containerData.get(CokeOvenTile.DATA_COOK_TIME_TOTAL);
    }

}
