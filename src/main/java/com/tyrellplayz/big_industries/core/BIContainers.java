package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.container.CokeOvenContainer;
import com.tyrellplayz.big_industries.common.container.CombustionEngineContainer;
import com.tyrellplayz.big_industries.common.container.CrusherMachineContainer;
import com.tyrellplayz.big_industries.util.TileNames;
import com.tyrellplayz.zlib.registry.ContainerRegistry;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BigIndustries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BIContainers extends ContainerRegistry {

    public static final ContainerType<CokeOvenContainer> COKE_OVEN = register(TileNames.COKE_OVEN,CokeOvenContainer::new);

    public static final ContainerType<CombustionEngineContainer> COMBUSTION_ENGINE = register(TileNames.COMBUSTION_ENGINE, CombustionEngineContainer::new);
    public static final ContainerType<CrusherMachineContainer> CRUSHER_MACHINE = register(TileNames.CRUSHER_MACHINE, CrusherMachineContainer::new);

}
