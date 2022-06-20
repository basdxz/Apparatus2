package com.github.basdxz.apparatus.common.registry;


import com.github.basdxz.apparatus.common.domain.IDomain;

public interface IParaRegistry {
    String registryName();

    String classPath();

    IDomain domain();
}
