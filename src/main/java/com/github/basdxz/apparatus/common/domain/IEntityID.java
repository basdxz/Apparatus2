package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.registry.IParaRegistry;

public interface IEntityID {
    default String toStringParaID() {
        return registry().toStringParaRegistry() + ":" + entityName();
    }

    IParaRegistry registry();

    String entityName();
}
