package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.big_industries.block.CombustionEngineBlock;
import com.tyrellplayz.big_industries.core.BITiles;
import com.tyrellplayz.big_industries.grid.energy.BasicEnergyStorage;
import com.tyrellplayz.big_industries.grid.energy.EnergyGridNetwork;
import com.tyrellplayz.big_industries.inventory.container.CombustionEngineContainer;
import com.tyrellplayz.big_industries.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CombustionEngineTile extends InventoryTile implements ITickableTileEntity, INamedContainerProvider {

    private static final int FUEL_SLOT = 0;

    public BasicEnergyStorage energyStorage = new BasicEnergyStorage(10000);
    /** The number of ticks that the crusher will keep burning */
    private int burnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the crusher burning for */
    private int currentItemBurnTime;
    protected final IIntArray combustionEngineData = new IIntArray() {
        @Override
        public int get(int index) {
            switch(index) {
                case 0:
                    return CombustionEngineTile.this.burnTime;
                case 1:
                    return CombustionEngineTile.this.currentItemBurnTime;
                case 2:
                    return CombustionEngineTile.this.energyStorage.getEnergyStored();
                case 3:
                    return CombustionEngineTile.this.energyStorage.getMaxEnergyStored();
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CombustionEngineTile.this.burnTime = value;
                    break;
                case 1:
                    CombustionEngineTile.this.currentItemBurnTime = value;
                    break;
                case 2:
                    CombustionEngineTile.this.energyStorage.setEnergy(value);
                    break;
                case 3:
                    break;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public CombustionEngineTile() {
        super(BITiles.COMBUSTION_ENGINE,1);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        ItemStack fuelStack = this.itemStacks.get(FUEL_SLOT);

        // If it is already burning or has a valid fuel item in the fuel slot.
        // then create energy.
        if (this.isBurning() || !fuelStack.isEmpty() && isItemFuel(fuelStack)) {

            // If nothing is burning at the moment then start burning the valid fuel item in the slot.
            if (!this.isBurning()) {
                this.burnTime = ForgeHooks.getBurnTime(fuelStack);
                this.currentItemBurnTime = this.burnTime;
                if (this.isBurning()) {
                    flag1 = true;
                    fuelStack.shrink(1);
                }
            }

            if (this.isBurning()) {
                // Create energy here.
                energyStorage.receiveEnergy(10,false);
            }
        }

        if (flag != this.isBurning()) {
            flag1 = true;

            this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(CombustionEngineBlock.ON, this.isBurning()), 3);
        }

        if (flag1) this.markDirty();

        if(energyStorage.getEnergyStored() > 0) {
            List<EnergyGridNetwork> neighbourGrids = Util.getGridsOfType(EnergyGridNetwork.class,this);
            if(!neighbourGrids.isEmpty()) {
                //FIXME: Support multiple grids
                EnergyGridNetwork gridNetwork = neighbourGrids.get(0);
                int energyTransferred = gridNetwork.receiveEnergy(energyStorage.getEnergyStored(),false);
                energyStorage.extractEnergy(energyTransferred,false);
            }
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        if(stack.hasContainerItem()) return false;
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new CombustionEngineContainer(id,playerInventory,this,combustionEngineData);
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState,compound);
        this.burnTime = compound.getInt("BurnTime");
        this.currentItemBurnTime = ForgeHooks.getBurnTime(this.itemStacks.get(0));
        this.energyStorage.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        this.energyStorage.write(compound);
        return compound;
    }

    private LazyOptional<?> energyHandler = LazyOptional.of(() -> this.energyStorage);
    private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return this.energyHandler.cast();
        }
        if (!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY )
            return this.itemHandler.cast();
        return super.getCapability(cap, side);
    }

}
