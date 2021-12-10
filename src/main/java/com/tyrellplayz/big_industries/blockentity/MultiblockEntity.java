package com.tyrellplayz.big_industries.blockentity;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.multiblock.IMultiblockEntity;
import com.tyrellplayz.big_industries.multiblock.Multiblock;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class MultiblockEntity<T extends MultiblockEntity<T,M>, M extends Multiblock> extends BaseContainerBlockEntity implements IMultiblockEntity {

    protected final MultiblockType<M> multiblockType;
    protected ImmutableList<BlockPos> children;
    protected boolean beingDeconstructed;

    protected ResourceLocation previousBlock;

    public MultiblockEntity(BlockEntityType<?> type, MultiblockType<M> multiblockType, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.multiblockType = multiblockType;
    }

    @Override
    public BlockPos getParent() {
        return this.worldPosition;
    }

    @Override
    public BlockEntity getParentEntity() {
        return this;
    }

    /**
     * @return A list of all children of parent BlockEntity. If BlockEntity is a child null is returned.
     */
    @Nullable
    public ImmutableList<BlockPos> getChildren() {
        return children;
    }

    /**
     * Sets the child of the parent BlockEntity. If BlockEntity is not parent, nothing happens.
     * @param children
     */
    public void setChildren(ImmutableList<BlockPos> children) {
        this.children = children;
        markUpdated();
    }

    /**
     * @return If the Multiblock is being deconstructed
     */
    public boolean isBeingDeconstructed() {
        return beingDeconstructed;
    }

    public void setBeingDeconstructed(boolean beingDeconstructed) {
        this.beingDeconstructed = beingDeconstructed;
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
        tag.putInt("children_size",this.children.size());
        ListTag childrenTag = new ListTag();
        children.forEach(pos -> {
            CompoundTag childTag = new CompoundTag();
            childTag.putInt("x", pos.getX());
            childTag.putInt("y", pos.getY());
            childTag.putInt("z", pos.getZ());
            childrenTag.add(childTag);
        });
        tag.put("children",childrenTag);

        tag.putString("previous_block",previousBlock.toString());
        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        int childrenSize = tag.getInt("children_size");
        ImmutableList.Builder<BlockPos> listBuilder = ImmutableList.builder();
        tag.getList("children",childrenSize).forEach(tag1 -> {
            CompoundTag childTag = (CompoundTag)tag1;
            listBuilder.add(new BlockPos(childTag.getInt("x"),childTag.getInt("y"),childTag.getInt("z")));
        });
        this.children = listBuilder.build();
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

    public static void onServerTick(Level level, BlockPos pos, BlockState state, MultiblockEntity entity) {

    }

}
