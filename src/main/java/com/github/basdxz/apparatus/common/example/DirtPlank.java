package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.ITile;
import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Renders as block with different sides
//Has a slight glow
public class DirtPlank implements ITile {
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
