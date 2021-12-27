package com.tyrellplayz.big_industries.client.render;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.backend.instancing.tile.TileEntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.tyrellplayz.big_industries.blockentity.BlastFurnaceEntity;
import com.tyrellplayz.big_industries.core.BIBlocks;

public class BlastFurnaceInstance extends TileEntityInstance<BlastFurnaceEntity> {

    private final ModelData modelData;

    public BlastFurnaceInstance(MaterialManager materialManager, BlastFurnaceEntity tile) {
        super(materialManager, tile);

        modelData = materialManager.defaultSolid()
                .material(Materials.TRANSFORMED)
                .getModel(BIBlocks.BLAST_FURNACE.get().defaultBlockState())
                .createInstance();

        modelData.loadIdentity()
                .translate(getInstancePosition());
    }

    @Override
    public void remove() {
        modelData.delete();
    }

    @Override
    public void updateLight() {
        relight(getWorldPosition(),modelData);
    }
}
