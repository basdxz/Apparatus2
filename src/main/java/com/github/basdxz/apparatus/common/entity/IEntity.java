package com.github.basdxz.apparatus.common.entity;


import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;

import java.util.Map;

public interface IEntity {
    default boolean entityEquals(@NonNull IEntity entity) {
        return entityID().entityIDEquals(entity.entityID());
    }

    default String entityToString() {
        return localizedName();
    }

    IEntityID entityID();

    String localizedName();

    Map<IRendererView, IRenderer> renderers();
}
