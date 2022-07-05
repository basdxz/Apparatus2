package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IEntityID {
    default boolean entityIDEquals(@NonNull IEntityID entityID) {
        return domain().equals(entityID.domain()) && entityName().equals(entityID.entityName());
    }

    default String entityIDToString() {
        return domain().domainToString() + ":" + entityName();
    }

    IDomain domain();

    String entityName();
}
