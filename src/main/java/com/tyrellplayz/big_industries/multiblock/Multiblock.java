package com.tyrellplayz.big_industries.multiblock;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.util.Util;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;
import org.jline.style.StyleResolver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of blocks that form on giant block. One being the parent and the others children of that parent.
 */
public class Multiblock<T extends Multiblock<T>> {

    private final MultiblockType<T> type;

    private final World world;
    private final BlockPos parent;
    private final List<BlockPos> children;

    public Multiblock(MultiblockType<T> type, World world, BlockPos parent) {
        this.type = type;
        this.world = world;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    /**
     * Gets called when the multiblock is created.
     */
    public void onCreate() {

    }

    /**
     * Gets called when the multiblock is destroyed which is before the parent or a child is removed.
     * @param removedBlockPos The pos of the block that is being removed.
     */
    public void onDestroyed(BlockPos removedBlockPos) {
        if(world.getTileEntity(parent) != null) {
            TileEntity tile = world.getTileEntity(parent);
            if(tile instanceof IInventory) InventoryHelper.dropInventoryItems(getWorld(),removedBlockPos, (IInventory) tile);
        }
    }

    /**
     *
     */
    public boolean matchesTemplate() {
        return true;
    }

    public boolean placeTemplate(Rotation rotation, BlockPos placementPos) {
        Template template = getTemplate();
        if (template == null) {
            BigIndustries.LOGGER.error("Cannot place template "+getType().getRegistryName()+" because it does not exist");
            return false;
        }
        PlacementSettings placementSettings = new PlacementSettings()
                .setMirror(Mirror.NONE)
                .setRotation(rotation);

        return true;
    }

    @Nullable
    public Template getTemplate() {
        if(getWorld() instanceof ServerWorld) return Util.getTemplate((ServerWorld) getWorld(),getType().getRegistryName());
        BigIndustries.LOGGER.error("Cannot get template "+getType().getRegistryName()+" from client world");
        return null;
    }


    public MultiblockType<?> getType() {
        return type;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getParent() {
        return parent;
    }

    public List<BlockPos> getChildren() {
        return children;
    }
}
