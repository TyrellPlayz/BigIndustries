package com.tyrellplayz.big_industries.blockentity;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.multiblock.IMultiblockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MultiblockEntityChild extends BlockEntity implements IMultiblockEntity {

    protected BlockPos parent;
    protected ResourceLocation previousBlock;

    public MultiblockEntityChild( BlockPos pos, BlockState state) {
        super(BIBlockEntities.MULTI_BLOCK_CHILD.get(), pos, state);
    }

    public BlockPos getParent() {
        return parent;
    }

    @Override
    public BlockEntity getParentEntity() {
        return level.getBlockEntity(getParent());
    }

    public void setParent(BlockPos parent) {
        this.parent = parent;
        markUpdated();
    }

    /**
     * @return The registry name of the block that was replaced by the multiblock.
     * Used when the multiblock is destroyed.
     */
    public ResourceLocation getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(ResourceLocation previousBlock) {
        this.previousBlock = previousBlock;
        markUpdated();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        tag.putInt("parent_x", this.parent.getX());
        tag.putInt("parent_y", this.parent.getY());
        tag.putInt("parent_z", this.parent.getZ());
        tag.putString("previous_block",previousBlock.toString());
        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.parent = new BlockPos(tag.getInt("parent_x"),tag.getInt("parent_y"),tag.getInt("parent_z"));
        this.previousBlock = new ResourceLocation(tag.getString("previous_block"));
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }

    private void markUpdated() {
        this.setChanged();
    }

}
