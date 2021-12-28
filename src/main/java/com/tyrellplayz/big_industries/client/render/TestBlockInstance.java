package com.tyrellplayz.big_industries.client.render;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.backend.instancing.tile.TileEntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.tyrellplayz.big_industries.blockentity.TestBlockEntity;
import com.tyrellplayz.big_industries.core.BIBlocks;

public class TestBlockInstance extends TileEntityInstance<TestBlockEntity> {

    private final ModelData modelData;

    public TestBlockInstance(MaterialManager materialManager, TestBlockEntity tile) {
        super(materialManager, tile);

        modelData = materialManager.defaultSolid()
                .material(Materials.TRANSFORMED)
                .getModel(BIBlocks.TEST_BLOCK.get().defaultBlockState())
                .createInstance();

        modelData.loadIdentity()
                .translate(getInstancePosition())
                .translate(-0.5,0,-0.5);
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
