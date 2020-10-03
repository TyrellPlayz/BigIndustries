package com.tyrellplayz.big_industries.block;

import com.google.gson.JsonObject;
import com.tyrellplayz.big_industries.tile.CombustionEngineTile;
import com.tyrellplayz.zlib.block.ICustomBlockState;
import com.tyrellplayz.zlib.block.ZBlock;
import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import com.tyrellplayz.zlib.block.ZHorizontalWaterloggedBlock;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import com.tyrellplayz.zlib.util.helpers.VoxelShapeHelper;
import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Turns solid fuel into energy.
 */
public class CombustionEngineBlock extends ZHorizontalBlock implements ICustomBlockState {

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public CombustionEngineBlock(Properties properties) {
        super(properties.notSolid());
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(ON,false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(ON) ? 13 : 0;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(TextFormatting.WHITE+"Turns solid fuel to energy"));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape base = VoxelShapeHelper.create(0,0,7,0,2,0,state.get(FACING));
        VoxelShape tanks = VoxelShapeHelper.create(9,0,0,0,0,0,state.get(FACING));
        return VoxelShapeHelper.combineAll(base,tanks);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ON);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(ServerHelper.isServerWorld(worldIn)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CombustionEngineTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player,(INamedContainerProvider) tileEntity,pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CombustionEngineTile();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(ON)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.get(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getXOffset() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getZOffset() * 0.52D : d4;
            worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public JsonObject getBlockStateObject(ResourceLocation registryName) {
        JsonObject object = new JsonObject();
        JsonObject variants = new JsonObject();

        variants.add("facing=north,on=false",getFacingVariant(registryName,0,false));
        variants.add("facing=east,on=false",getFacingVariant(registryName,90,false));
        variants.add("facing=south,on=false",getFacingVariant(registryName,180,false));
        variants.add("facing=west,on=false",getFacingVariant(registryName,270,false));

        variants.add("facing=north,on=true",getFacingVariant(registryName,0,true));
        variants.add("facing=east,on=true",getFacingVariant(registryName,90,true));
        variants.add("facing=south,on=true",getFacingVariant(registryName,180,true));
        variants.add("facing=west,on=true",getFacingVariant(registryName,270,true));

        object.add("variants",variants);
        return object;
    }

    private JsonObject getFacingVariant(ResourceLocation registryName, int rotation, boolean on) {
        JsonObject facingObject = new JsonObject();
        if(on) facingObject.addProperty("model",registryName.getNamespace()+":block/"+registryName.getPath()+"_on");
        else facingObject.addProperty("model",registryName.getNamespace()+":block/"+registryName.getPath());
        facingObject.addProperty("y",rotation);
        return facingObject;
    }

}
