package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.tile.*;
import com.tyrellplayz.big_industries.tile.renderer.WaterwheelTileRenderer;
import com.tyrellplayz.big_industries.util.TileNames;
import com.tyrellplayz.zlib.registry.TileRegistry;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BITiles extends TileRegistry {

    public static final TileEntityType<WaterwheelTile> WATERWHEEL = register(TileNames.WATERWHEEL,WaterwheelTile::new,BIBlocks.WATERWHEEL);

    public static final TileEntityType<CokeOvenTile> COKE_OVEN = register(TileNames.COKE_OVEN,CokeOvenTile::new,BIBlocks.COKE_OVEN_CONTROLLER);

    //public static final TileEntityType<CombustionEngineTile> COMBUSTION_ENGINE = register(TileNames.COMBUSTION_ENGINE, CombustionEngineTile::new, BIBlocks.COMBUSTION_ENGINE);
    public static final TileEntityType<EnergyConduitTile> ENERGY_CONDUIT = register(TileNames.ENERGY_CONDUIT, EnergyConduitTile::new, BIBlocks.ENERGY_CONDUIT);

    public static final TileEntityType<CrusherMachineTile> CRUSHER_MACHINE = register(TileNames.CRUSHER_MACHINE,CrusherMachineTile::new, BIBlocks.CRUSHER_MACHINE);

}
