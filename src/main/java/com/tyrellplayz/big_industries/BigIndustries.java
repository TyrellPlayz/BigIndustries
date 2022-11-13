package com.tyrellplayz.big_industries;

import com.tyrellplayz.big_industries.core.BIBlockEntities;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import com.tyrellplayz.big_industries.core.BIMenus;
import com.tyrellplayz.big_industries.data.*;
import com.tyrellplayz.big_industries.world.biomemod.BIBiomeModifiers;
import com.tyrellplayz.big_industries.world.feature.BIConfiguredFeatures;
import com.tyrellplayz.big_industries.world.feature.BIPlacedFeatures;
import com.tyrellplayz.zlib.proxy.ModProxy;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BigIndustries.MOD_ID)
public class BigIndustries {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "big_industries";

    public static final CreativeModeTab BLOCK_TAB = new CreativeModeTab(MOD_ID+".blocks") {
        public ItemStack makeIcon() { return new ItemStack(BIBlocks.ORE.get(Metals.TIN).get()); }
    };
    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab(MOD_ID+".items") {
        public ItemStack makeIcon() { return new ItemStack(BIItems.INGOT.get(Metals.TIN).get()); }
    };

    public static ModProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

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

        BIBiomeModifiers.BIOME_MODIFIERS.register(eventBus);
        BIConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        BIPlacedFeatures.PLACED_FEATURES.register(eventBus);

        //MinecraftForge.EVENT_BUS.register(ModFeatures.class);

        MinecraftForge.EVENT_BUS.addListener(this::registerRegistries);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event) {
        proxy.onCommonSetup(event);
    }

    public void onClientSetup(final FMLClientSetupEvent event) {
        proxy.onClientSetup(event);
    }

    public void gatherData(final GatherDataEvent dataEvent) {
        DataGenerator generator = dataEvent.getGenerator();
        generator.addProvider(dataEvent.includeClient(),new BlockStateGen(generator,dataEvent.getExistingFileHelper()));
        generator.addProvider(dataEvent.includeClient(),new ItemModelGen(generator,dataEvent.getExistingFileHelper()));
        generator.addProvider(dataEvent.includeClient(),new LanguageGen(generator));

        BlockTagGen blockTagGen = new BlockTagGen(generator,dataEvent.getExistingFileHelper());
        generator.addProvider(dataEvent.includeServer(),blockTagGen);
        generator.addProvider(dataEvent.includeServer(),new ItemTagGen(generator,blockTagGen,dataEvent.getExistingFileHelper()));
        generator.addProvider(dataEvent.includeServer(),new LootTableGen(generator));
        generator.addProvider(dataEvent.includeServer(),new RecipeGen(generator));
    }

    public void registerRegistries(NewRegistryEvent event) {
        // FIXME: Update to 1.19
        //createRegistry(event,new ResourceLocation(MOD_ID,"multiblock_type"), MultiblockType.class);
    }

    public <T extends IForgeRegistry<T>> void createRegistry(NewRegistryEvent event, ResourceLocation key, Class<T> type) {
        // FIXME: Update to 1.19
        //event.create(new RegistryBuilder<T>().setName(key).setType(type).setDefaultKey(key));
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
