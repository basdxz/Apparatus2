package com.github.basdxz.apparatus.common.registry;

public interface IParaID {
    default String toStringParaID() {
        return registry().toStringParaRegistry() + ":" + paraName();
    }

    IParaRegistry registry();

    String paraName();
}
