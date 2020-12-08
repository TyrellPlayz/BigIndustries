package com.tyrellplayz.big_industries.item;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.util.Util;
import com.tyrellplayz.zlib.util.helpers.ServerHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class PlaceTemplateToolItem extends Item {

    public PlaceTemplateToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if(ServerHelper.isServerWorld(world)) {
            Template template = Util.getTemplate((ServerWorld) world,new ResourceLocation(BigIndustries.MOD_ID,"coke_oven"));
            PlacementSettings placementSettings = new PlacementSettings()
                    .setMirror(Mirror.FRONT_BACK)
                    .setRotation(getRotation(context.getPlacementHorizontalFacing()));
            template.func_237144_a_((IServerWorld) world,context.getPos(),placementSettings,new Random());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    public Rotation getRotation(Direction direction) {
        switch (direction) {
            case EAST:
                return Rotation.COUNTERCLOCKWISE_90;
            case SOUTH:
                return Rotation.NONE;
            case WEST:
                return Rotation.CLOCKWISE_90;
            default:
                return Rotation.CLOCKWISE_180;
        }
    }

}
