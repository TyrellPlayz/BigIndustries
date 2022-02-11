package com.tyrellplayz.big_industries.inventory;

import com.tyrellplayz.big_industries.blockentity.BlastFurnaceEntity;
import com.tyrellplayz.big_industries.core.BIMenus;
import com.tyrellplayz.zlib.inventory.ZContainerMenu;
import com.tyrellplayz.zlib.inventory.slot.FuelSlot;
import com.tyrellplayz.zlib.inventory.slot.OutputSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;

public class BlastFurnaceMenu extends ZContainerMenu {

    private final RecipeType<? extends AbstractCookingRecipe> recipeType;

    public BlastFurnaceMenu(int containerId, Inventory inventory) {
        this(containerId,inventory,new SimpleContainer(3),new SimpleContainerData(4));
    }

    public BlastFurnaceMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(BIMenus.BLAST_FURNACE.get(), containerId,inventory,container,data);
        checkContainerSize(container,3);
        checkContainerDataCount(data,4);
        this.recipeType = RecipeType.BLASTING;
    }

    @Override
    public void createSlots(Inventory inventory, Container container) {
        this.addSlot(new Slot(container, BlastFurnaceEntity.SLOT_INPUT,56,17));
        this.addSlot(new FuelSlot(container,recipeType,FuelSlot.FuelType.BOTH, BlastFurnaceEntity.SLOT_FUEL,56,53));
        this.addSlot(new OutputSlot(inventory.player,container, BlastFurnaceEntity.SLOT_RESULT,116,35));
        createPlayerSlots(8,84,inventory);
    }

    @Override
    public MoveResponse quickMoveStackToContainer(ItemStack stack, int slotIndex) {
        if(canSmelt(stack)) return new MoveResponse(0);
        if(isFuel(stack,recipeType)) return new MoveResponse(1);
        return null;
    }

    protected boolean canSmelt(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)this.recipeType, new SimpleContainer(stack), this.level).isPresent();
    }

    public static boolean isFuel(ItemStack itemStack, RecipeType<?> type) {
        return ForgeHooks.getBurnTime(itemStack,type) > 0;
    }

}
