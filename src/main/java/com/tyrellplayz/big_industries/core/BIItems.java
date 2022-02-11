package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.item.BurnableItem;
import com.tyrellplayz.big_industries.item.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BIItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,BigIndustries.MOD_ID);
    public static final Map<Metals,RegistryObject<Item>> RAW = new HashMap<>();
    public static final Map<Metals,RegistryObject<Item>> CRUSHED = new HashMap<>();
    public static final Map<Metals,RegistryObject<Item>> INGOT = new HashMap<>();
    public static final Map<Metals,RegistryObject<Item>> NUGGET = new HashMap<>();

    static {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                RAW.put(metal,register("raw_"+metal,new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB))));
            }
            CRUSHED.put(metal,register("crushed_"+ metal,new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB))));
            INGOT.put(metal,register(metal+"_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB))));
            NUGGET.put(metal,register( metal+"_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB))));
        }
    }

    public static final RegistryObject<Item> CRUSHED_COAL = register("crushed_coal",new BurnableItem(1600,new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_IRON = register("crushed_iron",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_COPPER = register("crushed_copper",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_GOLD = register("crushed_gold",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static final RegistryObject<Item> COKE = register("coke",new BurnableItem(2200, new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Tools
    public static final RegistryObject<Item> HAMMER = register("hammer",new HammerItem(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static <T extends Item> RegistryObject<T> register(String registryName, T item) {
        return REGISTER.register(registryName,() -> item);
    }

}
