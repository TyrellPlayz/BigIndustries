package com.tyrellplayz.big_industries.inventory.container.slot;

import com.tyrellplayz.big_industries.inventory.container.CombustionEngineContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class FuelSlot extends Slot {

    public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

}
