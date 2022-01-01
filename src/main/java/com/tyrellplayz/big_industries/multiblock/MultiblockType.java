package com.tyrellplayz.big_industries.multiblock;

import com.google.common.collect.ImmutableList;
import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.block.multiblock.MultiblockBlock;
import com.tyrellplayz.big_industries.blockentity.MultiblockEntity;
import com.tyrellplayz.big_industries.blockentity.MultiblockEntityChild;
import com.tyrellplayz.big_industries.core.BIBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import tyrellplayz.zlib.util.StructureUtil;
import tyrellplayz.zlib.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MultiblockType extends ForgeRegistryEntry<MultiblockType> {

    protected static final List<MultiblockType> MULTIBLOCK_TYPES = new ArrayList<>();

    public static final MultiblockType BLAST_FURNACE = register("blast_furnace", BIBlocks.BLAST_FURNACE);

    private static MultiblockType register(String name, Supplier<Block> blockSupplier) {
        MultiblockType type = new MultiblockType(blockSupplier);
        type.setRegistryName(modLoc(name));
        MULTIBLOCK_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerMB(final RegistryEvent.Register<MultiblockType> event) {
        MULTIBLOCK_TYPES.forEach(multiblockType -> event.getRegistry().register(multiblockType));
    }

    private final Supplier<Block> blockSupplier;

    public MultiblockType(Supplier<Block> blockSupplier) {
        this.blockSupplier = blockSupplier;
    }

    public Block getBlock() {
        return blockSupplier.get();
    }

    public void construct(ServerLevel level, BlockPos parentPos, Player player) {
        StructureTemplate template = StructureUtil.getStructureTemplate(level,getRegistryName());
        if(template == null) return;
        List<BlockPos> posList = StructureUtil.getBlockLocations(parentPos,template,new StructurePlaceSettings().setRotation(Util.getRotation(player.getDirection().getOpposite())),true);
        ImmutableList.Builder<BlockPos> childBuilder = ImmutableList.builder();

        // Go through each multiblock location.
        for (BlockPos multiblockPos : posList) {
            Block previousBlock = level.getBlockState(multiblockPos).getBlock();
            // Change the base block into the multiblock
            if(multiblockPos.equals(parentPos)) {
                // Is parent block
                level.setBlockAndUpdate(multiblockPos,getBlock().defaultBlockState().setValue(MultiblockBlock.FACING,player.getDirection().getOpposite()).setValue(MultiblockBlock.PARENT,true));
                if(!(level.getBlockEntity(parentPos) instanceof MultiblockEntity<?> parentEntity)) {
                    BigIndustries.getLogger().error("Multiblock parent is not a Multiblock block entity.");
                    return;
                }
                parentEntity.setPreviousBlock(previousBlock.getRegistryName());
            }else {
                // Is child block
                level.setBlockAndUpdate(multiblockPos,getBlock().defaultBlockState().setValue(MultiblockBlock.FACING,player.getDirection().getOpposite()).setValue(MultiblockBlock.PARENT,false));
                // Add child to list of children.
                childBuilder.add(multiblockPos);
                if(!(level.getBlockEntity(multiblockPos) instanceof MultiblockEntityChild childEntity)) {
                    BigIndustries.getLogger().error("Multiblock child is not a Multiblock block entity.");
                    return;
                }
                // Tell the child who its parent is.
                childEntity.setParent(parentPos);
                childEntity.setPreviousBlock(previousBlock.getRegistryName());
            }
        }
        if(!(level.getBlockEntity(parentPos) instanceof MultiblockEntity<?> parentEntity)) {
            BigIndustries.getLogger().error("Multiblock parent is not a Multiblock block entity.");
            return;
        }
        // Tell the parent who its children is.
        parentEntity.setChildren(childBuilder.build());
    }

    public boolean deconstruct(ServerLevel level, BlockPos removedPos, BlockPos parentPos) {
        if(!(level.getBlockEntity(parentPos) instanceof MultiblockEntity<?> parentEntity)) {
            BigIndustries.getLogger().error("Multiblock parent is not a multiblock block. Some how??");
            return false;
        }
        if(parentEntity.isBeingDeconstructed()) {
            return true;
        }
        parentEntity.setBeingDeconstructed(true);
        parentEntity.getChildren().forEach(childPos -> {
            // If the child block was the removed block there is no need to replace it.
            if(!childPos.equals(removedPos)) {
                if(level.getBlockEntity(childPos) instanceof MultiblockEntityChild childEntity) {
                    level.setBlockAndUpdate(childPos,Util.getBlock(childEntity.getPreviousBlock()).defaultBlockState());
                }else {
                    BigIndustries.getLogger().error("Multiblock child is not a multiblock block. Some how??");
                }
            }
        });

        if(removedPos != parentPos) {
            level.setBlockAndUpdate(parentPos,Util.getBlock(parentEntity.getPreviousBlock()).defaultBlockState());
        }

        return true;
    }

    /*

    public boolean deconstruct(ServerLevel level, BlockPos removedPos, BlockPos parentPos) {
        if(!(level.getBlockEntity(parentPos) instanceof MultiblockEntity<?,?> parentEntity)) {
            BigIndustries.getLogger().error("Multiblock parent is not a multiblock block. Some how??");
            return false;
        }
        if(parentEntity.isBeingDeconstructed()) {
            BigIndustries.getLogger().info("Multiblock is already being deconstructed");
            return false;
        }
        parentEntity.setBeingDeconstructed(true);

        parentEntity.getChildren().forEach(childPos -> {
            // If the child block was the removed block there is no need to replace it.
            if(!childPos.equals(removedPos)) {
                if(level.getBlockEntity(childPos) instanceof MultiblockEntity<?,?> childEntity) {
                    level.setBlockAndUpdate(childPos,Util.getBlock(childEntity.getPreviousBlock()).defaultBlockState());
                }else {
                    BigIndustries.getLogger().error("Multiblock child is not a multiblock block. Some how??");
                }
            }
        });

        if(removedPos != parentPos) {
            level.setBlockAndUpdate(parentPos,Util.getBlock(parentEntity.getPreviousBlock()).defaultBlockState());
        }

        return true;
    }*/

    /*
     * Deconstructs the multiblock.
     * @param level The world the multiblock is located in.
     * @param removedPos The block that was removed.
     * @param parentPos The multiblock parent location.

    // Containers.dropContents(level,pos,blastFurnaceEntity);
    public boolean deconstruct(ServerLevel level, BlockPos removedPos, BlockPos parentPos) {
        StructureTemplate template = StructureUtil.getStructureTemplate(level,getRegistryName());
        if(template == null) return false;
        if(parentPos == null) {
            BigIndustries.getLogger().error("Multiblock parent is null.");
            return false;
        }
        BlockState parentState = level.getBlockState(parentPos);
        if(!(parentState.getBlock() instanceof MBBlock)) {
            //BigIndustries.getLogger().error("Multiblock parent is not a multiblock block. Some how??");
            return false;
        }
        Direction facing = level.getBlockState(parentPos).getValue(MBBlock.FACING);
        StructurePlaceSettings placeSettings = new StructurePlaceSettings().setRotation(Util.getRotation(facing));
        StructureUtil.placeInWorld(level,template,parentPos,placeSettings);
        level.destroyBlock(removedPos,true);
        return true;
    }
    */

    public boolean isStructureValid(ServerLevel level, BlockPos pos, Player player) {
        StructureTemplate template = StructureUtil.getStructureTemplate(level,getRegistryName());
        if(template == null) return false;
        return StructureUtil.structureInLevel(level,template,pos,player.getDirection().getOpposite());
    }

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation(BigIndustries.MOD_ID,path);
    }

}
