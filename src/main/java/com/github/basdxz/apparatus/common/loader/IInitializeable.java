package com.github.basdxz.apparatus.common.loader;

public interface IInitializeable {
    void preInit();

    void init();

    void postInit();
}
