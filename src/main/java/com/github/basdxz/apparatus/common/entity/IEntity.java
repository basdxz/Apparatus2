package com.github.basdxz.apparatus.common.entity;


import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resourceold.IRenderer;
import lombok.*;

import java.util.Map;

public interface IEntity {
    String ENTITY_TYPE_NAME = "entity";

    default boolean entityEquals(@NonNull IEntity entity) {
        return entityID().entityIDEquals(entity.entityID());
    }

    default String entityToString() {
        return localizedName();
    }

    default String unlocalizedName() {
        return String.join(
                ".",
                typeName(),
                domainName(),
                entityName()
        );
    }

    default String typeName() {
        return ENTITY_TYPE_NAME;
    }

    default String domainName() {
        return entityID().domain().domainName();
    }

    default String entityName() {
        return entityID().entityName();
    }

    IEntityID entityID();

    String localizedName();

    Map<IRendererView, IRenderer> renderers();
}
