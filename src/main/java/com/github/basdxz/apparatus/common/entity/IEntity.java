package com.github.basdxz.apparatus.common.entity;


import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.render.IEntityRenderer;
import com.github.basdxz.apparatus.common.render.impl.TestEntityRenderer;
import lombok.*;

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
    //TODO: make not default
    default IEntityRenderer entityRenderer() {
        return TestEntityRenderer.INSTANCE;
    }
}
