package com.tyrellplayz.big_industries.blockentity;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidPipeEntity extends SyncBlockEntity implements IFluidHandler {

    private final FluidTank tank = new FluidTank(1000);
    protected boolean[] disabledSides = new boolean[Direction.values().length];

    public FluidPipeEntity(BlockPos pos, BlockState state) {
        super(BIBlockEntities.FLUID_PIPE.get(), pos, state);
    }

    @Override
    public int getTanks() {
        return tank.getTanks();
    }

    @NotNull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return this.tank.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return this.tank.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return this.tank.isFluidValid(stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return this.tank.fill(resource,action);
    }

    @NotNull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return this.tank.drain(resource,action);
    }

    @NotNull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return this.tank.drain(maxDrain,action);
    }

    public boolean[] getDisabledSides() {
        return disabledSides;
    }

    public void setSideState(Direction side, boolean state) {
        this.disabledSides[side.get3DDataValue()] = state;
        // Sync to client
    }

    public boolean isSideDisabled(Direction side) {
        return this.disabledSides[side.get3DDataValue()];
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {

            return LazyOptional.empty();
        }
        return super.getCapability(cap, side);
    }
}
