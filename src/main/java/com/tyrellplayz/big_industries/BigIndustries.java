package com.tyrellplayz.big_industries;

import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import com.tyrellplayz.big_industries.data.BIBlockTagsProvider;
import com.tyrellplayz.big_industries.data.BIItemTagsProvider;
import com.tyrellplayz.big_industries.data.BIRecipes;
import com.tyrellplayz.big_industries.data.loot.BIBlockLoot;
import com.tyrellplayz.big_industries.events.WorldEvents;
import com.tyrellplayz.big_industries.network.server.SEnergyGridCreatedMessage;
import com.tyrellplayz.big_industries.network.server.SEnergyGridDeletedMessage;
import com.tyrellplayz.big_industries.proxy.ClientProxy;
import com.tyrellplayz.big_industries.proxy.CommonProxy;
import com.tyrellplayz.zlib.ZMod;
import com.tyrellplayz.zlib.data.BasicBlockModelProvider;
import com.tyrellplayz.zlib.data.BasicBlockStateProvider;
import com.tyrellplayz.zlib.data.ItemModelProvider;
import com.tyrellplayz.zlib.network.NetworkManager;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod(BigIndustries.MOD_ID)
public class BigIndustries extends ZMod {

    public static final String MOD_ID = "big_industries";
    public static final String MOD_VERSION = "0.1.0";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final ItemGroup BLOCKS_GROUP = new ItemGroup(MOD_ID+".blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BIBlocks.COPPER_ORE);
        }
    };
    public static final ItemGroup ITEMS_GROUP = new ItemGroup(MOD_ID+".items") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BIItems.COPPER_INGOT);
        }
    };
    public static NetworkManager NETWORK_MANAGER;

    public BigIndustries() {
        super(MOD_ID);
        NETWORK_MANAGER = new NetworkManager(MOD_ID);
        NETWORK_MANAGER.registerPlayMessage(SEnergyGridCreatedMessage.class, LogicalSide.SERVER);
        NETWORK_MANAGER.registerPlayMessage(SEnergyGridDeletedMessage.class, LogicalSide.SERVER);

        MinecraftForge.EVENT_BUS.register(WorldEvents.class);
    }

    @Override
    public void onCommonSetup(FMLCommonSetupEvent event) {
        proxy.onCommonSetup(event);
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        proxy.onClientSetup(event);
    }

    @Override
    public void dataGeneratorSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        dataGenerator.addProvider(new BasicBlockModelProvider(dataGenerator,MOD_ID, BIBlocks.getBlocks()));
        dataGenerator.addProvider(new ItemModelProvider(dataGenerator,MOD_ID, BIBlocks.getBlocks(), BIItems.getItems()));
        dataGenerator.addProvider(new BasicBlockStateProvider(dataGenerator,MOD_ID, BIBlocks.getBlocks()));
        dataGenerator.addProvider(new BIRecipes(dataGenerator));
        BIBlockTagsProvider blockTagsProvider = new BIBlockTagsProvider(dataGenerator,MOD_ID,event.getExistingFileHelper());
        dataGenerator.addProvider(blockTagsProvider);
        dataGenerator.addProvider(new BIItemTagsProvider(dataGenerator,blockTagsProvider,MOD_ID,event.getExistingFileHelper()));
        dataGenerator.addProvider(new BIBlockLoot(dataGenerator,MOD_ID));
    }

    public static NetworkManager getNetworkManager() {
        return NETWORK_MANAGER;
    }
}
