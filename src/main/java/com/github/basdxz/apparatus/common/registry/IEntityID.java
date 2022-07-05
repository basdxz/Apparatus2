package com.github.basdxz.apparatus.common.registry;

public interface IEntityID {
    default String toStringParaID() {
        return registry().toStringParaRegistry() + ":" + paraName();
    }

    IParaRegistry registry();

    String paraName();
}
