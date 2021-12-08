package com.tyrellplayz.big_industries.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class SplitOBJModelLoader implements IModelLoader<OBJModel> {


    @Override
    public OBJModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return null;
    }

    @Override
    public void onResourceManagerReload(ResourceManager p_10758_) {

    }
}
