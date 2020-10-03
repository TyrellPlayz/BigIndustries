package com.tyrellplayz.big_industries.block;

import com.tyrellplayz.zlib.block.ZHorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;

import java.util.List;

public class SteamEngine extends ZHorizontalBlock {

    public SteamEngine(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.WHITE+"Turns solid fuel into steam and energy"));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }
}
