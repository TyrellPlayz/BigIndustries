package com.tyrellplayz.big_industries.block.entity;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.multiblock.Multiblock;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MultiblockEntity<T extends MultiblockEntity<T,M>, M extends Multiblock> extends BlockEntity {

    protected final MultiblockType<M> multiblockType;
    protected BlockPos parent;
    protected ImmutableList<BlockPos> children;
    protected boolean beingDeconstructed;

    protected ResourceLocation previousBlock;

    public MultiblockEntity(BlockEntityType<?> type, MultiblockType<M> multiblockType, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.multiblockType = multiblockType;
    }

    public BlockPos getParent() {
        return parent;
    }

    public void setParent(BlockPos parent) {
        this.parent = parent;
        markUpdated();
    }

    public boolean isParent() {
        return this.parent.equals(this.worldPosition);
    }

    public ImmutableList<BlockPos> getChildren() {
        return children;
    }

    public void setChildren(ImmutableList<BlockPos> children) {
        this.children = children;
        markUpdated();
    }

    public boolean isBeingDeconstructed() {
        return beingDeconstructed;
    }

    public void setBeingDeconstructed(boolean beingDeconstructed) {
        this.beingDeconstructed = beingDeconstructed;
    }

    public ResourceLocation getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(ResourceLocation previousBlock) {
        this.previousBlock = previousBlock;
        markUpdated();
    }

    public void onConstruct() {

    }

    public void onDeconstruct() {

    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        if(parent != null) {
            tag.putInt("parent_x", this.parent.getX());
            tag.putInt("parent_y", this.parent.getY());
            tag.putInt("parent_z", this.parent.getZ());
        }
        if(isParent()) {
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
        }

        tag.putString("previous_block",previousBlock.toString());
        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.parent = new BlockPos(tag.getInt("parent_x"),tag.getInt("parent_y"),tag.getInt("parent_z"));
        if(isParent()) {
            int childrenSize = tag.getInt("children_size");
            ImmutableList.Builder<BlockPos> listBuilder = ImmutableList.builder();
            tag.getList("children",childrenSize).forEach(tag1 -> {
                CompoundTag childTag = (CompoundTag)tag1;
                listBuilder.add(new BlockPos(childTag.getInt("x"),childTag.getInt("y"),childTag.getInt("z")));
            });
            this.children = listBuilder.build();
        }
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
