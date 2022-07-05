package com.github.basdxz.apparatus.common.parathing;


import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;

import java.util.Map;

public interface IEntity {
    IEntityID entityID();//TODO: Better delegates

    String localizedName();

    Map<IRendererView, IRenderer> renderers();
}
