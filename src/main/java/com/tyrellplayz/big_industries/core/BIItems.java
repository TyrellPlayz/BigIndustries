package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.item.BurnableItem;
import com.tyrellplayz.big_industries.item.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BIItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,BigIndustries.MOD_ID);

    // Tin
    public static final RegistryObject<Item> RAW_TIN = register("raw_tin",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_DUST = register("tin_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_INGOT = register("tin_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_NUGGET = register("tin_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_GEAR = register("tin_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Lead
    public static final RegistryObject<Item> RAW_LEAD = register("raw_lead",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_DUST = register("lead_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_INGOT = register("lead_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_NUGGET = register("lead_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_GEAR = register("lead_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Aluminium
    public static final RegistryObject<Item> RAW_ALUMINIUM = register("raw_aluminium",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_DUST = register("aluminium_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = register("aluminium_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_NUGGET = register("aluminium_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Silver
    public static final RegistryObject<Item> RAW_SILVER = register("raw_silver",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> SILVER_DUST = register("silver_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = register("silver_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> SILVER_NUGGET = register("silver_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Nickel

    // Steel
    public static final RegistryObject<Item> STEEL_DUST = register("steel_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_INGOT = register("steel_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = register("steel_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_GEAR = register("steel_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static final RegistryObject<Item> COAL_DUST = register("coal_dust",new BurnableItem(1600,new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> IRON_DUST = register("iron_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> COPPER_DUST = register("copper_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> GOLD_DUST = register("gold_dust",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Tools
    public static final RegistryObject<Item> HAMMER = register("hammer",new HammerItem(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static <T extends Item> RegistryObject<T> register(String registryName, T item) {
        return REGISTER.register(registryName,() -> item);
    }

}
