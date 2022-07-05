package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.ITile;
import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Chest with a custom TESR
//Has GUI on client and server side
//Uses NBT to store the internal items`
public class ThreeSlotChest implements ITile {
    @Override
    public IEntityID entityID() {
        return null;
    }

    @Override
    public String localizedName() {
        return null;
    }

    @Override
    public Map<IRendererView, IRenderer> renderers() {
        return null;
    }
}
