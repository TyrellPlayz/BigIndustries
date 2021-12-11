package com.tyrellplayz.big_industries.blockentity;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.menu.BlastFurnaceMenu;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class BlastFurnaceEntity extends MultiblockEntity<BlastFurnaceEntity> implements StackedContentsCompatible {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_RESULT = 2;

    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;

    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;

    protected NonNullList<ItemStack> items = NonNullList.withSize(3,ItemStack.EMPTY);
    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int id) {
            return switch (id) {
                case 0 -> BlastFurnaceEntity.this.litTime;
                case 1 -> BlastFurnaceEntity.this.litDuration;
                case 2 -> BlastFurnaceEntity.this.cookingProgress;
                case 3 -> BlastFurnaceEntity.this.cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int id, int value) {
            switch (id) {
                case 0 -> BlastFurnaceEntity.this.litTime = value;
                case 1 -> BlastFurnaceEntity.this.litDuration = value;
                case 2 -> BlastFurnaceEntity.this.cookingProgress = value;
                case 3 -> BlastFurnaceEntity.this.cookingTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    private final RecipeType<? extends AbstractCookingRecipe> recipeType;

    public BlastFurnaceEntity(BlockPos pos, BlockState state) {
        super(BIBlockEntities.BLAST_FURNACE.get(), MultiblockType.BLAST_FURNACE, pos, state);
        this.recipeType = RecipeType.BLASTING;
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    private int getBurnDuration(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, recipeType);
    }

    private boolean isFuel(ItemStack stack) {
        return getBurnDuration(stack) > 0;
    }

    private static int getTotalCookTime(Level level, RecipeType<? extends AbstractCookingRecipe> recipeType, Container container) {
        return level.getRecipeManager().getRecipeFor(recipeType,container,level).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    }

    private boolean canBurn(Recipe<?> recipe, NonNullList<ItemStack> items, int stackSize) {
        if(!items.get(SLOT_INPUT).isEmpty() && recipe != null) {
            ItemStack recipeStack = ((Recipe<Container>)recipe).assemble(this);
            if(recipeStack.isEmpty()) {
                return false;
            }else {
                ItemStack resultStack = items.get(SLOT_RESULT);
                if (resultStack.isEmpty()) {
                    // The result slot is empty.
                    return true;
                }else if(!resultStack.sameItem(recipeStack)) {
                    // The result slot and recipe slot is not the same item.
                    return false;
                }else if(resultStack.getCount() + recipeStack.getCount() <= stackSize && resultStack.getCount() + recipeStack.getCount() <= resultStack.getMaxStackSize()) {
                    return true;
                }else {
                    return recipeStack.getCount() + recipeStack.getCount() <= resultStack.getMaxStackSize();
                }
            }
        }else {
            return false;
        }
    }

    private boolean burn(Recipe<?> recipe, NonNullList<ItemStack> items, int stackSize) {
        if(recipe != null && this.canBurn(recipe,items,stackSize)) {
            ItemStack inputStack = items.get(SLOT_INPUT);
            ItemStack recipeStack = ((Recipe<Container>)recipe).assemble(this);
            ItemStack resultStack = items.get(SLOT_RESULT);

            if(resultStack.isEmpty()) {
                items.set(SLOT_RESULT,recipeStack.copy());
            }else if(resultStack.is(resultStack.getItem())) {
                resultStack.grow(recipeStack.getCount());
            }

            inputStack.shrink(1);
            return true;
        }else {
            return false;
        }
    }

    public static void onServerTick(Level level, BlockPos pos, BlockState state, BlastFurnaceEntity entity) {

    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this .items);
        this.litTime = tag.getInt("BurnTime");
        this.cookingProgress = tag.getInt("CookTime");
        this.cookingTotalTime = tag.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(SLOT_FUEL));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        tag.putInt("BurnTime",this.litTime);
        tag.putInt("CookTIme",this.cookingProgress);
        tag.putInt("CookTimeTotal",this.cookingTotalTime);
        ContainerHelper.saveAllItems(tag,this.items);
        return tag;
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container.blast_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlastFurnaceMenu(containerId,inventory,this,this.data);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if(!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.items,slot,amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items,slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack slotStack = this.items.get(slot);
        boolean flag = !stack.isEmpty() && stack.sameItem(slotStack) && ItemStack.tagMatches(stack,slotStack);
        this.items.set(slot,stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if(slot == SLOT_INPUT && !flag) {
            this.cookingTotalTime = getTotalCookTime(this.level,this.recipeType, this);
            this.cookingProgress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if(this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }else {
            return player.distanceToSqr(
                    this.worldPosition.getX() + 0.5D,
                    this.worldPosition.getY() + 0.5D,
                    this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if(slot == SLOT_RESULT) {
            // Result slot
            return false;
        }else if(slot != SLOT_FUEL) {
            // Input slots
            return true;
        }else {
            // Fuel slot
            return isFuel(stack);
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void fillStackedContents(StackedContents contents) {
        for (ItemStack stack : this.items) {
            contents.accountStack(stack);
        }
    }
}
