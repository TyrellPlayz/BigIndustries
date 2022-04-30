package com.tyrellplayz.big_industries.data;

import com.tyrellplayz.big_industries.BigIndustries;
import com.tyrellplayz.big_industries.Metals;
import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.big_industries.core.BIItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

public class LanguageGen extends LanguageProvider {

    public LanguageGen(DataGenerator gen) {
        super(gen, BigIndustries.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (Metals metal : Metals.values()) {
            if(!metal.isAlloy()) {
                addBlock(metal::getOre,StringUtils.capitalize(metal.toString())+" Ore");
                addBlock(metal::getDeepslateOre,"Deepslate "+StringUtils.capitalize(metal.toString())+" Ore");
                addBlock(metal::getRawStorageBlock,"Block of Raw"+ StringUtils.capitalize(metal.toString()));

                addItem(metal::getRaw,"Raw "+StringUtils.capitalize(metal.toString()));
            }
            addBlock(metal::getStorageBlock,"Block of "+ StringUtils.capitalize(metal.toString()));

            addItem(metal::getCrushed,"Crushed "+StringUtils.capitalize(metal.toString()));
            addItem(metal::getIngot,StringUtils.capitalize(metal.toString())+" Ingot");
            addItem(metal::getNugget,StringUtils.capitalize(metal.toString())+" Nugget");
        }

        blockTranslations();
        itemTranslations();
        containerTranslations();
        menuTranslations();
        miscTranslations();
    }

    private void blockTranslations() {
        addBlock(BIBlocks.BLAST_BRICK,"Blast Brick");
        addBlock(BIBlocks.BLAST_FURNACE,"Blast Furnace");

        addBlock(BIBlocks.FLUID_PIPE,"Fluid Pipe");
    }

    private void itemTranslations() {
        addItem(BIItems.CRUSHED_COAL,"Crushed Coal");
        addItem(BIItems.CRUSHED_IRON,"Crushed Iron");
        addItem(BIItems.CRUSHED_COPPER,"Crushed Copper");
        addItem(BIItems.CRUSHED_GOLD,"Crushed Gold");

        addItem(BIItems.HAMMER,"Hammer");
        addItem(BIItems.COKE,"Coke");
    }

    private void containerTranslations() {

    }

    private void menuTranslations() {

    }

    private void miscTranslations() {
        addCreativeModeTab(() -> BigIndustries.BLOCK_TAB,"Big Industries (Blocks)");
        addCreativeModeTab(() -> BigIndustries.ITEM_TAB,"Big Industries (Items)");
    }

    private void addCreativeModeTab(Supplier<? extends CreativeModeTab> key, String name) {
        TranslatableComponent component = (TranslatableComponent)key.get().getDisplayName();
        add("itemGroup."+component.getKey(),name);
    }

}
