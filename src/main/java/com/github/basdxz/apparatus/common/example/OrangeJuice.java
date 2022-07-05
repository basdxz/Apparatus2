package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.IFluid;
import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Creates a fluid
public class OrangeJuice implements IFluid {
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
