package com.tyrellplayz.big_industries.data.loot;

import com.tyrellplayz.big_industries.core.BIBlocks;
import com.tyrellplayz.zlib.data.loot.AbstractBlockLootProvider;
import net.minecraft.data.DataGenerator;

public class BIBlockLoot extends AbstractBlockLootProvider {

    public BIBlockLoot(DataGenerator generator, String modId) {
        super(generator, modId);
    }

    @Override
    protected void registerTables() {

        registerAllRemainingAsDefault(BIBlocks.getBlocks());
    }
}
