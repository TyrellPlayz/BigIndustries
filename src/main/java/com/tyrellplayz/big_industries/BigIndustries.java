package com.tyrellplayz.big_industries;

import com.tyrellplayz.big_industries.client.render.BIInstances;
import com.tyrellplayz.big_industries.core.*;
import com.tyrellplayz.big_industries.data.*;
import com.tyrellplayz.big_industries.multiblock.MultiblockType;
import com.tyrellplayz.big_industries.proxy.ClientProxy;
import com.tyrellplayz.big_industries.proxy.CommonProxy;
import com.tyrellplayz.big_industries.world.worldgen.BIOreFeatures;
import com.tyrellplayz.big_industries.world.worldgen.BIOrePlacements;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tyrellplayz.zlib.proxy.IProxy;

@Mod(BigIndustries.MOD_ID)
public class BigIndustries {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "big_industries";

    public static final CreativeModeTab BLOCK_TAB = new CreativeModeTab(MOD_ID+".blocks") {
        public ItemStack makeIcon() { return new ItemStack(BIBlocks.TIN_ORE.get()); }
    };
    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab(MOD_ID+".items") {
        public ItemStack makeIcon() { return new ItemStack(BIItems.RAW_TIN.get()); }
    };

    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public BigIndustries() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for mod loading
        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::onClientSetup);
        eventBus.addListener(this::gatherData);

        BIBlocks.REGISTER.register(eventBus);
        BIItems.REGISTER.register(eventBus);
        BIBlockEntities.REGISTER.register(eventBus);
        BIMenus.REGISTER.register(eventBus);

        //MinecraftForge.EVENT_BUS.register(ModFeatures.class);

        MinecraftForge.EVENT_BUS.addListener(this::registerRegistries);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event) {

        BIOreFeatures.init();
        BIOrePlacements.init();

        BIInstances.init();

        proxy.onCommonSetup(event);
    }

    public void onClientSetup(final FMLClientSetupEvent event) {
        proxy.onClientSetup(event);
    }

    public void gatherData(final GatherDataEvent dataEvent) {
        DataGenerator generator = dataEvent.getGenerator();
        if(dataEvent.includeClient()) {
            generator.addProvider(new BlockStateGen(generator,dataEvent.getExistingFileHelper()));
            generator.addProvider(new ItemModelGen(generator,dataEvent.getExistingFileHelper()));
        }
        if(dataEvent.includeServer()) {
            BlockTagGen blockTagGen = new BlockTagGen(generator,dataEvent.getExistingFileHelper());
            generator.addProvider(blockTagGen);
            generator.addProvider(new ItemTagGen(generator,blockTagGen,dataEvent.getExistingFileHelper()));
            generator.addProvider(new LootTableGen(generator));
            generator.addProvider(new RecipeGen(generator));
        }
    }

    public void registerRegistries(RegistryEvent.NewRegistry event) {
        createRegistry(new ResourceLocation(MOD_ID,"multiblock_type"), MultiblockType.class);
    }

    public <T extends IForgeRegistryEntry<T>> void createRegistry(ResourceLocation key, Class<T> type) {
        new RegistryBuilder<T>().setName(key).setType(type).setDefaultKey(key).create();
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
