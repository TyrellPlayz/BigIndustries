package com.tyrellplayz.big_industries.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class SolidFuelSlot extends FuelSlot {

    public SolidFuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

}
