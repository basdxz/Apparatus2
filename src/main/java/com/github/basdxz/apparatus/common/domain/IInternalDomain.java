package com.github.basdxz.apparatus.common.domain;


import java.util.List;

public interface IInternalDomain extends IDomain, IRegistry {
    @Override
    default String registryName() {
        return domainName();
    }

    List<String> loaderPackages();
}
