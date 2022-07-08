package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resourceold.IRenderer;

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
