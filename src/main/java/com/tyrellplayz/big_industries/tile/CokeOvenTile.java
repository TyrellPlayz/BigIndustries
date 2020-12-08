package com.tyrellplayz.big_industries.tile;

import com.tyrellplayz.big_industries.common.container.CokeOvenContainer;
import com.tyrellplayz.big_industries.common.crafting.BICookingRecipe;
import com.tyrellplayz.big_industries.common.crafting.CokeOvenRecipe;
import com.tyrellplayz.big_industries.core.BITiles;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class CokeOvenTile extends InventoryTile implements ITickableTileEntity, INamedContainerProvider {

    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public static final int DATA_BURN_TIME = 0;
    public static final int DATA_BURN_TIME_TOTAL = 1;
    public static final int DATA_COOK_TIME = 2;
    public static final int DATA_COOK_TIME_TOTAL = 3;

    /** The number of ticks that the oven will keep burning for */
    private int burnTime;
    /** The total ticks that the oven will keeping burning for */
    private int burnTimeTotal;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the oven burning for */
    private int cookTime;
    /** The total ticks the currently-burning item needs to cook for */
    private int cookTimeTotal;

    protected final IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case DATA_BURN_TIME:
                    return CokeOvenTile.this.burnTime;
                case DATA_BURN_TIME_TOTAL:
                    return CokeOvenTile.this.burnTimeTotal;
                case DATA_COOK_TIME:
                    return CokeOvenTile.this.cookTime;
                case DATA_COOK_TIME_TOTAL:
                    return CokeOvenTile.this.cookTimeTotal;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case DATA_BURN_TIME:
                    CokeOvenTile.this.burnTime = value;
                    break;
                case DATA_BURN_TIME_TOTAL:
                    CokeOvenTile.this.burnTimeTotal = value;
                    break;
                case DATA_COOK_TIME:
                    CokeOvenTile.this.cookTime = value;
                    break;
                case DATA_COOK_TIME_TOTAL:
                    CokeOvenTile.this.cookTimeTotal = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
    protected final IRecipeType<? extends BICookingRecipe> recipeType;

    public CokeOvenTile() {
        super(BITiles.COKE_OVEN, 3);
        recipeType = CokeOvenRecipe.TYPE;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new CokeOvenContainer(id,playerInventory,this,data);
    }

    @Override
    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.itemStacks.get(1);
            if (this.isBurning() || !itemstack.isEmpty() && !this.itemStacks.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = this.getBurnTime(itemstack);
                    this.burnTimeTotal = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem())
                            this.itemStacks.set(1, itemstack.getContainerItem());
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.itemStacks.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTime();
                        this.smelt(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (!this.itemStacks.get(INPUT_SLOT).isEmpty() && recipeIn != null) {
            ItemStack recipeOutput = recipeIn.getRecipeOutput();
            if (recipeOutput.isEmpty()) {
                return false;
            } else {
                ItemStack outStack = this.itemStacks.get(OUTPUT_SLOT);
                if (outStack.isEmpty()) return true;
                else if (!outStack.isItemEqual(recipeOutput)) return false;
                else if (outStack.getCount() + recipeOutput.getCount() <= this.getInventoryStackLimit() && outStack.getCount() + recipeOutput.getCount() <= outStack.getMaxStackSize()) return true;
                else return outStack.getCount() + recipeOutput.getCount() <= recipeOutput.getMaxStackSize();
            }
        } else {
            return false;
        }
    }

    private void smelt(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack inputStack = this.itemStacks.get(INPUT_SLOT);
            ItemStack recipeOutput = recipe.getRecipeOutput();
            ItemStack outputStack = this.itemStacks.get(OUTPUT_SLOT);
            if (outputStack.isEmpty()) {
                this.itemStacks.set(OUTPUT_SLOT, recipeOutput.copy());
            } else if (outputStack.getItem() == recipeOutput.getItem()) {
                outputStack.grow(recipeOutput.getCount());
            }

            if (!this.world.isRemote) this.setRecipeUsed(recipe);
            inputStack.shrink(1);
        }
    }

    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipes.addTo(resourcelocation, 1);
        }
    }

    protected int getCookTime() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(BICookingRecipe::getCookTime).orElse(200);
    }

    public boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    protected int getBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        super.setInventorySlotContents(index, stack);
        if (index == INPUT_SLOT) {
            this.cookTimeTotal = this.getCookTime();
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState, compound);
        this.burnTime = compound.getInt("BurnTime");
        this.burnTimeTotal = compound.getInt("BurnTimeTotal");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime",burnTime);
        compound.putInt("BurnTimeTotal",burnTimeTotal);
        compound.putInt("CookTime",cookTime);
        compound.putInt("CookTimeTotal",cookTimeTotal);
        return compound;
    }

    private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return this.itemHandler.cast();
        return super.getCapability(cap, side);
    }
}
