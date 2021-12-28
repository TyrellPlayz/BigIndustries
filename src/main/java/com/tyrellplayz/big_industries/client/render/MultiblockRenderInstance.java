package com.tyrellplayz.big_industries.client.render;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.backend.instancing.tile.TileEntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.model.ModelData;
import com.tyrellplayz.big_industries.blockentity.MultiblockEntity;

public class MultiblockRenderInstance<T extends MultiblockEntity<T>> extends TileEntityInstance<MultiblockEntity<T>> {

    private final ModelData modelData;

    public MultiblockRenderInstance(MaterialManager materialManager, MultiblockEntity<T> tile) {
        super(materialManager, tile);

        modelData = materialManager.defaultSolid()
                .material(Materials.TRANSFORMED)
                .getModel(tile.getBlockState())
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
