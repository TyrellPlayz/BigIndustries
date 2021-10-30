package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.common.ModTags;
import com.tyrellplayz.big_industries.data.ItemTagGen;
import com.tyrellplayz.big_industries.item.BurnableItem;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,BigIndustries.MOD_ID);

    // Tin
    public static final RegistryObject<Item> RAW_TIN = register("raw_tin",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_TIN = register("crushed_tin",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_INGOT = register("tin_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_NUGGET = register("tin_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> TIN_GEAR = register("tin_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Lead
    public static final RegistryObject<Item> RAW_LEAD = register("raw_lead",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_LEAD = register("crushed_lead",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_INGOT = register("lead_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_NUGGET = register("lead_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> LEAD_GEAR = register("lead_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Aluminium
    public static final RegistryObject<Item> RAW_ALUMINIUM = register("raw_aluminium",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_ALUMINIUM = register("crushed_aluminium",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = register("aluminium_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_NUGGET = register("aluminium_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Silver
    public static final RegistryObject<Item> SILVER_INGOT = register("silver_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Steel
    public static final RegistryObject<Item> CRUSHED_STEEL = register("crushed_steel",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_INGOT = register("steel_ingot",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = register("steel_nugget",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> STEEL_GEAR = register("steel_gear",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static final RegistryObject<Item> CRUSHED_COAL = register("crushed_coal",new BurnableItem(1600,new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_IRON = register("crushed_iron",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_COPPER = register("crushed_copper",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));
    public static final RegistryObject<Item> CRUSHED_GOLD = register("crushed_gold",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    //public static final RegistryObject<Item> SOLAR_CELL = register("solar_cell",new Item(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    // Tools
    //public static final RegistryObject<Item> HAMMER = register("hammer",new HammerItem(new Item.Properties().tab(BigIndustries.ITEM_TAB)));

    public static <T extends Item> RegistryObject<T> register(String registryName, T item) {
        return REGISTER.register(registryName,() -> item);
    }

}
