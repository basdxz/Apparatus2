package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.IParaFluid;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Creates a fluid
public class OrangeJuice implements IParaFluid {
    @Override
    public IParaID paraID() {
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
