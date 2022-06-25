package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

//Edible
//Provides Food
//Gives user speed buff
public class YummyBacon implements IParaItem {
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
