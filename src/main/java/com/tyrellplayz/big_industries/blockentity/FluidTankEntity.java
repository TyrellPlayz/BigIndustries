package com.tyrellplayz.big_industries.blockentity;

import com.tyrellplayz.big_industries.Port;
import com.tyrellplayz.big_industries.block.FluidTankBlock;
import com.tyrellplayz.big_industries.core.BIBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidTankEntity extends SyncBlockEntity implements ICapabilityProvider {

    private final FluidTank tank = new FluidTank(10000) {
        @Override
        protected void onContentsChanged() {
            markUpdated();
        }
    };

    private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> tank);

    public FluidTankEntity(BlockPos pos, BlockState state) {
        super(BIBlockEntities.FLUID_TANK.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        tank.readFromNBT(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tank.writeToNBT(tag);
    }

    public FluidTank getTank() {
        return this.tank;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            if(side == null || sideEnabled(side)){
                return holder.cast();
            }
        return super.getCapability(cap, side);
    }

    boolean sideEnabled(Direction side) {
        return level.getBlockState(getBlockPos()).getValue(FluidTankBlock.portFromDirection(side)) != Port.NONE;
    }

}
