package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Bow that shoots every arrow in the inventory at once
public class BFGTheBow implements IParaItem {
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
