package com.tyrellplayz.big_industries.core;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.inventory.BlastFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BIMenus {

    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES,BigIndustries.MOD_ID);

    public static final RegistryObject<MenuType<BlastFurnaceMenu>> BLAST_FURNACE = register("blast_furnace", BlastFurnaceMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String registryName, MenuType.MenuSupplier<T> supplier) {
        MenuType<T> menuType = new MenuType<>(supplier);
        return REGISTER.register(registryName,() -> menuType);
    }

}
