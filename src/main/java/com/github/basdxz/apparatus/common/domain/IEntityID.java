package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;

public interface IEntityID {
    default boolean entityIDEquals(@NonNull IEntityID entityID) {
        return registry().equals(entityID.registry()) && entityName().equals(entityID.entityName());
    }

    default String entityIDToString() {
        return registry().toStringParaRegistry() + ":" + entityName();
    }

    IParaRegistry registry();

    String entityName();
}
