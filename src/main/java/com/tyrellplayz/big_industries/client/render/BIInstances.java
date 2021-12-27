package com.tyrellplayz.big_industries.client.render;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;
import com.tyrellplayz.big_industries.core.BIBlockEntities;

public class BIInstances {

    public static void init() {
        InstancedRenderRegistry registry = InstancedRenderRegistry.getInstance();

        registry.tile(BIBlockEntities.BLAST_FURNACE.get())
                .setSkipRender(true) // Completely skip the BlockEntityRenderer.
                .factory(BlastFurnaceInstance::new); // Use our TileEntityInstance instead.

        registry.tile(BIBlockEntities.TEST_BLOCK.get())
                .setSkipRender(true) // Completely skip the BlockEntityRenderer.
                .factory(TestBlockInstance::new); // Use our TileEntityInstance instead.
    }

}
